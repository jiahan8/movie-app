package com.jiahan.fave.viewmodelannocompiler;

import com.google.auto.service.AutoService;
import com.jiahan.fave.viewmodelanno.ViewModelFactory;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.tools.JavaFileObject;

@AutoService(Processor.class)
public class ViewModelAnnoCompiler extends AbstractProcessor {
    Filer mFiler;

    @Override
    public synchronized void init(final ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        mFiler = processingEnvironment.getFiler();
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> set = new HashSet<>();
        set.add(ViewModelFactory.class.getCanonicalName());
        return set;
    }

    @Override
    public boolean process(final Set<? extends TypeElement> set, final RoundEnvironment roundEnvironment) {
        // Class - TypeElement
        // Function , Constructor - ExecutableElement
        // Field - VariableElement
        Set<? extends Element> viewModelElement = roundEnvironment.getElementsAnnotatedWith(ViewModelFactory.class);
        Map<String, String> mViewModelFactory = new HashMap<>();

        for (Element e : viewModelElement) {
            ExecutableElement executableElement = (ExecutableElement) e;
            TypeElement viewModel = (TypeElement) executableElement.getEnclosingElement();

            String packageName = processingEnv.getElementUtils().getPackageOf(viewModel).getQualifiedName().toString();
            String vmName = viewModel.getSimpleName().toString();
            StringBuilder sbFinalVar = new StringBuilder();
            StringBuilder sbConstructorVar = new StringBuilder();
            StringBuilder sbConstructorBody = new StringBuilder();
            StringBuilder sbCreatorVar = new StringBuilder();

            List<VmConstrParam> vmParams = getVmConstrParams(executableElement);
            for (VmConstrParam params : vmParams) {
                append(sbFinalVar, "private final %s;\n    ", params.getTypeAndName());
                append(sbConstructorVar, ", %s ", params.getTypeAndName());
                append(sbConstructorBody, "this.%1$s = %1$s;\n    ", params.getName());
                append(sbCreatorVar, ", %s ", params.getName());
            }
            trimFirstDots(sbConstructorVar);
            trimFirstDots(sbCreatorVar);

            String factory2File = String.format("" +
                            "package %1$s;\n" +
                            "\n" +
                            "import androidx.annotation.NonNull;\n" +
                            "import androidx.lifecycle.ViewModel;\n" +
                            "import androidx.lifecycle.ViewModelProvider;\n" +
                            "\n" +
                            "public class %2$sFactory implements ViewModelProvider.Factory{\n" +
                            "        %3$s\n" +
                            "\n" +
                            "        public static ViewModelProvider.Factory create(%4$s) {\n" +
                            "            return new %2$sFactory(%6$s);\n" +
                            "        }\n"+
                            "\n" +
                            "        public %2$sFactory(%4$s) {\n" +
                            "            %5$s\n" +
                            "        }\n" +
                            "\n" +
                            "        @NonNull\n" +
                            "        @Override\n" +
                            "        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {\n" +
                            "            return (T) new %2$s(%6$s);\n" +
                            "        }\n" +
                            "}\n" ,
                    packageName,
                    vmName,
                    sbFinalVar,
                    sbConstructorVar,
                    sbConstructorBody,
                    sbCreatorVar);
            mViewModelFactory.put(viewModel.getQualifiedName() + "Factory", factory2File);
        }

        if (mViewModelFactory.size() > 0) {
            for (Map.Entry<String, String> javaFile : mViewModelFactory.entrySet()) {
                Writer writer = null;
                try {
                    JavaFileObject sourceFile = mFiler.createSourceFile(javaFile.getKey());
                    writer = sourceFile.openWriter();
                    writer.write(javaFile.getValue());
                    System.out.println(javaFile.getKey() +"Written");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                } finally {
                    if (writer != null) {
                        try {
                            writer.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return false;
    }

    private List<VmConstrParam> getVmConstrParams(ExecutableElement vmConstructor) {
        List<VmConstrParam> vmConstrParams = new ArrayList<>();
        for (VariableElement param : vmConstructor.getParameters()) {
            vmConstrParams.add(new VmConstrParam(
                    param.asType().toString(),
                    param.getSimpleName().toString()));
        }
        return vmConstrParams;
    }

    private void append(StringBuilder stringBuilder, String format, String... args) {
        stringBuilder.append(String.format(format, (Object[]) args));
    }

    private void trimFirstDots(StringBuilder sb) {
        if (sb.charAt(0) == ',') {
            sb.deleteCharAt(0);
        }
    }

    public class VmConstrParam {
        private final String type;
        private final String name;
        private final String typeAndName;

        public VmConstrParam(String type, String name) {
            this.type = type;
            this.name = name;
            typeAndName = type + " " + name;
        }

        public String getType() {
            return type;
        }

        public String getName() {
            return name;
        }

        public String getTypeAndName() {
            return typeAndName;
        }
    }

}