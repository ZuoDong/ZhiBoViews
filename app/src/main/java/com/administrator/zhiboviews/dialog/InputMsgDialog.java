package com.administrator.zhiboviews.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;

import com.administrator.zhiboviews.R;

/**
 * Created by Administrator on 2016/10/19.
 */

public class InputMsgDialog extends Dialog {

    private final Context context;

    public InputMsgDialog(Context context){
        super(context,R.style.MyTheme_Dialog);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        View view = View.inflate(context,R.layout.inputmsg_dialog,null);
        EditText et_content = (EditText) view.findViewById(R.id.et_content);

        Window window = getWindow();
        window.getDecorView().setPadding(0, 0, 0, 0);//取消边框
        setCanceledOnTouchOutside(true);//点击取消
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(attributes);
        window.setGravity(Gravity.BOTTOM);
        setContentView(view);
    }

}
