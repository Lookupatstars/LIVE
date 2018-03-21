package com.example.yinwei.live.editprofile;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.yinwei.live.R;

/**
 * Created by yinwei on 2018/3/20.
 */

//将相同的布局抽象出成一个linearLayout，
    //可以减少xml里的代码量
public class EditProfile extends LinearLayout {


    private ImageView mIconView;
    private TextView mKeyView;
    private TextView mValueView;
    private ImageView mRightArrowView;
    
    public EditProfile(Context context) {
        super(context);
        init();

    }
    public EditProfile(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();

    }
    public EditProfile(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();

    }
    //初始化把自己的类和控件连接起来
    private void init() {
        //需要把View_profile_edit.xml布局Inflate一下
        LayoutInflater.from(getContext()).inflate(R.layout.view_profile_edit,this,true);
        findAllViews();
    }
    //找到这些views
    private void findAllViews() {
        mIconView = (ImageView) findViewById(R.id.profile_icon);
        mKeyView = (TextView) findViewById(R.id.profile_key);
        mValueView = (TextView) findViewById(R.id.profile_value);
        mRightArrowView = (ImageView) findViewById(R.id.right_arrow);
    }

    public void set(int iconResId, String key, String value) {
        mIconView.setImageResource(iconResId);
        mKeyView.setText(key);
        mValueView.setText(value);
    }

    public void updateValue(String value) {
        mValueView.setText(value);
    }

    public String getValue() {
        return mValueView.getText().toString();
    }
        //取消箭头的显示
    protected void disableEdit() {
        mRightArrowView.setVisibility(GONE);
    }
}