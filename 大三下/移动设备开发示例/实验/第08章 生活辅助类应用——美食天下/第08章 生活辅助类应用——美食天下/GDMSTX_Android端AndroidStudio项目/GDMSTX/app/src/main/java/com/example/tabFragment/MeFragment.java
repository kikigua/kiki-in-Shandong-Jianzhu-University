package com.example.tabFragment;
import com.example.activity.R;
import com.example.fragment.Login;
import com.example.fragment.NoLand;
import com.example.util.Utils;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.FragmentTransaction;
/**
 * 我的 界面
 */
public class MeFragment extends Fragment {
	
	FragmentTransaction fragmentTransaction;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.me_fragment, container, false);
		return v;
	}
	public void changeFragment(FragmentTransaction fragmentTransaction,Fragment fragment)
	{
		fragmentTransaction.replace(R.id.fragment,fragment);
		fragmentTransaction.commit();
	}
	@Override
	public void onStart() {
		fragmentTransaction = getFragmentManager().beginTransaction();
		if(Utils.isLand(getActivity()))
		{		//如果登陆、则加载登陆完的界面
			Login fragment=new Login();
			changeFragment(fragmentTransaction, fragment);		
		}
		else {
			//如果没有登陆,加载点击登陆或注册的页面
			NoLand fragment=new NoLand();
			changeFragment(fragmentTransaction, fragment);
		}
		super.onStart();
	}
}