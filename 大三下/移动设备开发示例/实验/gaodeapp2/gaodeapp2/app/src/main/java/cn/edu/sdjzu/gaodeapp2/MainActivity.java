package cn.edu.sdjzu.gaodeapp2;

import android.app.Activity;
import android.os.Bundle;
import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.model.LatLng;

public class MainActivity extends Activity {
    private MapView mapView;
    private AMap aMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mapView = (MapView) findViewById(R.id.location);
        mapView.onCreate(savedInstanceState);//必须要写
        init();
        //北京天安门的纬度为39.906901，经度为116.397972117.022194。
        //山东建筑大学本校区东南门的纬度为36.683333，经度为117.188292。
        //设置新的默认经纬度
        LatLng latLng = new LatLng(36.683333,117.188292);
        aMap.moveCamera(CameraUpdateFactory.changeLatLng(latLng));
        //设置新的默认缩放级别
        aMap.moveCamera(CameraUpdateFactory.zoomTo(16));
    }

    /**
     * 初始化AMap对象
     */
    private void init() {
        if (aMap == null) {
            aMap = mapView.getMap();
        }
    }
    /**
     * 方法必须重写
     */
    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }
    /**
     * 方法必须重写
     */
    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }
    /**
     * 方法必须重写
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }
    /**
     * 方法必须重写
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }
}

