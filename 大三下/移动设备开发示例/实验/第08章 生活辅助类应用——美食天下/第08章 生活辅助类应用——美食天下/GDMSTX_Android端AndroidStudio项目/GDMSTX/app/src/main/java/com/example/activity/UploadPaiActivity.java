package com.example.activity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import com.example.MyGridView.MyGridView;
import com.example.util.BitmapUtils;
import com.example.util.Constant;
import com.example.util.LocationUtil;
import com.example.util.UploadUtil;
import com.example.util.Utils;
import com.king.photo.util.Bimp;
import com.king.photo.util.ImageItem;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Toast;
import android.widget.TextView;
//上传随拍界面
public class UploadPaiActivity extends Activity implements OnClickListener {
	public static TextView tv_tip;
	public static EditText tv_adress;
	private EditText et_msg;
	MyGridView gv; // 图片gridview
	BaseAdapter ba; // 适配器
	UploadUtil util;
	PopupWindow pop;
	View thisView;
	String[] contents;
	List<ImageItem> listfile;
	InputMethodManager imm;
	ScrollView scrollView;
	Handler mHandler;
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		thisView = getLayoutInflater().inflate(R.layout.lay_pai_upload, null);
		mHandler=new Handler();
		setContentView(thisView);
		listfile=new ArrayList<ImageItem>();
		Bimp.initPai(listfile.size());
		scrollView=(ScrollView) thisView.findViewById(R.id.scrollView);
		imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		init();
		util = new UploadUtil(this);
		RelativeLayout rl = (RelativeLayout) findViewById(R.id.include_head);
		TextView tv_head = (TextView) rl.findViewById(R.id.head_title);
		tv_head.setText("上传随拍");
		TextView tv_submit = (TextView) rl.findViewById(R.id.upload_submit);
		tv_submit.setText("完成");
		tv_submit.setOnClickListener(this);
		et_msg = (EditText) findViewById(R.id.upload_pai_inputmsg);
		tv_tip = (TextView) findViewById(R.id.tip);
		tv_adress = (EditText) findViewById(R.id.tv_adress);
		ImageView iv_back = (ImageView) rl.findViewById(R.id.btback);
		iv_back.setOnClickListener(this);
		LinearLayout ll_tip = (LinearLayout) findViewById(R.id.pai_upload_tip);
		ll_tip.setOnClickListener(this);
		ImageButton localBt=(ImageButton) findViewById(R.id.local);
		localBt.setOnClickListener(this);
		gv = (MyGridView) findViewById(R.id.upload_pai_img_gridview);
		ba = new PicChooseAdapter(this);
		gv.setAdapter(ba);	
		tv_adress.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				tv_adress.setHint("");
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {}

			@Override
			public void afterTextChanged(Editable s) {}
		});
	}

	private void init() {
		pop = new PopupWindow();
		pop.setWidth(LayoutParams.MATCH_PARENT);
		pop.setHeight(LayoutParams.WRAP_CONTENT);
		ColorDrawable dw = new ColorDrawable(0xa0000000);  
        pop.setBackgroundDrawable(dw);
		pop.setFocusable(true);
		pop.setOutsideTouchable(true);
		View view = getLayoutInflater().inflate(R.layout.take_pic_popwindow,
				null);
		pop.setContentView(view);
		View parent = view.findViewById(R.id.parent);
		parent.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				pop.dismiss();
			}
		});
		TextView take = (TextView) view.findViewById(R.id.take_pic_camera);
		TextView lib = (TextView) view.findViewById(R.id.lib);
		TextView cancel = (TextView) view.findViewById(R.id.cancel);
		lib.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Bimp.initPai(listfile.size());
				Intent intent = new Intent(UploadPaiActivity.this,
						AlbumActivity.class);				
				startActivityForResult(intent, Constant.PICTURE);
				overridePendingTransition(R.anim.activity_translate_in,
						R.anim.activity_translate_out);
				pop.dismiss();
			}
		});
		take.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				startActivityForResult(camera, Constant.CAMERA_SIMPLE);
				overridePendingTransition(R.anim.activity_translate_in,
						R.anim.activity_translate_out);
				pop.dismiss();
			}
		});
		cancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				pop.dismiss();
			}
		});
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent = new Intent();
		switch (v.getId()) {
		case R.id.btback:
			this.finish();
			break;
		case R.id.pai_upload_tip: // 标签
			intent.setClass(UploadPaiActivity.this, RandomChooseLabelActivity.class);
			startActivity(intent);
			break;
		case R.id.local:
			tv_adress.setHint("正在获取城市...");
			if (Utils.isNewWork(this)) {
				new LocationUtil(this);
			}
			break;
		case R.id.upload_submit:
			contents = new String[5];
			contents[0] = Utils.getUser(getApplicationContext())[0];
			contents[3] = et_msg.getText().toString();
			contents[2] = tv_tip.getText().toString();
			contents[1] = tv_adress.getText().toString();
			upload(contents, listfile);			
			break;
		}
	}

	public Boolean upload(String[] contents, List<ImageItem> items) {
		if (contents[3] == null || contents[3].length() < 1) {
			Toast.makeText(this, "请补充描述内容", Toast.LENGTH_SHORT).show();
			return false;
		}
		if (contents[2] == null || contents[2].length() < 1) {
			Toast.makeText(this, "请选择或自定义标签", Toast.LENGTH_SHORT).show();
			return false;
		}
		if (items == null || items.size() < 1) {
			Toast.makeText(this, "请选择随拍图片", Toast.LENGTH_SHORT).show();
			return false;
		}
		util.uploadRandom(contents, items);
		finish();
		return true;
	}

	// 处理反回的结果随拍图片到gridview
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		System.out.println("ok requestcode "+requestCode);
		if (requestCode == Constant.CAMERA_SIMPLE && resultCode == Activity.RESULT_OK
				&& null != data) {			
			Bundle bundle = data.getExtras();
			// 获取相机返回的数据，并转换为图片格式
			Bitmap bitmap = (Bitmap) bundle.get("data");
			FileOutputStream fout = null;			
	        File fileFolder = new File(Environment.getExternalStorageDirectory()  
	                + "/pintu/");  
	        if (!fileFolder.exists()) { // 如果目录不存在，则创建一个名为"finger"的目录   
	            fileFolder.mkdir();  
	        } 
			String fileName=DateFormat.format("yyyyMMdd_hhmmss",
					Calendar.getInstance(Locale.CHINA))	+ ".jpeg";
	        File jpgFile = new File(fileFolder, fileName);  
	        String pathName=jpgFile.getPath();
			try {				
				fout = new FileOutputStream(jpgFile);
				bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fout);
				ImageItem item = new ImageItem();
				item.setImagePath(pathName);
				listfile.add(item);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} finally {
				try {
					fout.flush();
					fout.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			scrollView.post(new Runnable() { 
			       public void run() { 
			        scrollView.fullScroll(ScrollView.FOCUS_DOWN); 
					ba.notifyDataSetChanged();
			       } 			 
			}); 
			// 显示图片
		}
		if (requestCode == Constant.PICTURE )
		{			
			listfile.addAll(Bimp.tempSelectBitmap);
			ba.notifyDataSetChanged();
			scrollView.post(new Runnable() { 
			       public void run() { 
			        scrollView.fullScroll(ScrollView.FOCUS_DOWN); 
			       } 
			 
			}); 
		}
	}
	
	class PicChooseAdapter extends BaseAdapter {
		Context context;
		PicChooseAdapter(Context context) {
			this.context = context;
		}
		@Override
		public int getCount() {
			if (listfile.size() == Bimp.PROCESS_COUNT) {
				return Bimp.PROCESS_COUNT;
			}
			return listfile.size() + 1;
		}

		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return 0;
		}
		@Override
		public View getView(final int arg0, View arg1, ViewGroup arg2) {
			if (arg1 == null) {
				arg1 = LayoutInflater.from(context).inflate(
						R.layout.pai_add_image, null);
			}
			Button bt = (Button) arg1.findViewById(R.id.delete);
			ImageView imageview = (ImageView) arg1.findViewById(R.id.imageView);
			if (arg0 < listfile.size()) {
				ImageItem item = listfile.get(arg0);
				imageview.setTag(item.imagePath);
				Bimp.displayBmp(imageview, item.thumbnailPath, item.imagePath,
						item);		
				bt.setVisibility(View.VISIBLE);
				bt.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						listfile.remove(arg0);
						notifyDataSetChanged();
						int current=scrollView.getScrollY();						
						int height=scrollView.getHeight();
						final int now=current>height?height:current;
						System.out.println("current "+current);
						mHandler.postDelayed(new Runnable()
						{
							@Override
							public void run() {
								scrollView.smoothScrollTo(0,now);										
							}							
						},2);
					}
				});
				imageview.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						ArrayList<String> sd=new ArrayList<String>();
						for(ImageItem item:listfile)
						{
							sd.add(item.imagePath);
						}
						Intent intent=new Intent(UploadPaiActivity.this,
								PictureBrowsing.class);
						intent.putStringArrayListExtra("paths", sd);
						intent.putExtra("order",arg0);
						startActivity(intent);
						overridePendingTransition(R.anim.fade, R.anim.hold);
					}
				});
			}else {
				imageview.setTag(null);
				imageview.setImageResource(R.drawable.icon_addpic_unfocused);
				bt.setVisibility(View.GONE);
				imageview.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						pop.showAtLocation(thisView, Gravity.BOTTOM, 0, 0);
						imm.hideSoftInputFromWindow(et_msg.getWindowToken(), 0);
					}
				});
			}
			return arg1;
		}
	}
}
