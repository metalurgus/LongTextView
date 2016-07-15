# Very long text support for Android

## Features
- split text into multiple parts, shown as list items in ListView
- select max number of lines in a single item
- select text size, color and gravity same way as in a regular `TextView`

## How to use?

```xml
<com.metalurgus.longtextview.LongTextView 
        xmlns:app="http://schemas.android.com/apk/res-auto"
        android:id="@+id/view"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:gravity="left|top"
        app:maxLinesPerItem="2"
        app:text="@string/long_text"
        app:textColor="#ff0000"
        app:textSize="5dp" />

```

```java
@Override
protected void onCreate(Bundle savedInstanceState) {
super.onCreate(savedInstanceState);

    LongTextView longTextView = (LongTextView) findViewById(R.id.view);
    longTextView.setText(getString(R.string.long_text));
}
```
## License

    See LICENSE file (GNU GENERAL PUBLIC LICENSE)
