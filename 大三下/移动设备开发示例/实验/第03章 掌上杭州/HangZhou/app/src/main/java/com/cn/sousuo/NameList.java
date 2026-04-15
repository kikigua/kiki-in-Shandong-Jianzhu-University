package com.cn.sousuo;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.cn.meishi.DetailsActivity;
import com.cn.util.PubMethod;
import com.cn.yiliao.YyActivity;
import com.cn.yule.DtActivity;
import com.cn.zhusu.ZyActivity;

public class NameList
{
	public int n_sum;
	public int no;
	String[] infor=new String[120];
	String[] infor_y=new String[120];
	String[] infor_x=new String[120];
	public String[] s_name=new String[40];
	String[] s_abc=new String[40];
	String[] s_lei=new String[40];
	public NameList()
	{
		getList();
	}

	public void getList()
	{
		  String information=PubMethod.loadFromFile("sousuo.txt");   //文本内容，获取图片名和菜名
		  infor=information.split("\\|");
		  n_sum=infor.length/3;
	        for(int i=0;i<infor.length/3;i++)
	         {
                         s_name[i]=infor[i*3];
                         s_abc[i]=infor[i*3+1];
                         s_lei[i]=infor[i*3+2];
	         }

	        
	}
	
	public  void  getIntent(Context context,String s)
	{
		int num=-1;
        for(int i=0;i<infor.length/3;i++)                 //确定选中项的编号
        {
       
        	if(s_name[i].equals(s))
        	{
        		num=i;
        		break;
        	}
        }
        if(num==-1)
        {
        	Toast.makeText(context, "对不起，暂无此信息！", Toast.LENGTH_LONG).show();
        }
        else
        {
            //判断是类别的   美食类的
            if(s_lei[num].equals("MingCai")||s_lei[num].equals("LaoWeiDao")||s_lei[num].equals("NongJiaCai")||s_lei[num].equals("XiaoChi"))
            {
            	
            	Intent intent=new Intent(context,DetailsActivity.class);
            	intent.putExtra("cai_lm", s_lei[num]);
            	intent.putExtra("dText", s_name[num]);
            	intent.putExtra("imgPath", "food/img/"+s_abc[num]+".jpg");
            	context.startActivity(intent);
            }
            
            //判断类别   医疗类的
            if(s_lei[num].equals("yiliao"))
            {
            	
             	String information=PubMethod.loadFromFile("yiliao/yiliaoname.txt");   //文本内容，获取图片名和菜名
       		    
       		    infor_y=information.split("\\|");
       	        int count=infor_y.length/4;
       	        int[] longitude=new int[count];
       	        int[] latitude=new int[count];
       	        //String[] yy_mc=new String[count];
       	        String[] yy_js=new String[count];
       	        //获取图片和菜名的路径
       	        for(int i=0;i<count;i++)
       	        {
       	        	yy_js[i]=infor_y[i*4+1];	 
       	            longitude[i]=Integer.valueOf(infor_y[i*4+2]);
       		        latitude[i]=Integer.valueOf(infor_y[i*4+3]);
       	        }
       	        
       	        for(int i=0;i<count;i++)
       	        {
       	        	if(yy_js[i]==s_abc[num])
       	        	{
       	        		no=i;
       	        		break;
       	        	}
       	        }
            	
            	Intent intent=new Intent(context,YyActivity.class);
            	intent.putExtra("yy_mc", s_name[num]);
            	intent.putExtra("yy_js", "yiliao/"+s_abc[num]+".txt");
            	intent.putExtra("longitude", longitude[no]);
            	intent.putExtra("latitude", latitude[no]);
            	context.startActivity(intent);
            }
            //判断类别   娱乐
            if(s_lei[num].equals("bar")||s_lei[num].equals("ktv")||s_lei[num].equals("foot")||s_lei[num].equals("club"))
            {
            	
            	String information=PubMethod.loadFromFile("yule/"+s_lei[num]+"/name.txt");    
            	
            	infor_x=information.split("\\|");
       	        int count=infor_x.length/4;    //有多少个品牌
       	        int[] longitude=new int[count];
       	        int[] latitude=new int[count];
       	        String[] jiePath=new String[count];
       	       //获取图片和介绍的路径
                for(int i=0;i<count;i++)
                {
               	 jiePath[i]=infor_x[4*i+1];
               	 longitude[i]=Integer.valueOf(infor_x[i*4+2]);
       		     latitude[i]=Integer.valueOf(infor_x[i*4+3]);
                }
                
                for(int i=0;i<count;i++)
       	        {
       	        	if(jiePath[i]==s_abc[num])
       	        	{
       	        		no=i;
       	        		break;
       	        	}
       	        }
            	
            	Intent intent=new Intent(context,DtActivity.class);
            	intent.putExtra("namePP", s_name[num]);
            	intent.putExtra("imgPath", "yule/"+s_lei[num]+"/"+s_abc[num]+".jpg");
            	intent.putExtra("jiePath", "yule/"+s_lei[num]+"/"+s_abc[num]+".txt");
            	intent.putExtra("longitude", longitude[no]);
            	intent.putExtra("latitude", latitude[no]);
            	context.startActivity(intent);
            }
            
            //判断类别   住宿
            if(s_lei[num].equals("zhusu"))
            {
            	Intent intent=new Intent(context,ZyActivity.class);
            	intent.putExtra("namePP", s_name[num]);
            	intent.putExtra("imgPath", "zhusu/"+s_abc[num]+".jpg");
            	intent.putExtra("jiePath", "zhusu/"+s_abc[num]+".txt");
            	context.startActivity(intent);
            }
        }		
	}
	
	
	
	
}