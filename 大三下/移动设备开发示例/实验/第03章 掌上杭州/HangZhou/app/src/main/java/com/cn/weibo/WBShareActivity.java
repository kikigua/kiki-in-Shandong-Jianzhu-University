package com.cn.weibo;


import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.cn.hangzhou.R;
import com.cn.util.Constant;
import com.cn.util.FontManager;
import com.sina.weibo.sdk.api.ImageObject;
import com.sina.weibo.sdk.api.TextObject;
import com.sina.weibo.sdk.api.WeiboMessage;
import com.sina.weibo.sdk.api.WeiboMultiMessage;
import com.sina.weibo.sdk.api.share.BaseResponse;
import com.sina.weibo.sdk.api.share.IWeiboDownloadListener;
import com.sina.weibo.sdk.api.share.IWeiboHandler;
import com.sina.weibo.sdk.api.share.IWeiboShareAPI;
import com.sina.weibo.sdk.api.share.SendMessageToWeiboRequest;
import com.sina.weibo.sdk.api.share.SendMultiMessageToWeiboRequest;
import com.sina.weibo.sdk.api.share.WeiboShareSDK;
import com.sina.weibo.sdk.constant.WBConstants;

public class WBShareActivity extends Activity implements OnClickListener,IWeiboHandler.Response{
	public static final int WEIBO_MAX_LENGTH = 140;//最多140个字
	public Button mapType;
	public  String token;
	public String user_id;
    public FrameLayout mPiclayout;//盛放附加图片的layout
    ImageView ivImage;
    BitmapDrawable bitmapDrawable;
    Bitmap tempBitmap;
    public Button getPicBnt;//添加图片按钮
    public String mPicPath = "";//图片路径
    int BZ;
    /** 微博微博分享接口实例 */
    private IWeiboShareAPI  mWeiboShareAPI = null;

	 @Override
	 protected void onCreate(Bundle savedInstanceState) 
	 {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.weibo);//@+id/btnLogin  @+id/tvToken
	        //用自定义的字体方法
	        FontManager.changeFonts(FontManager.getContentView(this),this);
	        // 创建微博分享接口实例
	        mWeiboShareAPI = WeiboShareSDK.createWeiboAPI(this, Constants.APP_KEY);

	      
	        
	        // 注册第三方应用到微博客户端中，注册成功后该应用将显示在微博的应用列表中。
	        // 但该附件栏集成分享权限需要合作申请，详情请查看 Demo 提示
	        // NOTE：请务必提前注册，即界面初始化的时候或是应用程序初始化时，进行注册
	        mWeiboShareAPI.registerApp();
	        mapType = (Button) this.findViewById(R.id.btnLogin);
	        mapType.setText("分享");
 			mapType.setOnClickListener(new OnClickListener() 
 			{
				@Override
				public void onClick(View v)
				{
					send();
				}	
 			});
 			getPicBnt=(Button)findViewById(R.id.get_pic_button);//获取图片按钮
 			getPicBnt.setOnClickListener(this);//添加监听
 	        mPiclayout = (FrameLayout)findViewById(R.id.flPic);//承装图片的layout
	        // 如果未安装微博客户端，设置下载微博对应的回调
	        if (!mWeiboShareAPI.isWeiboAppInstalled()) {
	            mWeiboShareAPI.registerWeiboDownloadListener(new IWeiboDownloadListener() {
	                @Override
	                public void onCancel() {
	                    Toast.makeText(WBShareActivity.this, 
	                            "取消下载", 
	                            Toast.LENGTH_SHORT).show();
	                }
	            });
	        }
	        
			// 当 Activity 被重新初始化时（该 Activity 处于后台时，可能会由于内存不足被杀掉了），
	        // 需要调用 {@link IWeiboShareAPI#handleWeiboResponse} 来接收微博客户端返回的数据。
	        // 执行成功，返回 true，并调用 {@link IWeiboHandler.Response#onResponse}；
	        // 失败返回 false，不调用上述回调
	        if (savedInstanceState != null) {
	            mWeiboShareAPI.handleWeiboResponse(getIntent(), this);
	        }

	 }
	 /**
	     * @see {@link Activity#onNewIntent}
	     */	
	    @Override
	    protected void onNewIntent(Intent intent) {
	        super.onNewIntent(intent);
	        
	        // 从当前应用唤起微博并进行分享后，返回到当前应用时，需要在此处调用该函数
	        // 来接收微博客户端返回的数据；执行成功，返回 true，并调用
	        // {@link IWeiboHandler.Response#onResponse}；失败返回 false，不调用上述回调
	        mWeiboShareAPI.handleWeiboResponse(intent, this);
	    }
	    
	
	public void send()
	{
		if (mWeiboShareAPI.isWeiboAppSupportAPI())
		        {
		            int supportApi = mWeiboShareAPI.getWeiboAppSupportAPI();
		            if (supportApi >= 10351 /*ApiUtils.BUILD_INT_VER_2_2*/) 
		            {
		                 WeiboMultiMessage weiboMessage = new WeiboMultiMessage();
		                 weiboMessage.textObject = getTextObj();
		                 if(BZ==1){
		                 weiboMessage.imageObject = getImageObj();
		                 }
		                 // 2. 初始化从第三方到微博的消息请求
		                 SendMultiMessageToWeiboRequest request = new SendMultiMessageToWeiboRequest();
		                 // 用transaction唯一标识一个请求
		                 request.transaction = String.valueOf(System.currentTimeMillis());
		                 request.multiMessage = weiboMessage;
		                 
		                 // 3. 发送请求消息到微博，唤起微博分享界面
		                 
		                 mWeiboShareAPI.sendRequest(request);
		            } else {
		            	  // 1. 初始化微博的分享消息
		                // 用户可以分享文本、图片、网页、音乐、视频中的一种
		                WeiboMessage weiboMessage = new WeiboMessage();
		                weiboMessage.mediaObject = getTextObj();
		                if(BZ==1){
                        weiboMessage.mediaObject = getImageObj();
		                }
		                // 2. 初始化从第三方到微博的消息请求
		                SendMessageToWeiboRequest request = new SendMessageToWeiboRequest();
		                // 用transaction唯一标识一个请求
		                request.transaction = String.valueOf(System.currentTimeMillis());
		                request.message = weiboMessage;
		                
		                // 3. 发送请求消息到微博，唤起微博分享界面
		                mWeiboShareAPI.sendRequest(request);
		            }
			        } else {
			           Toast.makeText(this,"微博客户端不支持 SDK 分享或微博客户端未安装或微博客户端是非官方版本。", Toast.LENGTH_SHORT).show();
			        }
	}
	private TextObject getTextObj() 
	{
		TextObject textObject = new TextObject();
        textObject.text = getSharedText();
        return textObject;
	}
	 /**
     * 创建图片消息对象。
     * 
     * @return 图片消息对象。
     */
    private ImageObject getImageObj() {
        ImageObject imageObject = new ImageObject();
        bitmapDrawable = (BitmapDrawable) ivImage.getDrawable();
        imageObject.setImageObject(bitmapDrawable.getBitmap());
        return imageObject;
    }
	  /**
     * 获取分享的文本模板。
     * 
     * @return 分享的文本模板
     */
    private String getSharedText() {
        int formatId = R.string.share_dialog_title;
        String format = getString(formatId);
        String text = format;
        format ="我正在使用微博客户端发博器分享文字！";
        return text;
    }
	/**
	 * 接收微客户端博请求的数据。
	 * 当微博客户端唤起当前应用并进行分享时，该方法被调用。
	 * 
	 * @param baseRequest 微博请求数据对象
	 * @see {@link IWeiboShareAPI#handleWeiboRequest}
	 */
	@Override
	public void onResponse(BaseResponse baseResp) {
		switch (baseResp.errCode) {
        case WBConstants.ErrorCode.ERR_OK:
            Toast.makeText(this, "成功", Toast.LENGTH_LONG).show();
            break;
        case WBConstants.ErrorCode.ERR_CANCEL:
            Toast.makeText(this, "取消", Toast.LENGTH_LONG).show();
            break;
        case WBConstants.ErrorCode.ERR_FAIL:
            Toast.makeText(this, 
                    "失败" + "Error Message: " + baseResp.errMsg, 
                    Toast.LENGTH_LONG).show();
            break;
        }
		
	}
	@Override
	public void onClick(View v) {
		if(v.getId()==R.id.get_pic_button)//获取照片的按钮
		{
			SelectPicDialog spdialog=new SelectPicDialog(this);
			spdialog.show();//显示选择方式对话框
			BZ=1;
		}
		
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if(resultCode==RESULT_OK && requestCode==Constant.FROMALBUM && null!=data){//成功返回且是从图片库返回了图片
			Uri seletedImage=data.getData();
			String[] filePathColumn={MediaStore.Images.Media.DATA};
			Cursor cursor=getContentResolver().query(seletedImage, filePathColumn, null, null, null);
			cursor.moveToFirst();
			int columnIndex=cursor.getColumnIndex(filePathColumn[0]);
			this.mPicPath=cursor.getString(columnIndex);//得到图片的路径
			cursor.close();
			ivImage=(ImageView)findViewById(R.id.ivImage);//获取显示图片的控件引用
			bitmapDrawable=(BitmapDrawable)ivImage.getDrawable();
			if(!bitmapDrawable.getBitmap().isRecycled()){//将此控件中之前的图片释放掉
				bitmapDrawable.getBitmap().recycle();
			}
			ivImage.setImageBitmap(BitmapFactory.decodeFile(this.mPicPath));//将图片解析设置显示
			mPiclayout.setVisibility(View.VISIBLE);//设置图片可见
		}
		//照相机返回的结果
		if(resultCode==RESULT_OK && requestCode==Constant.FROMCAMERA && null!=data){
			Bundle dataBundle=data.getExtras();//得到附加的值
			tempBitmap=(Bitmap)dataBundle.get("data");//得到图片
			ivImage=(ImageView)findViewById(R.id.ivImage);//获取显示图片的控件引用
			bitmapDrawable=(BitmapDrawable)ivImage.getDrawable();
			if(!bitmapDrawable.getBitmap().isRecycled()){//将此控件中之前的图片释放掉
				bitmapDrawable.getBitmap().recycle();
			}
			ivImage.setImageBitmap(tempBitmap);//将图片设置显示
			mPiclayout.setVisibility(View.VISIBLE);//设置图片可见
			//将图片保存，获得保存路径
			// 判断SDcard是否存在
            boolean isSdCardExit = Environment.getExternalStorageState().equals(
                            android.os.Environment.MEDIA_MOUNTED);
            if(isSdCardExit){
            	File saveImageFile = new File("/sdcard/bnguid");//文件夹目录
            	if (!saveImageFile.exists()) {//若无此文件夹
            		saveImageFile.mkdir();//创建
            	}
            	String fileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date(System.currentTimeMillis()));//设置文件名
            	File file=null;
    			try {
					file=File.createTempFile(fileName, ".png", saveImageFile);//创建新文件
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
    			if(null != file){//若创建成功
    				try {
        				BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
        				tempBitmap.compress(Bitmap.CompressFormat.JPEG, 80, bos);
        				this.mPicPath=file.getAbsolutePath();//存储图像的路径
        				System.out.println("camera pic save path :"+this.mPicPath);
        				bos.flush();//将输入流强行输出并且关闭
        				bos.close();
        			} catch (FileNotFoundException e) {
        				e.printStackTrace();
        			} catch (IOException e) {
        				e.printStackTrace();
        			}
    			}else{
    				Toast.makeText(this,getResources().getString(R.string.save_failed) , Toast.LENGTH_SHORT).show();
    			}
    			 
            }else{
            	Toast.makeText(this,getResources().getString(R.string.nosdcard) , Toast.LENGTH_SHORT).show();
            }
			
			
		}
	}
}