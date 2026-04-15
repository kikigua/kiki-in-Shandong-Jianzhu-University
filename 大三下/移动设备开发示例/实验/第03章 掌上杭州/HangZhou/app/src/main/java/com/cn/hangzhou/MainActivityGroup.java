package com.cn.hangzhou;

import com.cn.hangzhou.R;
import com.cn.shezhi.ShiZhiActivity;
import com.cn.sousuo.SouSuoActivity;
import com.cn.util.FontManager;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;

public class MainActivityGroup extends MZActivityGroup implements OnClickListener{
	
	RadioButton[] tv; 
    private static final String CONTENT_ACTIVITY_NAME_0 = "ShouYeActivity";
    private static final String CONTENT_ACTIVITY_NAME_1 = "SouSuoAcyivity";
    private static final String CONTENT_ACTIVITY_NAME_2 = "ShiZhiActivity";
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.main);
        super.onCreate(savedInstanceState);
      //用自定义的字体方法
        FontManager.changeFonts(FontManager.getContentView(this),this);
        tv= new RadioButton[3];   
        initRadioBtns();
        ((RadioButton)findViewById(R.id.Button01)).setChecked(true);
        ((RadioButton)findViewById(R.id.Button01)).setBackgroundResource(R.drawable.bt_home2);
        ((RadioButton)findViewById(R.id.Button02)).setBackgroundResource(R.drawable.bt_search);
        ((RadioButton)findViewById(R.id.Button03)).setBackgroundResource(R.drawable.bt_set);
        tv[0]=(RadioButton) findViewById(R.id.Button01);
        tv[1]=(RadioButton) findViewById(R.id.Button02);
        tv[2]=(RadioButton) findViewById(R.id.Button03);
        
        for(int i=0;i<tv.length;i++)
        {
        	tv[i].setOnClickListener(this);
        }
    }
    protected void dialog() { 
        AlertDialog.Builder builder = new Builder(MainActivityGroup.this); 
        builder.setMessage("您确定要退出吗?"); 
//        builder.setTitle(" 提示"); 
        builder.setPositiveButton("确认", 
                new android.content.DialogInterface.OnClickListener() { 
                    @Override
                    public void onClick(DialogInterface dialog, int which) { 
                        dialog.dismiss(); 
                        MainActivityGroup.this.finish(); 
                    } 
                }); 
        builder.setNegativeButton("取消", 
                new android.content.DialogInterface.OnClickListener() { 
                    @Override
                    public void onClick(DialogInterface dialog, int which) { 
                        dialog.dismiss(); 
                    } 
                }); 
        builder.create().show(); 
    } 
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) { 
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0)
        { 
            dialog(); 
            return false; 
        } 
        return false; 
    }
    protected ViewGroup getContainer() 
    {
        return (ViewGroup) findViewById(R.id.container);
    }
    
   
    protected void initRadioBtns() 
    {
        initRadioBtn(R.id.Button01);
        initRadioBtn(R.id.Button02);
        initRadioBtn(R.id.Button03);
    }
    
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) 
    {
        if (isChecked) 
        {
            switch (buttonView.getId()) 
            {
            case R.id.Button01:
            {
                setContainerView(CONTENT_ACTIVITY_NAME_0, ShouYeActivity.class);
            }
            break;
            case R.id.Button02:
                setContainerView(CONTENT_ACTIVITY_NAME_1, SouSuoActivity.class);
                break;
            case R.id.Button03:
                setContainerView(CONTENT_ACTIVITY_NAME_2, ShiZhiActivity.class);
                break;
            default:
                break;
            }
        }
    }
	@Override
	public void onClick(View v) 
	{
		
			
		if(v.getId()==R.id.Button01)
		{
			tv[0].setBackgroundResource(R.drawable.bt_home2);
			tv[1].setBackgroundResource(R.drawable.bt_search);
			tv[2].setBackgroundResource(R.drawable.bt_set);
		}
		
		if(v.getId()==R.id.Button02)
		{
			tv[1].setBackgroundResource(R.drawable.bt_search2);
			tv[0].setBackgroundResource(R.drawable.bt_home);
			tv[2].setBackgroundResource(R.drawable.bt_set);
			//vp.setCurrentItem(1);
		}
		if(v.getId()==R.id.Button03)
		{
			tv[2].setBackgroundResource(R.drawable.bt_set2);
			tv[0].setBackgroundResource(R.drawable.bt_home);
			tv[1].setBackgroundResource(R.drawable.bt_search);
			//vp.setCurrentItem(2);
	    }
	  
	}
} 