package com.jiahan.fave.core.utils;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.TypedArray;
import android.net.Uri;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import com.jiahan.fave.core.R;
import com.jiahan.fave.core.common.CoreApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.*;

public class Utils {
    private static final int MULTIPLE_BUTTON_CLICK_DELAY = 1000;

    /**
     * copy text to clipboard, can show toast also
     *
     * @param text
     * @param toastMessage can send null if if no need to show toast otherwise will show toast with toastMessage values
     */
    public static void copyToClipboard(final String text,
                                       final String toastMessage) {
        ClipboardManager clipboard = (ClipboardManager) CoreApplication.getInstance().getApplicationContext().getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("", text);
        clipboard.setPrimaryClip(clip);
        if (!TextUtils.isEmpty(toastMessage)) {
            Toast.makeText(CoreApplication.getInstance().getApplicationContext(), toastMessage, Toast.LENGTH_LONG).show();
        }
    }

    public static String join(String delimeter, String[] array) {
        if (array == null || array.length == 0) return "";
        return TextUtils.join(delimeter, array);
    }

    public static String join(String delimeter, List<String> list) {
        if (list == null || list.size() == 0) return "";
        return TextUtils.join(delimeter, list);
    }


    public static int dpToPx(Context context, int dp) {
        return (int) ((dp * context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static int getActionBarHeight(Context context) {
        final TypedArray typedArray = context.getTheme().obtainStyledAttributes(new int[]{android.R.attr.actionBarSize});
        int height = (int) typedArray.getDimension(0, 0);
        typedArray.recycle();
        return height;
    }

    public static DisplayMetrics getScreenDimension(Context context) {
        return context.getResources().getDisplayMetrics();
    }

    public static void openShareIntent(Context context, String shareContent) {
        final Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, shareContent);
        context.startActivity(Intent.createChooser(intent, context.getString(R.string.title_share_via)));
    }

    public static void openDialer(Context context, String telephone) {
        try {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:" + telephone));
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        } catch (Exception ex) {
            Toast.makeText(context, context.getString(R.string.msg_error_no_dialer), Toast.LENGTH_SHORT).show();
            Logger.logException(ex);
        }
    }

    // Helps avoiding mistaken multiple clicks of a view
    public static void avoidMultipleClicks(final View v) {
        if (v == null) {
            return;
        }
        v.setEnabled(false);
        v.postDelayed(() -> v.setEnabled(true), MULTIPLE_BUTTON_CLICK_DELAY);
    }

    public static boolean isPermissionGranted(final Context context, String... permissions) {
        if (permissions == null || permissions.length == 0) {
            return false;
        }
        for (String permission : permissions) {
            try {
                if (ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            } catch (RuntimeException e) {
                Logger.logException(e);
                return false;
            }
        }
        return true;
    }

    public static void vibrate() {
        Vibrator vibrator = (Vibrator) CoreApplication.getInstance().getSystemService(Activity.VIBRATOR_SERVICE);
        if (Build.VERSION.SDK_INT >= 26) {
            vibrator.vibrate(VibrationEffect.createOneShot(Constant.VIBRATE_DURATION, VibrationEffect.DEFAULT_AMPLITUDE));
            return;
        }
        vibrator.vibrate(Constant.VIBRATE_DURATION);
    }


    /**
     * Return full image url.
     */
    public static String getImageURL(String secret) {
        return "https://image.tmdb.org/t/p/w500$secret";
    }

    public static String floatToString(float f) {
        return String.valueOf(f);
    }

    public static String getGenre(List<Object> genres) {
//        List<String> result = new ArrayList<>();
//        for(int i=0; i<genres.size(); i++){
//            result.add(genres.get(i).name);
//        }
//        return TextUtils.join(",", result);
        return "";
    }

    /**
     * Convert ISO to full form.
     */
    public static String isoToLanguage( String iso ) {
        if (iso != null) {
            return new Locale(iso).getDisplayLanguage( new Locale(iso));
        } else {
            return "";
        }
    }



}