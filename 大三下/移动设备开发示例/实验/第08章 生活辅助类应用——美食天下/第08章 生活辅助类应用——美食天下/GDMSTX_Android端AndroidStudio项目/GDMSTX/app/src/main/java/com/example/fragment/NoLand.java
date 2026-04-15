package com.example.fragment;

import com.example.activity.Land;
import com.example.activity.R;
import com.example.activity.Register;
import com.example.util.Utils;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class NoLand extends Fragment implements OnClickListener
{
	Button login;
	Button register;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v=inflater.inflate(R.layout.me_noland, null);
		login=(Button) v.findViewById(R.id.land_bt);
		register=(Button) v.findViewById(R.id.regitster_bt);
		login.setText("登录");
		login.setOnClickListener(this);
		register.setOnClickListener(this);				
		return v;
	}

	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
	}

	@Override
	public void onClick(View v) {
		if (v == login) {
			if (!Utils.isLand(getActivity())) {
				Intent intent = new Intent(getActivity(), Land.class);
				getActivity().startActivity(intent);
				return;
			}
		}else if(v==register)
		{
			if (!Utils.isLand(getActivity())) {
				Intent intent = new Intent(getActivity(), Register.class);
				getActivity().startActivity(intent);
				return;
			}
		}

	}
	
}