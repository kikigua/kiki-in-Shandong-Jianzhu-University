package com.example.activity;
import com.example.util.Constant;
import com.example.util.BitmapUtils;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

/*
 * 添加菜品时,详细添加制作过程的界面
 */
public class AddProcessDetail extends Activity
{	
	EditText et;
	Button delete;
	ImageView iv;
	ImageButton back;
	TextView ok;
	int order;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_process_detail);
		delete=(Button) this.findViewById(R.id.delete);
		iv=(ImageView) this.findViewById(R.id.image);
		et=(EditText) this.findViewById(R.id.content);
		TextView tv=(TextView) this.findViewById(R.id.order);
		Bundle bundle=this.getIntent().getExtras();
		order=bundle.getInt("order", 0);
		tv.setText(order+"");
		String content=bundle.getString("content", "");
		et.setText(content);
		final String pathName=bundle.getString("pathName",null);
		Bitmap bm=BitmapUtils.revitionImageSize(pathName);
		iv.setImageBitmap(bm);
		iv.setOnClickListener(
				new OnClickListener()
				{
					@Override
					public void onClick(View v) {
						Intent intent=new Intent();
						intent.putExtra("pathName", pathName);
						intent.setClass(AddProcessDetail.this, PreviewSingleImage.class);
						startActivity(intent);
						overridePendingTransition(R.anim.fade, R.anim.hold);
					}					
				});	
		initView();
		}	
	private void initView()
	{
		back=(ImageButton) this.findViewById(R.id.btback);
		back.setOnClickListener(
				new OnClickListener()
				{
					@Override
					public void onClick(View v) {
						finish();	
						overridePendingTransition(R.anim.fade, R.anim.hold);
					}					
				});
		ok=(TextView) findViewById(R.id.upload_submit);
		ok.setText("完成");
		ok.setOnClickListener(
				new OnClickListener()
				{
					@Override
					public void onClick(View v) {
						String conent=et.getText().toString().trim();
						Intent intent=new Intent();
						intent.setClass(AddProcessDetail.this, UploadActivity.class);
						intent.putExtra("order", order);
						intent.putExtra("content", conent);
						setResult(Constant.PROCESS_ADD,intent);
						finish();
						overridePendingTransition(R.anim.fade, R.anim.hold);

					}					
				});
		delete=(Button) findViewById(R.id.delete);
		delete.setOnClickListener(
				new OnClickListener()
				{

					@Override
					public void onClick(View v) {
						Intent intent=new Intent();
						intent.setClass(AddProcessDetail.this, UploadActivity.class);
						intent.putExtra("order", order);
						setResult(Constant.DELETE_PROCESS,intent);
						System.out.println("addProcessDetail"+Constant.DELETE_PROCESS);
						finish();
					}					
				});
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			this.finish();
			overridePendingTransition(R.anim.fade, R.anim.hold);
		}
		return true;
	}	
}