package com.example.somasur.comicviewer;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;


public class QustomDialogBuilder extends AlertDialog.Builder{

    /** The custom_body layout */
    private View mDialogView;

    /** optional dialog title layout */
    private TextView mTitle;
    /** optional alert dialog image */
    /** optional message displayed below title if title exists*/
    private TextView mMessage;
    /** The colored holo divider. You can set its color with the setDividerColor method */
    private View mDivider;

    public QustomDialogBuilder(Context context) {
        super(context);

        mDialogView = View.inflate(context, R.layout.dialog, null);
        setView(mDialogView);

        mTitle = mDialogView.findViewById(R.id.alertTitle);
        mMessage = mDialogView.findViewById(R.id.message);
        mDivider = mDialogView.findViewById(R.id.titleDivider);
    }

    /**
     * Use this method to color the divider between the title and content.
     * Will not display if no title is set.
     *
     * @param colorString for passing "#ffffff"
     */
    public QustomDialogBuilder setDividerColor(String colorString) {
        mDivider.setBackgroundColor(Color.parseColor(colorString));
        return this;
    }

    @Override
    public QustomDialogBuilder setTitle(CharSequence text) {
        mTitle.setText(text);
        return this;
    }

    public QustomDialogBuilder setTitleColor(String colorString) {
        mTitle.setTextColor(Color.parseColor(colorString));
        return this;
    }

    @Override
    public QustomDialogBuilder setMessage(CharSequence text) {
        mMessage.setText(text);
        return this;
    }

//    public QustomDialogBuilder setCustomView(int resId, Context context) {
//        View customView = View.inflate(context, resId, null);
//        ((FrameLayout)mDialogView.findViewById(R.id.customPanel)).addView(customView);
//        return this;
//    }
//
//    @Override
//    public AlertDialog show() {
//        if (mTitle.getText().equals("")) mDialogView.findViewById(R.id.topPanel).setVisibility(View.GONE);
//        return super.show();
//    }

}
