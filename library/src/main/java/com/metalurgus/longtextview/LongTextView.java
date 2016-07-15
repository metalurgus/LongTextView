package com.metalurgus.longtextview;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by Vlad on 15.07.2016.
 */
public class LongTextView extends ListView {
    private int textColor;
    private float textSize;
    private int gravity;
    private int maxLines;

    public LongTextView(Context context) {
        super(context);
        init(null);
    }

    public LongTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public LongTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public LongTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        setDivider(null);
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.LongTextView);
        String text = a.getString(R.styleable.LongTextView_text);
        int textColor = a.getColor(R.styleable.LongTextView_textColor, Color.BLACK);
        float textSize = a.getDimensionPixelSize(R.styleable.LongTextView_textSize, 15);
        int gravity = a.getInt(R.styleable.LongTextView_gravity, Gravity.LEFT | Gravity.TOP);
        int maxLines = a.getInt(R.styleable.LongTextView_maxLines, 5);

        setTextColor(textColor);
        setTextSize(textSize);
        setGravity(gravity);
        setMaxLines(maxLines);

        if (!TextUtils.isEmpty(text)) {
            setText(text);
        }
    }

    public void setText(String text) {
        setAdapter(new LongTextViewAdapter(getContext(), text));
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
        checkAndNotify();
    }

    public void setTextSize(float textSize) {
        this.textSize = textSize;
        checkAndNotify();
    }

    public void setGravity(int gravity) {
        this.gravity = gravity;
        checkAndNotify();
    }

    public void setMaxLines(int maxLines) {
        this.maxLines = maxLines;
        checkAndNotify();
    }

    private void checkAndNotify() {
        if (getAdapter() instanceof BaseAdapter) {
            ((BaseAdapter) getAdapter()).notifyDataSetChanged();
        }
    }

    /**
     * Adapter, which handles the text wrapping into multiple list items
     */
    private class LongTextViewAdapter extends BaseAdapter {

        private final Context context;
        private final String text;
        private final TextView textView;
        private int itemsCount = 0;
        private StaticLayout staticLayout;


        /**
         * @param context - Context to be used across adapter
         * @param text    - source text to be wrapped
         */
        public LongTextViewAdapter(Context context, String text) {
            this.context = context;
            this.text = text;

            textView = new TextView(context);
            textView.setMaxLines(maxLines);
            textView.setIncludeFontPadding(false);
            textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
            textView.setTextColor(textColor);
            textView.setGravity(gravity);

            new Handler().post(new Runnable() {
                @Override
                public void run() {
                    init();
                }
            });

        }

        private void init() {
            staticLayout = new StaticLayout(
                    text,
                    textView.getPaint(),
                    getWidth(),
                    Layout.Alignment.ALIGN_NORMAL,
                    1,
                    0,
                    true
            );
            itemsCount = staticLayout.getLineCount() / maxLines;
            if (staticLayout.getLineCount() % maxLines > 0) {
                itemsCount++;
            }
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return itemsCount;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView textView = (TextView) convertView;
            if (textView == null) {
                textView = new TextView(context);
                textView.setMaxLines(maxLines);
                textView.setIncludeFontPadding(false);
                textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
                textView.setTextColor(textColor);
                textView.setGravity(gravity);
            }
            int startChar = staticLayout.getLineStart(position * maxLines);
            int endChar;
            if ((position + 1) * maxLines >= staticLayout.getLineCount()) {
                endChar = text.length();
            } else {
                endChar = staticLayout.getLineStart((position + 1) * maxLines);
            }
            textView.setText(text.substring(startChar, endChar));
            return textView;
        }
    }
}
