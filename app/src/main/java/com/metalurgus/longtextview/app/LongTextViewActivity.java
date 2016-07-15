package com.metalurgus.longtextview.app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.metalurgus.longtextview.LongTextView;

public class LongTextViewActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_longtextview);
        LongTextView longTextView = (LongTextView) findViewById(R.id.view);
        longTextView.setText(getString(R.string.long_text));
    }
}
