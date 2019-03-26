package com.bkz.demo.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.TextView;

public class ViewCreator {

    public TextView getTextView(Context context, String text, int text_color, int text_size) {
        final TextView textView = new TextView(context);
        textView.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, text_size);
        textView.setText(text);
        textView.setSingleLine(true);
        textView.setPadding(5, 0, 5, 0);
        textView.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        textView.setSelected(true);
        textView.setTextColor(ContextCompat.getColor(context, text_color));
        textView.setGravity(Gravity.CENTER);
        return textView;
    }
}
