package com.example.fragment;

import com.example.MyPopWindow.exitPopupWindow;
import com.example.MyRoundedImageView.RoundImageView;
import com.example.activity.CollectionActivity;
import com.example.activity.MenuChooseListActivity;
import com.example.activity.PreviewSingleImage;
import com.example.activity.R;
import com.example.activity.RandomListViewActivity;
import com.example.downLoader.ImageDownLoader;
import com.example.util.Constant;
import com.example.util.MyToast;
import com.example.util.Utils;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

/*
 * 登陆成功后的界面
 */
public class Login extends Fragment implements OnClickListener {
	Button exit;
	LinearLayout menu;
	LinearLayout random;
	LinearLayout rough;
	LinearLayout collection;
	ImageDownLoader imageDownLoader;
	View view;
	String uid;
	String uidPic;
	MyToast toast;
	RoundImageView imageView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		toast = new MyToast(getActivity());
		view = inflater.inflate(R.layout.me_land, null);
		exit = (Button) view.findViewById(R.id.land_bt);
		TextView atten = (TextView) view.findViewById(R.id.attention);
		TextView fans = (TextView) view.findViewById(R.id.fans);
		collection = (LinearLayout) view.findViewById(R.id.randomc);
		rough = (LinearLayout) view.findViewById(R.id.rough);
		random = (LinearLayout) view.findViewById(R.id.random);
		menu = (LinearLayout) view.findViewById(R.id.menu);
		imageDownLoader = new ImageDownLoader();
		if (Utils.isLand(getActivity())) {
			SharedPreferences sp = getActivity().getSharedPreferences("mstx",
					Context.MODE_PRIVATE);
			TextView name = (TextView) view.findViewById(R.id.name);
			name.setText(sp.getString("username", null));
			uid = sp.getString("username", null);
			imageView = (RoundImageView) view.findViewById(R.id.imageView);
			uidPic = sp.getString("sculpture", null);
			imageDownLoader.imgExcute(imageView, uidPic);
			imageView.setOnClickListener(this);
			imageDownLoader.cancelTask();
			atten.setText(sp.getString("attention", null));
			fans.setText(sp.getString("fans", null));
			exit.setText("退出登录");
		}

		exit.setOnClickListener(this);
		// atten.setOnClickListener(this);
		// fans.setOnClickListener(this);
		menu.setOnClickListener(this);
		random.setOnClickListener(this);
		collection.setOnClickListener(this);
		rough.setOnClickListener(this);
		return view;
	}

	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
	}

	@Override
	public void onClick(View v) {
		if (v == exit) {
			PopupWindow exit = new exitPopupWindow(getActivity(),"您确认要退出登录吗？",
					new OnClickListener() {
						@Override
						public void onClick(View v) {
							if (v.getId() == R.id.ok_bt) {
								exitUserInfo();
								getActivity().finish();
							}
						}
					});
			exit.showAtLocation(getActivity().findViewById(R.id.me_fragment2),
					Gravity.CENTER, 0, 0);
			return;
		} else if (v == imageView) {
			Intent intent = new Intent(getActivity(), PreviewSingleImage.class);
//			int[] location = new int[2];
//			imageView.getLocationOnScreen(location);
			intent.putExtra("picName", uidPic);
//			intent.putExtra("locationX", location[0]);// 必须
//			intent.putExtra("locationY", location[1]);// 必须
//			intent.putExtra("width", imageView.getWidth());// 必须
//			intent.putExtra("height", imageView.getHeight());// 必须
			startActivity(intent);
//			getActivity().overridePendingTransition(0, 0);
			getActivity().overridePendingTransition(R.anim.fade, R.anim.hold);

			return;
		}
		if (!Utils.isNewWork(getActivity())) {
			toast.showToast("网络已中断,联网后重试");
			return;
		} 
		if (v == collection) {
			Intent intent = new Intent(getActivity(), CollectionActivity.class);
			startActivity(intent);
		} else if (v == rough) {
			toast.showToast("待开发");
		} else if (v == random) {
			Intent intent = new Intent(getActivity(),
					RandomListViewActivity.class);
			intent.putExtra("type", Constant.RANDOM_BY_UID);
			intent.putExtra("arg", uid);
			intent.putExtra("title", "我的随拍");
			startActivity(intent);
		} else if (v == menu) {
			Intent intent = new Intent(getActivity(),
					MenuChooseListActivity.class);
			intent.putExtra("type", Constant.MENU_BY_UID);
			intent.putExtra("args", uid);
			intent.putExtra("title", "我的菜品");
			startActivity(intent);
		}
	}
	// 退出时删除用户名和密码
	private void exitUserInfo() {
		SharedPreferences sp = getActivity().getSharedPreferences("mstx",
				Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = sp.edit();
		editor.remove("username");
		editor.remove("password");
		editor.commit();
	}
}