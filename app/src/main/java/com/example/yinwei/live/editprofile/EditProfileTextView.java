package com.example.yinwei.live.editprofile;

import android.content.Context;
import android.util.AttributeSet;

/**
 * Created by yinwei on 2018/3/20.
 */

//用来展示没有箭头的那几项，
    //下面不能编辑的部分
public class EditProfileTextView extends EditProfile {

    public EditProfileTextView(Context context) {
        super(context);
        //直接调用父类写好的取消箭头的显示函数
        disableEdit();
    }

    public EditProfileTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        disableEdit();
    }

    public EditProfileTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        disableEdit();
    }
}
