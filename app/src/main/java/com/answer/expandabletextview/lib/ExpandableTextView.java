package com.answer.expandabletextview.lib;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.answer.expandabletextview.R;

/**
 * Created by jianhaohong on 5/13/16.
 */
public class ExpandableTextView extends LinearLayout {


    private final static int DEFAULT_TEXT_COLOR = Color.BLACK;
    private final static int DEFAULT_TEXT_SIZE = 25;
    private final static int DEFAULT_MAX_LINE = 5;
    private final static int GRAVITY_LEFT = 0;
    private final static int GRAVITY_CENTER = 1;
    private final static int GRAVITY_RIGHT = 2;

    private TextView textViewContent;
    private TextView textViewShowAll;

    private int lineCount;
    private boolean isShowAll;
    private int maxLine;
    private boolean relayout;
    private String contentText;
    private String labelExpandText;
    private String labelCollapseText;
    private Drawable labelExpandDrawableLeft;
    private Drawable labelCollapseDrawableLeft;

    public ExpandableTextView(Context context) {
        super(context);
        init();
    }

    public ExpandableTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
        TypedArray array = getContext().obtainStyledAttributes(attrs, R.styleable.ExpandableTextView);
        contentText = array.getString(R.styleable.ExpandableTextView_extContentText);
        maxLine = array.getInteger(R.styleable.ExpandableTextView_extContentMaxLine, DEFAULT_MAX_LINE);
        float contentTextSize = array.getDimensionPixelSize(R.styleable.ExpandableTextView_extContentTextSize, DEFAULT_TEXT_SIZE);
        int contentTextColor = array.getColor(R.styleable.ExpandableTextView_extContentTextColor, DEFAULT_TEXT_COLOR);
        labelExpandText = array.getString(R.styleable.ExpandableTextView_extLabelExpandText);
        labelCollapseText = array.getString(R.styleable.ExpandableTextView_extLabelCollapseText);
        float labelTextSize = array.getDimensionPixelSize(R.styleable.ExpandableTextView_extLabelTextSize, DEFAULT_TEXT_SIZE);
        int labelTextColor = array.getColor(R.styleable.ExpandableTextView_extLabelTextColor, DEFAULT_TEXT_COLOR);
        labelExpandDrawableLeft = array.getDrawable(R.styleable.ExpandableTextView_extLabelExpandDrawableLeft);
        labelCollapseDrawableLeft = array.getDrawable(R.styleable.ExpandableTextView_extLabelCollapseDrawableLeft);
        int gravity = array.getInteger(R.styleable.ExpandableTextView_extLabelPosition, GRAVITY_LEFT);
        array.recycle();
        textViewContent.setText(contentText);
        textViewContent.setMaxLines(maxLine);
        textViewContent.setTextSize(TypedValue.COMPLEX_UNIT_PX, contentTextSize);
        textViewContent.setTextColor(contentTextColor);

        textViewShowAll.setText(labelExpandText);
        textViewShowAll.setTextSize(TypedValue.COMPLEX_UNIT_PX, labelTextSize);
        textViewShowAll.setTextColor(labelTextColor);

        textViewShowAll.setCompoundDrawablesWithIntrinsicBounds(labelExpandDrawableLeft, null, null, null);
        LinearLayout.LayoutParams layoutParams = (LayoutParams) textViewShowAll.getLayoutParams();
        switch (gravity) {
            case GRAVITY_LEFT:
                layoutParams.gravity = Gravity.LEFT;
                break;
            case GRAVITY_CENTER:
                layoutParams.gravity = Gravity.CENTER;
                break;
            case GRAVITY_RIGHT:
                layoutParams.gravity = Gravity.RIGHT;
                break;
            default:
                layoutParams.gravity = Gravity.LEFT;
                break;
        }

        textViewShowAll.setLayoutParams(layoutParams);

    }

    private void init() {
        inflate(getContext(), R.layout.layout_of_expandable_text_view, this);
        textViewContent = (TextView) findViewById(R.id.text_view_content);
        textViewShowAll = (TextView) findViewById(R.id.text_view_show_all);
        textViewShowAll.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                showAllText();
            }
        });
    }

    public void showAllText() {
        if (!isShowAll) {
            isShowAll = true;
            textViewContent.setMaxLines(lineCount);
            textViewShowAll.setText(labelCollapseText);
            textViewShowAll.setCompoundDrawablesWithIntrinsicBounds(labelCollapseDrawableLeft, null, null, null);
        } else {
            isShowAll = false;
            textViewContent.setMaxLines(maxLine);
            textViewShowAll.setText(labelExpandText);
            textViewShowAll.setCompoundDrawablesWithIntrinsicBounds(labelExpandDrawableLeft, null, null, null);
        }
    }

    public void setText(String content) {
        //当重新设置了文本后，需要重新测量
        relayout = true;
        textViewContent.setText(content);
        textViewShowAll.setVisibility(TextUtils.isEmpty(content) ? View.GONE : View.VISIBLE);
    }

    public CharSequence getText() {
        return textViewContent.getText();
    }

    public boolean isShowAll() {
        return isShowAll;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        // 如果不需要重新计算，那么直接调用父View的onMeasure并返回
        if (!relayout || getVisibility() == View.GONE) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            return;
        }
        relayout = false;

        //先假设所有情况都正常，展示全文控件不需要显示，显示的文本行数在我们限制内
        textViewShowAll.setVisibility(View.GONE);
        textViewContent.setMaxLines(Integer.MAX_VALUE);

        // 调用父View的onMeasure，这个过程完成后可以得出TextView的具体行数
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        // 如果行数没有超过我们限制的函数，那么直接返回
        if (textViewContent.getLineCount() <= maxLine) {
            return;
        }

        // 如果当前状态不是展开全文的状态，那么限制函数
        if (!isShowAll) {
            textViewContent.setMaxLines(maxLine);
        }
        //显示展开全文文本
        lineCount = textViewContent.getLineCount();
        textViewShowAll.setVisibility(View.VISIBLE);

        // 重新让父View计算当前的大小
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }
}