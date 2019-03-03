package com.alp.usermanager.ui;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import com.alp.usermanager.R;
import com.bumptech.glide.Glide;

public class SquareImageView extends AppCompatImageView {

    public SquareImageView(Context context) {
        super(context);
    }

    public SquareImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = getMeasuredWidth();
        setMeasuredDimension(width, width);
    }

    public void loadUrl(String url) {
        Glide.with(getContext())
                .load(url)
                .placeholder(R.drawable.sample_photo)
                .into(this);
    }
}
