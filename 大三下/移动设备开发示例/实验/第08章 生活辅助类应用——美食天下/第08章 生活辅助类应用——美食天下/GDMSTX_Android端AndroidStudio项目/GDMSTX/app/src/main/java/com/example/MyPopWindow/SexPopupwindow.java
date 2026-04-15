package com.example.MyPopWindow;
import com.example.activity.R;
import kankan.wheel.widget.OnWheelChangedListener;
import kankan.wheel.widget.WheelView;
import kankan.wheel.widget.adapters.ArrayWheelAdapter;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
/**
 * 	性别选择
 */
public class SexPopupwindow extends PopupWindow implements OnWheelChangedListener
{
	private WheelView mViewProvince;
	private Button mBtnConfirm;
	private String[] mProvinceDatas=new String[]{"男","女","保密"};
	private String mCurrentProviceName;
	public SexPopupwindow(Context context,final A listener)
	{
		setWidth(LayoutParams.MATCH_PARENT);
		setHeight(LayoutParams.MATCH_PARENT);
		setFocusable(true);
		setOutsideTouchable(true);
		View view = LayoutInflater.from(context).inflate(R.layout.sex_popupwindow, null);
		RelativeLayout rl=(RelativeLayout) view.findViewById(R.id.parent);
		rl.setOnClickListener(
				new OnClickListener()
				{
					@Override
					public void onClick(View v) {
						dismiss();
						
					}
					
				});
		setContentView(view);		
		mViewProvince = (WheelView) view.findViewById(R.id.wheelview);
		mBtnConfirm = (Button) view.findViewById(R.id.btn_confirm);
		mViewProvince.addChangingListener(this);
    	mBtnConfirm.setOnClickListener(
    			new OnClickListener()
    			{
					@Override
					public void onClick(View v) {						// TODO Auto-generated method stub
						listener.onSelected(mCurrentProviceName);
					}    				
    			});    	
    	mViewProvince.setViewAdapter(new ArrayWheelAdapter<String>(context, mProvinceDatas));
		mCurrentProviceName=mProvinceDatas[0];
		// 设置可见条目数量
		mViewProvince.setVisibleItems(7);
	}
	@Override
	public void onChanged(WheelView wheel, int oldValue, int newValue) {
		int pCurrent = mViewProvince.getCurrentItem();
		mCurrentProviceName = mProvinceDatas[pCurrent];
	}
	public interface A{ 
		public void onSelected(String mCurrentProviceName);
	};
}