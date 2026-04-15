package com.example.MyPopWindow;
import com.example.activity.R;

import android.app.Activity;  
import android.content.Context;  
import android.graphics.drawable.ColorDrawable;  
import android.view.LayoutInflater;  
import android.view.MotionEvent;  
import android.view.View;  
import android.view.View.OnClickListener;  
import android.view.View.OnTouchListener;  
import android.view.ViewGroup.LayoutParams;   
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;  
import android.widget.TextView;
  
public class exitPopupWindow extends PopupWindow {  
  
  
    private Button ok_bt;
    private Button no_bt;
    private View mMenuView;  
  
    public exitPopupWindow(Activity context,String tip,OnClickListener itemsOnClick) {  
        super(context);  
        LayoutInflater inflater = (LayoutInflater) context  
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);  
        mMenuView = inflater.inflate(R.layout.exit_tip_popwindow, null);  
        TextView tipTV=(TextView) mMenuView.findViewById(R.id.tip);
        tipTV.setText(tip);
        ok_bt=(Button) mMenuView.findViewById(R.id.ok_bt);
        no_bt=(Button) mMenuView.findViewById(R.id.cancel_bt);
        //取消按钮 
        no_bt.setOnClickListener(new OnClickListener() {  
  
            public void onClick(View v) {  
                //销毁弹出框  
                dismiss();  
            }  
        });  
        //设置按钮监听  
        ok_bt.setOnClickListener(itemsOnClick);  
        //设置SelectPicPopupWindow的View  
        this.setContentView(mMenuView);  
        //设置SelectPicPopupWindow弹出窗体的宽  
        this.setWidth(LayoutParams.MATCH_PARENT);  
        //设置SelectPicPopupWindow弹出窗体的高  
        this.setHeight(LayoutParams.MATCH_PARENT);  
        //设置SelectPicPopupWindow弹出窗体可点击  
        this.setFocusable(true);  
        //设置SelectPicPopupWindow弹出窗体动画效果  
 //       this.setAnimationStyle(R.style.PopUpAnimation);  
        //实例化一个ColorDrawable颜色为半透明  
        ColorDrawable dw = new ColorDrawable(0xb0000000);  
        //设置SelectPicPopupWindow弹出窗体的背景         
        this.setBackgroundDrawable(dw);  
        //mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框  
        mMenuView.setOnTouchListener(
        		new OnTouchListener()
        		{        			
					@Override
					public boolean onTouch(View v, MotionEvent event) {
					
						int[] location = new int[2];
						LinearLayout menu_exit=(LinearLayout) mMenuView.findViewById(R.id.menu_exit);
						menu_exit.getLocationOnScreen(location);	        			
						float height=menu_exit.getHeight();
						float y=event.getY();
						if(y>location[1]+height||y<location[1])
						{
							dismiss();
						}
						return false;
					}
        			
        		});
  
    }  
  
}  