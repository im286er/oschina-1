package com.liang.oschina.ui.activitys;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import com.liang.oschina.R;
import com.liang.oschina.utils.UnitConversionUtils;

import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/7/6 0006.
 */
public class QuickOptionView {
    public Dialog mQuickView;
    private Context context ;

    @BindViews({R.id.quick_text,R.id.quick_album,R.id.quick_photo,
    R.id.quick_vioce,R.id.quick_scan,R.id.quick_note})
    public RadioButton[] mRadioButton ;

    public  QuickOptionView(Context context){
        this.context = context ;
        mQuickView = new Dialog(context,R.style.DialogTheme);
    }


    public void showView() {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        params.topMargin = UnitConversionUtils.dip2px(context,10);
        params.gravity = Gravity.BOTTOM;
        View view = LayoutInflater.from(context).inflate(
                R.layout.plus_dialog_layout,null);
        view.setLayoutParams(params);
        mQuickView.setContentView(view);
        ButterKnife.bind(this, view);
        setDiaglogLocation();
        //mQuickView.onActionModeStarted(ActionMode.DEFAULT_HIDE_DURATION);
       //失去焦点之后dismiss
        mQuickView.setCanceledOnTouchOutside(true);
        mQuickView.show();
    }

    public void dismissView() {
        if(mQuickView !=null){
            mQuickView.cancel();
            mQuickView.dismiss();
        }
    }

    @OnClick({R.id.quick_text,R.id.quick_album,R.id.quick_photo, R.id.quick_vioce,R.id.quick_scan,R.id.quick_note})
    public void onClick(View view){
        if(clickCallBack != null){
            clickCallBack.onClickBack(view.getId());
        }
    }

    //回调接口
    private ClickCallBack clickCallBack;
    public void setClickCallBack(ClickCallBack click){
        this.clickCallBack = click;
    }
    public interface ClickCallBack{
       public void onClickBack(int id);
    }


    private void setDiaglogLocation(){
        Window window = mQuickView.getWindow();
        // 可以在此设置显示动画
        WindowManager.LayoutParams wl = window.getAttributes();
        wl.x = 0;
        MainActivity activity = (MainActivity) context;
        wl.y = activity.getWindowManager().getDefaultDisplay().getHeight();
       // 以下这两句是为了保证按钮可以水平满屏
        wl.width = ViewGroup.LayoutParams.MATCH_PARENT;
        wl.height = ViewGroup.LayoutParams.WRAP_CONTENT;
         // 设置显示位置
        mQuickView.onWindowAttributesChanged(wl);
    }


}
