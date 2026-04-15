package com.cn.util;

import android.app.Activity;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class FontManager
{	
	public static Typeface tf =null;
	//初始化Typeface
	public static void init(Activity act,String xx)
	{
		if(tf==null)
		{
		    tf= Typeface.createFromAsset(act.getAssets(),  "fonts/kaiti.ttf");
		}else{
		if(xx.equals("kaiti"))
		{
			if(tf!=Typeface.createFromAsset(act.getAssets(),  "fonts/kaiti.ttf"))
			{
			   tf= Typeface.createFromAsset(act.getAssets(),  "fonts/kaiti.ttf"); 
			  // System.out.println("==================楷体========================");
			}
		}
		if(xx.equals("songti"))
		{
			if(tf!=Typeface.createFromAsset(act.getAssets(),  "fonts/songti.ttf"))
			{
			   tf= Typeface.createFromAsset(act.getAssets(),  "fonts/songti.ttf");  
			}
		}
		if(xx.equals("lishu"))
		{
			if(tf!=Typeface.createFromAsset(act.getAssets(),  "fonts/lishu.ttf"))
			{
			    tf= Typeface.createFromAsset(act.getAssets(),  "fonts/lishu.ttf");  
			}
		}
		if(xx.equals("fangzhen"))
		{
			if(tf!=Typeface.createFromAsset(act.getAssets(),  "fonts/fangzhen.ttf"))
			{
			    tf= Typeface.createFromAsset(act.getAssets(),  "fonts/fangzhen.ttf");  
			}
		}
		if(xx.equals("huawen"))
		{
			if(tf!=Typeface.createFromAsset(act.getAssets(),  "fonts/huawen.ttf"))
			{
			    tf= Typeface.createFromAsset(act.getAssets(),  "fonts/huawen.ttf");  
			}
		}
		if(xx.equals("katong"))
		{
			if(tf!=Typeface.createFromAsset(act.getAssets(),  "fonts/newfont.ttf"))
			{
			    tf= Typeface.createFromAsset(act.getAssets(),  "fonts/newfont.ttf");  
			}
		}
	 }	
		
	} 
	//转换字体
	public static void changeFonts(ViewGroup root,Activity act) 
	{ 

        for (int i = 0; i < root.getChildCount(); i++) 
        { 
            View v = root.getChildAt(i);  
            if (v instanceof TextView) 
            { 
                ((TextView) v).setTypeface(tf);  
            } 
            else if (v instanceof Button) 
            {  
                ((Button) v).setTypeface(tf);  
            } 
            else if (v instanceof EditText) 
            {  
                ((EditText) v).setTypeface(tf);  
            } 
            else if (v instanceof ViewGroup) 
            {  
                changeFonts((ViewGroup) v, act); 
            }
       }  
	}  
	/** * Get root content view. * @param act * @return */ 
	public static ViewGroup getContentView(Activity act)
	{ 
		ViewGroup systemContent = (ViewGroup)act.getWindow().getDecorView().findViewById(android.R.id.content); 
		ViewGroup content = null;
		if(systemContent.getChildCount() > 0 && systemContent.getChildAt(0) instanceof ViewGroup)
		{ 
			content = (ViewGroup)systemContent.getChildAt(0); 
		} 
		return content; 
	}
}

