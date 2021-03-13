package com.jiahan.fave.core.utils;

import android.text.TextUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class DateTimeUtils {
    public static String formatRFC3339DateTimeForDisplay(final String rfc3339DateTime, final String displayFormat) {
        final SimpleDateFormat displayDateFormat = new SimpleDateFormat(displayFormat, Locale.getDefault());
        final Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(parseRFC3339DateTime(rfc3339DateTime));
        return displayDateFormat.format(calendar.getTime());
    }

    public static long parseRFC3339DateTime(final String rfc3339DateTime) {
        try {
            final com.jiahan.fave.core.utils.RFC3339DateTime dateTime = parseRFC3339DateTimeInternal(rfc3339DateTime);
            return dateTime == null ? 0L : dateTime.getValue();
        } catch (final NumberFormatException e) {
            // Nothing we can do
        }
        return 0L;
    }

    private static com.jiahan.fave.core.utils.RFC3339DateTime parseRFC3339DateTimeInternal(final String rfc3339DateTime) throws NumberFormatException {
        if (TextUtils.isEmpty(rfc3339DateTime)) {
            return null;
        }
        return com.jiahan.fave.core.utils.RFC3339DateTime.parseRfc3339(rfc3339DateTime);
    }
}