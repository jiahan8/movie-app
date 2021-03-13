package com.jiahan.fave.core.utils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.jiahan.fave.core.common.BaseActivity;
import com.jiahan.fave.core.common.BaseDialogFragment;

public class DialogFragmentUtil {
    public static void displayFragment(@NonNull final BaseActivity activity,
                                       @NonNull final BaseDialogFragment fragment,
                                       @Nullable String tag) {
        if (activity == null || fragment == null) {
            return;
        }
        try {
            FragmentManager fragmentManager = activity.getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(fragment, tag);
            fragmentTransaction.commitNow();
            fragmentManager.executePendingTransactions();
        } catch (Exception e) {
            Logger.logException(e);
            // State Loss. For detail explanation of this issue:
            // http://www.androiddesignpatterns.com/2013/08/fragment-transaction-commit-state-loss.html
        }
    }
}