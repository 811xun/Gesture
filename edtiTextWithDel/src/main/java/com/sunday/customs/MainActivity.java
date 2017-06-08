package com.sunday.customs;


import android.app.Activity;
import android.graphics.Rect;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import com.example.customs.R;

import static android.content.ContentValues.TAG;

public class MainActivity extends Activity implements TextWatcher, View.OnTouchListener {
    private EditText mEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEditText = (EditText) findViewById(R.id.tv);
        mEditText.addTextChangedListener(this);
        mEditText.setOnTouchListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        if (!TextUtils.isEmpty(charSequence.toString())) {
            mEditText.setCompoundDrawablesWithIntrinsicBounds(null, null, getResources().getDrawable(R.drawable.delete), null);
        } else {
            mEditText.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {
    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            int eventX = (int) event.getRawX();
            Log.d(TAG, "[xzc] onTouch: " + eventX + "\n" + (int) event.getX());
            int eventY = (int) event.getRawY();
            Log.e(TAG, "eventX = " + eventX + "; eventY = " + eventY);
            Rect rect1 = new Rect();
            mEditText.getGlobalVisibleRect(rect1);
            Log.d(TAG, "[xzc] onTouch: " + rect1.left + "," + rect1.top + "," + rect1.right + "," + rect1.bottom);
            Log.d(TAG, "[xzc] onTouch: " + mEditText.getLeft() + "," + mEditText.getTop() + "," + mEditText.getRight() + "," + mEditText.getBottom());
            rect1.left = rect1.right - 50;
            if (rect1.contains(eventX, eventY))
                mEditText.setText("");

            Rect rect2 = new Rect();
            mEditText.getLocalVisibleRect(rect1);
            Log.d(TAG, "[xzc] onTouch: " + rect2.left + "," + rect2.top + "," + rect2.right + "," + rect2.bottom);
        }
        return super.onTouchEvent(event);
    }
}

