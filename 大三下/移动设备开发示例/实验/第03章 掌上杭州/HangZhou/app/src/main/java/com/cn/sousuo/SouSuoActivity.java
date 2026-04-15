package com.cn.sousuo;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cn.hangzhou.R;
import com.cn.util.FontManager;

public class SouSuoActivity extends Activity implements View.OnClickListener {
	private String[] totalKeys = null;
	private  String[] key_words=new String[15];
	protected static final String TAG = "SearchPage";
	
	private KeywordsView showKeywords = null;
	private LinearLayout searchLayout = null;
	
	/**
	 * 搜索栏
	 */

	private GestureDetector mggd;
	/**
	 * 判断是在外页面还是内页面
	 */
	private boolean isOutter;
	AutoCompleteTextView  et_ss;     //搜索框
	NameList nl;       //获取列表
	
	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.sousuo);
		searchLayout = (LinearLayout) this.findViewById(R.id.searchContent);
		 //用自定义的字体方法
        FontManager.changeFonts(FontManager.getContentView(this),this);
		showKeywords = (KeywordsView) this.findViewById(R.id.word);
		showKeywords.setDuration(2000l);
		showKeywords.setOnClickListener(this);
		this.mggd =new GestureDetector(new Mygdlinseter());
		showKeywords.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
			    return mggd.onTouchEvent(event); //注册点击事件
			}
		});
		isOutter = true;
		
		handler.sendEmptyMessage(Msg_Start_Load);

          nl=new NameList();  //一开始就拿到列表
          String[] autoStrs=new String[nl.n_sum];
          
	        for(int i=0;i<nl.n_sum;i++)
	         {
	        	autoStrs[i]=nl.s_name[i];
	         }

		//搜索框
	    et_ss=(AutoCompleteTextView)this.findViewById(R.id.search_Keywords);
		 ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				 android.R.layout.simple_dropdown_item_1line,autoStrs); 
		 et_ss.setAdapter(adapter); 
	    
		//清除搜素框
		ImageView clear= (ImageView)this.findViewById(R.id.ivSButtonClear);
		clear.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				et_ss.setText("");
			}
		});
		
		//搜索按钮
		ImageButton  ib_search=(ImageButton)this.findViewById(R.id.search_button);
		ib_search.setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						String s_txt=et_ss.getText().toString();
						nl.getIntent(SouSuoActivity.this, s_txt);
						et_ss.setText("");
					}
				}
				);
		
	}
	private String[]getRandomArray(){
		if (totalKeys != null && totalKeys.length > 0) {
			String[] keys = new String[15];
			List<String> ks = new ArrayList<String>();
			for (int i = 0; i < totalKeys.length; i++) {
				ks.add(totalKeys[i]);
			}
			for (int i = 0; i < keys.length; i++) {
				int k = (int) (ks.size() * Math.random());
				keys[i] = ks.remove(k);
				if(keys[i] == null)
					System.out.println("nulnulnulnulnul");	
			}
			System.out.println("result's length = "+keys.length);
			return keys;
		}                                                                           
		return 	new String[]{"苏西黄酒吧","叫花童子鸡","斩鱼圆","东坡肉","西湖醋鱼","龙井虾仁",
				"杭州酱鸭","浙江医院","杭州市中医医院","西雅图音乐酒吧","7天连锁酒店" ,"新概念量贩KTV",
						"香格里拉酒店","金麦KTV","彩乐台球俱乐部"};
	}

	private static final int Msg_Start_Load = 0x0102;
	private static final int Msg_Load_End = 0x0203;
	
	private LoadKeywordsTask task = null;
	
	private Handler handler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			switch(msg.what){
			case Msg_Start_Load:

				
					task = new LoadKeywordsTask();
					new Thread(task).start();
				
				break;
			case Msg_Load_End:
				showKeywords.rubKeywords();
				feedKeywordsFlow(showKeywords, key_words);
				showKeywords.go2Shwo(KeywordsView.ANIMATION_IN);
				break;
			}
			
		}
	};
	private class LoadKeywordsTask implements Runnable{
		@Override
		public void run() {
			try {
				
				key_words = getRandomArray();
				if(key_words.length>0)
					handler.sendEmptyMessage(Msg_Load_End);
			} catch (Exception e) {
			}
		}
	}
	private void feedKeywordsFlow(KeywordsView keyworldFlow, String[] arr) {
		for (int i = 0; i < KeywordsView.MAX; i++) {
			String tmp = arr[i];
			keyworldFlow.feedKeyword(tmp);
		}
	}

	

	class Mygdlinseter implements OnGestureListener {
		@Override
		public boolean onDown(MotionEvent e) {
			return true;
		}
		@Override
		public void onShowPress(MotionEvent e) {
		}
		@Override
		public boolean onSingleTapUp(MotionEvent e) {
			return false;
		}
		@Override
		public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
				float distanceY) {
			return false;
		}
		@Override
		public void onLongPress(MotionEvent e) {
		}
		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
				float velocityY) {
			if (e2.getX() - e1.getX() > 100) { //右滑
				key_words = getRandomArray();
				showKeywords.rubKeywords();
				feedKeywordsFlow(showKeywords, key_words);
				showKeywords.go2Shwo(KeywordsView.ANIMATION_OUT);
				return true;
			}
			if (e2.getX() - e1.getX() < -100) {//左滑
				key_words = getRandomArray();
				showKeywords.rubKeywords();
				feedKeywordsFlow(showKeywords, key_words);
				showKeywords.go2Shwo(KeywordsView.ANIMATION_IN);
				return true;
			}
			if (e2.getY() - e1.getY() < -100) {//上滑
				key_words = getRandomArray();
				showKeywords.rubKeywords();
				feedKeywordsFlow(showKeywords, key_words);
				showKeywords.go2Shwo(KeywordsView.ANIMATION_IN);
				return true;
			}
			if (e2.getY() - e1.getY() > 100) {//下滑
				key_words = getRandomArray();
				showKeywords.rubKeywords();
				feedKeywordsFlow(showKeywords, key_words);
				showKeywords.go2Shwo(KeywordsView.ANIMATION_OUT);
				return true;
			}
			return false;
		}
	}
 

	@Override
	public void onClick(View v) {
		System.out.println("V"+v);
		
		//Toast.makeText(this, "选中的内容是：" + ((TextView) v).getText().toString(), 1).show();
		nl.getIntent(SouSuoActivity.this, ((TextView) v).getText().toString());
	
	}

	
	
	/**
	 * 处理返回按键事件
	 */
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		
		switch(keyCode){
		case KeyEvent.KEYCODE_BACK:
			
			if(!isOutter){
				isOutter = true;
				searchLayout.removeAllViews();
				searchLayout.addView(showKeywords);
				/**
				 * 将自身设为不可动作
				 */
			
				/**
				 * 将搜索栏清空
				 */
				
			}else{
				dialog();
			/**
			 * 执行返回按键操作
			 */
			
			}
			
			return true;
		}
		
		return super.onKeyDown(keyCode, event);
	}
	 protected void dialog() { 
	        AlertDialog.Builder builder = new Builder(SouSuoActivity.this); 
	        builder.setMessage("您确定要退出吗?"); 
//	        builder.setTitle(" 提示"); 
	        builder.setPositiveButton("确认", 
	                new android.content.DialogInterface.OnClickListener() { 
	                    @Override
	                    public void onClick(DialogInterface dialog, int which) { 
	                        dialog.dismiss(); 
	                        SouSuoActivity.this.finish(); 
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
}
