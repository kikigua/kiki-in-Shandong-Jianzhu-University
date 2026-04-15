package com.cn.weibo;

import java.text.SimpleDateFormat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

import com.cn.hangzhou.R;
import com.cn.util.FontManager;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WeiboAuth;
import com.sina.weibo.sdk.auth.WeiboAuthListener;
import com.sina.weibo.sdk.auth.sso.SsoHandler;
import com.sina.weibo.sdk.exception.WeiboException;

public class WBMainActivity extends Activity{
	 /** 显示认证后的信息，如 AccessToken */
    private TextView mTokenText;
    /** 微博 Web 授权类，提供登陆等功能  */
    private WeiboAuth mWeiboAuth;
    String atoken;
    String uid;
    /** 封装了 "access_token"，"expires_in"，"refresh_token"，并提供了他们的管理功能  */
    private Oauth2AccessToken mAccessToken;
	 @Override
	 protected void onCreate(Bundle savedInstanceState) 
	 {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.weibo_main);//@+id/btnLogin  @+id/tvToken
	        //用自定义的字体方法
	        FontManager.changeFonts(FontManager.getContentView(this),this);
	        // 获取 Token View，并让提示 View 的内容可滚动（小屏幕可能显示不全）
	        mTokenText = (TextView) findViewById(R.id.tvToken);
	        // 创建微博实例
	        mWeiboAuth = new WeiboAuth(this, Constants.APP_KEY, Constants.REDIRECT_URL, Constants.SCOPE);
	        // Web 授权
	        findViewById(R.id.btnLogin).setOnClickListener(new OnClickListener() {
	            @Override
	            public void onClick(View v) {
	                mWeiboAuth.anthorize(new AuthListener());
	                // 或者使用：mWeiboAuth.authorize(new AuthListener(), Weibo.OBTAIN_AUTH_TOKEN);
	            }
	        });
	        
	        // 从 SharedPreferences 中读取上次已保存好 AccessToken 等信息，
	        // 第一次启动本应用，AccessToken 不可用
	        mAccessToken = AccessTokenKeeper.readAccessToken(this);
	        if (mAccessToken.isSessionValid()) {
	            updateTokenView(true);
	            StarActivity();
	        }     
	        
	 }
	 /**
	     * 微博认证授权回调类。
	     * 1. SSO 授权时，需要在 {@link #onActivityResult} 中调用 {@link SsoHandler#authorizeCallBack} 后，
	     *    该回调才会被执行。
	     * 2. 非 SSO 授权时，当授权结束后，该回调就会被执行。
	     * 当授权成功后，请保存该 access_token、expires_in、uid 等信息到 SharedPreferences 中。
	     */
	    class AuthListener implements WeiboAuthListener {
	        
	        @Override
	        public void onComplete(Bundle values) {
	            // 从 Bundle 中解析 Token
	            mAccessToken = Oauth2AccessToken.parseAccessToken(values);
	            if (mAccessToken.isSessionValid()) {
	                // 显示 Token
	                updateTokenView(false);
	                
	                // 保存 Token 到 SharedPreferences
	                AccessTokenKeeper.writeAccessToken(WBMainActivity.this, mAccessToken);
	                System.out.println("获取到的mAccessToken值"+mAccessToken);
	               
	                Toast.makeText(WBMainActivity.this, 
	                        "授权成功", Toast.LENGTH_SHORT).show();
	                StarActivity();
	            } else {
	                // 以下几种情况，您会收到 Code：
	                // 1. 当您未在平台上注册的应用程序的包名与签名时；
	                // 2. 当您注册的应用程序包名与签名不正确时；
	                // 3. 当您在平台上注册的包名和签名与您当前测试的应用的包名和签名不匹配时。
	                String code = values.getString("code");
	                String message = "授权失败";
	                if (!TextUtils.isEmpty(code)) {
	                    message = message + "\nObtained the code: " + code;
	                }
	                Toast.makeText(WBMainActivity.this, message, Toast.LENGTH_LONG).show();
	            }
	        }

	        @Override
	        public void onCancel() {
	            Toast.makeText(WBMainActivity.this, 
	                    "授权取消", Toast.LENGTH_LONG).show();
	        }

	        @Override
	        public void onWeiboException(WeiboException e) {
	            Toast.makeText(WBMainActivity.this, 
	                    "Auth exception : " + e.getMessage(), Toast.LENGTH_LONG).show();
	        }
	    }
	    /**
	     * 显示当前 Token 信息。
	     * 
	     * @param hasExisted 配置文件中是否已存在 token 信息并且合法
	     */
	    private void updateTokenView(boolean hasExisted) {
	        String date = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(
	                new java.util.Date(mAccessToken.getExpiresTime()));
	        String format ="Token：%1$s \n有效期：%2$s";
	        mTokenText.setText(String.format(format, mAccessToken.getToken(), date));
	        
	        String message = String.format(format, mAccessToken.getToken(), date);
	        if (hasExisted) {
	            message = "Token 仍在有效期内，无需再次登录。" + "\n" + message;
	        }
	        mTokenText.setText(message);
	    }
	    public void StarActivity()
	    {
	    	Intent intent = new Intent();
    		intent.putExtra("uid", uid);
    		intent.putExtra("token", atoken);
            intent=new Intent(WBMainActivity.this,WBShareActivity.class);
            WBMainActivity.this.startActivity(intent);
	    }

}
