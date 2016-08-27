package com.mash.pig.bobpercent.app.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.os.Build;
import android.text.Layout;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mash.pig.bobpercent.R;

/**
 * Created by bigstark on 2016. 8. 28..
 */

public class FontTextView extends TextView {

    private boolean isWrapContent = false;

    private int lineCount = 0;
    private int lineHeight = 0;

    public FontTextView(Context context) {
        this(context, null);
    }

    public FontTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FontTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        FontHelper fontHelper = FontHelper.getInstance();
        setTypeface(fontHelper.getRegular());
        setLineHeight(getLineHeightByAttrs(attrs));
    }

    /**
     * @return attrs에서부터 lineHeight를 읽어 반환한다. attrs이 null이면 0을 반환한다.
     */
    private int getLineHeightByAttrs(AttributeSet attrs) {
        if (attrs == null) {
            return 0;
        }

        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.BobPercentFontFont);
        int lineHeight = ta.getDimensionPixelSize(R.styleable.BobPercentFontFont_lineHeight, 0);
        ta.recycle();

        return lineHeight;
    }

    public void setLineHeight(int lineHeight) {
        this.lineHeight = lineHeight;
        super.setLineSpacing(lineHeight, 1);
    }

    /**
     * TextView의 Layout이 onMeasure에서 일어난다. onMeasure 후에 lineCount를 가져올 수 있다.
     * lineCount가 변경되었고, height가 WRAP_CONTENT라면 height를 지정해준다.
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (getLayoutParams().height == ViewGroup.LayoutParams.WRAP_CONTENT) {
            isWrapContent = true;
        }

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        if (lineHeight == 0) {
            return;
        }

        // Lollipop 이상부터는 맨 마지막 줄에 lineHeight가 붙지 않는다.
        if (Build.VERSION.SDK_INT > 19) {
            return;
        }

        if (isWrapContent) {
            int lineCount = getLayout().getLineCount();
            onLineCountChanged(lineCount);
        }

    }

    /**
     * lineCount에 따라 height를 정해준다. (height가 WRAP_CONTENT일 때만)
     * padding top, bottom이 존재한다면 추가해준다.
     */
    public void onLineCountChanged(int lineCount) {
        this.lineCount = lineCount;

        Layout layout = getLayout();

        Rect bound = new Rect();
        layout.getLineBounds(lineCount - 1, bound);
        int paddingTopAndBottom = getPaddingTop() + getPaddingBottom();

        getLayoutParams().height = bound.bottom - lineHeight + paddingTopAndBottom;
        requestLayout();
    }

}
