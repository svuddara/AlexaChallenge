package com.visa.androidauthentication.widgets;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.Button;

/**
 * Created by frahman on 8/16/15.
 */
public class VisaButton extends Button {
    public VisaButton(Context context) {
        super(context);
        init();
    }

    public VisaButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public VisaButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public VisaButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "MyriadPro-Regular.ttf");
        setTypeface(tf);
    }
}
