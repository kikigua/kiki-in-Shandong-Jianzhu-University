package com.example.activity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import com.example.tabFragment.MeFragment;
import com.example.util.Constant;
import com.example.util.BitmapUtils;
import com.example.util.MyToast;
import com.example.util.UploadUtil;
import com.example.util.Utils;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.format.DateFormat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.example.activity.AlbumActivity;
import com.king.photo.util.Bimp;
import com.king.photo.util.ImageItem;

public class UploadActivity extends Activity implements OnClickListener {
	TextView tv_submit;
	ImageView iv_back;
	ImageView main_image;
	RelativeLayout layout_imag;
	LinearLayout add_step;// 添加步骤
	PopupWindow pop;
	String pripath; // 主图
	LinearLayout proll; // 制作过程
	LinearLayout foodll; // 主食
	LinearLayout vicell; // 辅料
	UploadUtil uploadUtil;
	ImageView add_vice; // 添加辅料
	ImageView add_food;// 添加主料
	int[] baserl = new int[] { R.id.lay_recipe_upload_caixi,
			R.id.lay_recipe_upload_kouwei,
			R.id.lay_recipe_upload_pengren_fangshi,
			R.id.lay_recipe_upload_pengren_shijian,
			R.id.lay_recipe_upload_zhuyao_chuju,
			R.id.lay_recipe_upload_zhizuo_nandu };
	RelativeLayout rls[] = new RelativeLayout[baserl.length]; // 6种信息
	public static TextView[] tvs = new TextView[6]; // 6中信息
	TextView take;
	TextView lib;
	InputMethodManager imm;
	MyToast toast;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lay_upload);
		uploadUtil = new UploadUtil(this);
		toast = new MyToast(this);
		initView();
	}

	private void initView() {
		imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		RelativeLayout rl = (RelativeLayout) findViewById(R.id.include_head);
		TextView tv_head = (TextView) rl.findViewById(R.id.head_title);
		tv_head.setText("上传菜谱");
		tv_submit = (TextView) rl.findViewById(R.id.upload_submit);
		tv_submit.setText("完成");
		tv_submit.setOnClickListener(this);
		iv_back = (ImageView) rl.findViewById(R.id.btback);
		iv_back.setOnClickListener(this);
		layout_imag = (RelativeLayout) this.findViewById(R.id.layout_image);
		layout_imag.setOnClickListener(this);
		main_image = (ImageView) findViewById(R.id.main_image);

		proll = (LinearLayout) this.findViewById(R.id.pro_list);
		for (int i = 0; i < baserl.length; i++) {
			rls[i] = (RelativeLayout) this.findViewById(baserl[i]);
			tvs[i] = (TextView) rls[i].getChildAt(2);
			rls[i].setOnClickListener(this);
		}
		add_step = (LinearLayout) findViewById(R.id.lay_recipe_upload_add_step);
		add_step.setOnClickListener(this);
		foodll = (LinearLayout) this.findViewById(R.id.food_add);

		RelativeLayout include_food = (RelativeLayout) this
				.findViewById(R.id.include_food);
		add_food = (ImageView) include_food.findViewById(R.id.delete);
		add_food.setOnClickListener(this);
		EditText food = (EditText) include_food.findViewById(R.id.food);
		EditText unit = (EditText) include_food.findViewById(R.id.unit);
		food.setEnabled(false);
		unit.setVisibility(View.GONE);
		addfood();
		addfood();
		vicell = (LinearLayout) this.findViewById(R.id.vice_add);
		RelativeLayout include_vice = (RelativeLayout) this
				.findViewById(R.id.include_vice);
		add_vice = (ImageView) include_vice.findViewById(R.id.delete);
		add_vice.setOnClickListener(this);
		food = (EditText) include_vice.findViewById(R.id.food);
		unit = (EditText) include_vice.findViewById(R.id.unit);
		food.setEnabled(false);
		unit.setVisibility(View.GONE);
		addVice();
		addVice();
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
		take = (TextView) view.findViewById(R.id.take_pic_camera);
		lib = (TextView) view.findViewById(R.id.lib);
		TextView cancel = (TextView) view.findViewById(R.id.cancel);
		cancel.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				pop.dismiss();
			}
		});
		main_image.setFocusable(true);
		main_image.setFocusableInTouchMode(true);
		main_image.requestFocus();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);		
		if (requestCode == Constant.CAMERA_SIMPLE		// 主图 照片
				&& resultCode == Activity.RESULT_OK && null != data) {
			String path = savePhoto(data);
			Bimp.displayBmpfromFile(main_image, path);
		}// 主图 图库
		else if (requestCode == Constant.PICTURE_SIMLE) {
			if(Bimp.tempSelectBitmap.size()>0){
				pripath = Bimp.tempSelectBitmap.get(0).getImagePath();
				Bitmap bm = BitmapUtils.revitionImageSize(pripath);
				main_image.setImageBitmap(bm);
		}}
		else if (requestCode == Constant.PICTURE) {		// 过程 图库
			for (ImageItem i : Bimp.tempSelectBitmap) {
				addPro(i.getImagePath());
			}}		
		else if (requestCode == Constant.CAMERA// 制作过程图片拍照
				&& resultCode == Activity.RESULT_OK && null != data) {
			String filename = savePhoto(data);			
			addPro(filename);
		}		
		else if (resultCode == Constant.PROCESS_ADD) {// 添加过程描述
			Bundle bundle = data.getExtras();
			String content = bundle.getString("content", "");
			int order = bundle.getInt("order");
			View v = proll.getChildAt(order - 1);
			EditText et = (EditText) v.findViewById(R.id.introduce);
			et.setText(content);
		}// 删除过程
		else if (resultCode == Constant.DELETE_PROCESS) {
			Bundle bundle = data.getExtras();
			int order = bundle.getInt("order");
			proll.removeViewAt(order - 1);
			for (int i = 0; i < proll.getChildCount(); i++) {// 编号重置
				View chiled = proll.getChildAt(i);
				TextView num = (TextView) chiled.findViewById(R.id.order);
				num.setText((1 + i) + "");
		}}}

	// 界面onclick
	@Override
	public void onClick(View v) {
		if (v.equals(layout_imag)) {
			lib.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					Bimp.initSingleBimp();
					Intent intent = new Intent(UploadActivity.this,
							AlbumActivity.class);
					startActivityForResult(intent, Constant.PICTURE_SIMLE);
					overridePendingTransition(R.anim.activity_translate_in,
							R.anim.activity_translate_out);
					pop.dismiss();
				}});
			take.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
					startActivityForResult(camera, Constant.CAMERA_SIMPLE);
					pop.dismiss();
				}
			});
			pop.showAtLocation(findViewById(R.id.lay_upload), Gravity.BOTTOM
					| Gravity.CENTER_HORIZONTAL, 0, 0); // 设置layout在PopupWindow中显示的位置
			imm.hideSoftInputFromWindow(UploadActivity.this.getCurrentFocus()
					.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
			return;
		} else if (v.equals(add_step)) { // 添加过程步骤
			if (proll.getChildCount() >= Bimp.PROCESS_COUNT) {
				toast.showToast("最多20个步骤");
				return;
			}
			lib.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent intent = new Intent(UploadActivity.this,
							AlbumActivity.class);
					Bimp.initMenu(proll.getChildCount());
					System.out.println("count " + proll.getChildCount());
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
					startActivityForResult(camera, Constant.CAMERA);
					pop.dismiss();

				}
			});
			pop.showAtLocation(findViewById(R.id.lay_upload), Gravity.BOTTOM
					| Gravity.CENTER_HORIZONTAL, 0, 0); // 设置layout在PopupWindow中显示的位置
			imm.hideSoftInputFromWindow(UploadActivity.this.getCurrentFocus()
					.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
			return;
		} 
		Intent intent = new Intent();
		intent.setClass(this, MenuChooseLabelActivity.class);
		String[] list;
		if (v.equals(rls[0])) {
			RelativeLayout r = (RelativeLayout) v;
			TextView tv = (TextView) r.getChildAt(0);
			String str = tv.getText().toString();
			list = Constant.style;
			intent.putExtra("list", list);
			intent.putExtra("title", str);
			intent.putExtra("count", 0);
			startActivity(intent);
		} else if (v.equals(rls[1])) {
			RelativeLayout r = (RelativeLayout) v;
			TextView tv = (TextView) r.getChildAt(0);
			String str = tv.getText().toString();
			list = Constant.flavour;
			intent.putExtra("list", list);
			intent.putExtra("title", str);
			intent.putExtra("count", 1);
			startActivity(intent);
		} else if (v.equals(rls[2])) {
			RelativeLayout r = (RelativeLayout) v;
			TextView tv = (TextView) r.getChildAt(0);
			String str = tv.getText().toString();
			list = Constant.craft;
			intent.putExtra("list", list);
			intent.putExtra("title", str);
			intent.putExtra("count", 2);
			startActivity(intent);
		} else if (v.equals(rls[3])) {
			RelativeLayout r = (RelativeLayout) v;
			TextView tv = (TextView) r.getChildAt(0);
			String str = tv.getText().toString();
			list = Constant.ctime;
			intent.putExtra("list", list);
			intent.putExtra("title", str);
			intent.putExtra("count", 3);
			startActivity(intent);
		} else if (v.equals(rls[4])) {
			RelativeLayout r = (RelativeLayout) v;
			TextView tv = (TextView) r.getChildAt(0);
			String str = tv.getText().toString();
			list = Constant.tool;
			intent.putExtra("list", list);
			intent.putExtra("title", str);
			intent.putExtra("count", 4);
			startActivity(intent);
		} else if (v.equals(rls[5])) {
			RelativeLayout r = (RelativeLayout) v;
			TextView tv = (TextView) r.getChildAt(0);
			String str = tv.getText().toString();
			list = Constant.difficulty;
			intent.putExtra("list", list);
			intent.putExtra("title", str);
			intent.putExtra("count", 5);
			startActivity(intent);
		}else if (v.equals(add_food)) // 添加主料
		{
			addfood();
		} else if (v.equals(add_vice)) // 添加辅料
		{
			addVice();
		} else if (v.equals(tv_submit)) { // 上传
			if (uploadMenu()) {
				finish();
			}
		} else if (v.equals(iv_back)) { // 返回
			finish();
		}
	}

	// 上传菜谱
	private Boolean uploadMenu() {
		// 没有登陆登陆
		if (!Utils.isLand(getApplicationContext())) {
			Intent tent = new Intent(this, MeFragment.class);
			startActivity(tent);
			return false;
		}
		String[] users = Utils.getUser(getApplicationContext());
		LinkedList<String> contents = new LinkedList<String>();
		contents.add(users[0]); // user 名
		TextView et_name = (TextView) this.findViewById(R.id.et_name);
		if (et_name.getText().length() < 1) {
			toast.showToast("菜名不能为空");
			return false;
		}

		contents.add(et_name.getText().toString()); // 菜谱名
		for (int i = 0; i < tvs.length; i++){ //相关信息
			TextView tv = tvs[i];
			String  label=tv.getText().toString().trim();
			TextView tip = (TextView) rls[i].getChildAt(0);
			if (label.length() < 1) {				
				String wo = tip.getText().toString().trim();				
				toast.showToast(wo + "不能为空");
				return false;
			}
			contents.add(label);
		}
		EditText tip = (EditText) this.findViewById(R.id.et_kill);
		contents.add(tip.getText().toString()); // 小技巧
		EditText introduce = (EditText) this.findViewById(R.id.menu_introduce);
		contents.add(introduce.getText().toString());
		StringBuilder sb = new StringBuilder();
		if (foodll.getChildCount() <= 0) {
			toast.showToast("主食项项不能为空");
			return false;
		}
		for (int i = 0; i < foodll.getChildCount(); i++) {
			RelativeLayout ll = (RelativeLayout) foodll.getChildAt(i);
			EditText food = (EditText) ll.findViewById(R.id.food);
			EditText unit = (EditText) ll.findViewById(R.id.unit);
			String foodst = food.getText().toString().trim();
			String unitst = unit.getText().toString().trim();
			if (foodst.length() < 1 || unitst.length() < 1) {
				toast.showToast("请补充主食项");
				return false;
			}
			sb.append( foodst+ "|" +  unitst + "%");
		}
		contents.add(sb.substring(0, sb.length() - 1)); // 主食
		sb = new StringBuilder();
		if (vicell.getChildCount() <= 0) {
			toast.showToast("辅料不呢为空");
			return false;
		}
		for (int i = 0; i < vicell.getChildCount(); i++) {
			RelativeLayout ll = (RelativeLayout) vicell.getChildAt(i);
			EditText food = (EditText) ll.findViewById(R.id.food);
			EditText unit = (EditText) ll.findViewById(R.id.unit);
			String foodst = food.getText().toString().trim();
			String unitst = unit.getText().toString().trim();
			if (foodst.length() < 1 || unitst.length() < 1) {
				toast.showToast("请补充辅料项");
				return false;
			}
			sb.append( foodst+ "|" +  unitst + "%");
		}
		contents.add(sb.substring(0, sb.length() - 1));// 辅料
		List<String> introduces = new ArrayList<String>(); // 简介
		List<String> paths = new ArrayList<String>();
		if (proll.getChildCount() <= 0) {
			toast.showToast("请添加制作过程");
			return false;
		}
		for (int i = 0; i < proll.getChildCount(); i++) {
			View v = proll.getChildAt(i);
			EditText et = (EditText) v.findViewById(R.id.introduce);
			TextView tv = (TextView) v.findViewById(R.id.path);
			if (et.getText().length() < 1 || tv.getText().length() < 1) {
				toast.showToast("请将制作过程补充完整");
				return false;
			}
			introduces.add(et.getText().toString());
			paths.add(tv.getText().toString());
		}
		if (pripath == null) {
			toast.showToast("请选择主图");
			return false;
		}
		uploadUtil.uploadMenu(contents, paths, introduces, pripath);
		finish();
		return false;
	}

	// 过程列添加
	private void addPro(final String picPath) {
		final LinearLayout ll = (LinearLayout) LayoutInflater.from(
				getApplicationContext())
				.inflate(R.layout.menuup_pro_item, null);
		final TextView order = (TextView) ll.findViewById(R.id.order);
		final EditText et = (EditText) ll.findViewById(R.id.introduce);
		order.setText(proll.getChildCount() + 1 + "");
		Button delete = (Button) ll.findViewById(R.id.delete);
		delete.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				proll.removeView(ll);
				for (int i = 0; i < proll.getChildCount(); i++) {// 编号删除不稳定
					View chiled = proll.getChildAt(i);
					TextView order = (TextView) chiled.findViewById(R.id.order);
					order.setText((1 + i) + "");
				}
			}
		});
		ImageView iv = (ImageView) ll.findViewById(R.id.image);
		iv.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.putExtra("pathName", picPath);
				intent.setClass(UploadActivity.this, AddProcessDetail.class);
				int num = Integer.parseInt(order.getText().toString().trim());
				intent.putExtra("order", num);
				intent.putExtra("content", et.getText().toString().trim());
				startActivityForResult(intent, Constant.PROCESS);
			}
		});
		Bimp.displayBmpfromFile(iv, picPath);
		TextView path = (TextView) ll.findViewById(R.id.path);
		path.setText(picPath); // 图片路径
		proll.addView(ll);
	}

	// 增加主食项
	private void addfood() {
		final RelativeLayout ll = (RelativeLayout) LayoutInflater.from(
				getApplicationContext()).inflate(R.layout.menu_food_item, null);
		ImageView delete = (ImageView) ll.findViewById(R.id.delete);
		EditText dt = (EditText) ll.findViewById(R.id.food);

		delete.setImageResource(R.drawable.delete_sub);
		delete.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				foodll.removeView(ll);
			}
		});
		foodll.addView(ll);
		dt.requestFocus();
	}

	// 增加辅料项
	private void addVice() {
		final RelativeLayout ll = (RelativeLayout) LayoutInflater.from(
				getApplicationContext()).inflate(R.layout.menu_food_item,
				null);
		ImageView delete = (ImageView) ll.findViewById(R.id.delete);
		delete.setImageResource(R.drawable.delete_sub);
		delete.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				vicell.removeView(ll);
			}
		});
		vicell.addView(ll);
		EditText dt = (EditText) ll.findViewById(R.id.food);
		dt.requestFocus();
	}

	// 保存拍照图片
	private String savePhoto(Intent data) {
		String sdState = Environment.getExternalStorageState();
		if (!sdState.equals(Environment.MEDIA_MOUNTED)) {
			return null;
		}
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
		return pathName;
	}

	@Override
	protected void onStop() {
		Bimp.cancelTask();
		super.onStop();
	}
}
