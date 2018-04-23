package com.administrator.zhiboviews.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;

import com.administrator.zhiboviews.R;

/**
 * Created by Administrator on 2016/3/8.
 */

public class ApplicationDialog extends Dialog {

    private final Context context;

    public ApplicationDialog(Context context){
        super(context,R.style.MyTheme_Dialog);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {
        View view = View.inflate(context,R.layout.application_dialog,null);

        Window window = getWindow();
        window.getDecorView().setPadding(0, 0, 0, 0);//取消边框
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width = WindowManager.LayoutParams.MATCH_PARENT;
//        window.setType(WindowManager.LayoutParams.TYPE_APPLICATION_ATTACHED_DIALOG);
        window.setAttributes(attributes);
        window.setGravity(Gravity.CENTER);
        setContentView(view);
    }

}
