# Very long text support for Android 
[![](https://jitpack.io/v/metalurgus/LongTextView.svg)](https://jitpack.io/#metalurgus/LongTextView) [![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-LongTextView-brightgreen.svg?style=flat)](http://android-arsenal.com/details/1/3920) [![Build Status](https://travis-ci.org/metalurgus/LongTextView.svg?branch=master)](https://travis-ci.org/metalurgus/LongTextView)

## Features
- split text into multiple parts, shown as list items in ListView
- select max number of lines in a single item
- select text size, color and gravity same way as in a regular `TextView`

## How to use?
Add dependency (`LongTextView` is available in jitpack.io):
```
repositories {
    maven { url "https://jitpack.io" }
}
dependencies {
    compile 'com.github.metalurgus:LongTextView:1.0'
}
```
In your `layout.xml` file:
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
In your `Java` code
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
