package com.cn.hangzhou;

import android.app.Activity;
import android.app.ActivityGroup;
import android.app.LocalActivityManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RadioButton;


@SuppressWarnings("deprecation")
public abstract class MZActivityGroup extends ActivityGroup implements
CompoundButton.OnCheckedChangeListener{
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initRadioBtns();
    }
    
   
    private ViewGroup container;
    private LocalActivityManager localActivityManager;
   
    abstract protected ViewGroup getContainer();
   
    protected void initRadioBtn(int id)
    {
    	RadioButton btn = (RadioButton) findViewById(id);
        btn.setOnCheckedChangeListener(this);
    }
    
    
    abstract protected void initRadioBtns();
    
    private Intent initIntent(Class<?> cls)
    {
        return new Intent(this,    cls).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    }
   
    protected void setContainerView(String activityName, Class<?> activityClassTye)
    {
        if(null == localActivityManager)
        {
            localActivityManager = getLocalActivityManager();
        }
        if(null == container)
        {
            container = getContainer();
        }
        
        container.removeAllViews();
		Activity contentActivity = localActivityManager.getActivity(activityName);
        if((activityName.equals("MapNavigateDemoA5ctivity"))||(activityName.equals("ShouYeActivity")))
        {
        	localActivityManager.startActivity(activityName, initIntent(activityClassTye));
        }else if (null == contentActivity) 
        {
            localActivityManager.startActivity(activityName, initIntent(activityClassTye));
        }
       
        container.addView(
                localActivityManager.getActivity(activityName).getWindow().getDecorView(),
                new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.FILL_PARENT));
    }
}