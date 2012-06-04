package com.android.liujun;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;


import android.app.Activity;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class CarDiagnosticsActivity extends Activity {
    /** Called when the activity is first created. */
	private static final boolean JAY_DEBUG = true;
	private static final String TAG = "CarDiagnoticsActivity";

	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        GridView gridview = (GridView) findViewById(R.id.gridview);
        
        //���ɶ�̬���飬����ת������
        ArrayList<HashMap<String, Object>> lstImageItem = new ArrayList<HashMap<String, Object>>();
//        for(int i=0;i<10;i++)
//        {
//        	HashMap<String, Object> map = new HashMap<String, Object>();
//        	map.put("ItemImage", R.drawable.temperature);//���ͼ����Դ��ID
//			map.put("ItemText", "NO."+String.valueOf(i));//�������ItemText
//        	lstImageItem.add(map);
//        }
    	HashMap<String, Object> map1 = new HashMap<String, Object>();
    	map1.put("ItemImage", R.drawable.check);//���ͼ����Դ��ID
		map1.put("ItemIndex", 1);//�������ItemText
    	lstImageItem.add(map1);
    	HashMap<String, Object> map2 = new HashMap<String, Object>();
    	map2.put("ItemImage", R.drawable.temperature);//���ͼ����Դ��ID
		map2.put("ItemIndex", 2);//�������ItemText
    	lstImageItem.add(map2);
    	HashMap<String, Object> map3 = new HashMap<String, Object>();
    	map3.put("ItemImage", R.drawable.speed);//���ͼ����Դ��ID
		map3.put("ItemIndex", 3);//�������ItemText
    	lstImageItem.add(map3);
    	
    	
        //������������ImageItem <====> ��̬�����Ԫ�أ�����һһ��Ӧ
        SimpleAdapter saImageItems = new SimpleAdapter(this, //ûʲô����
        		                                    lstImageItem,//������Դ 
        		                                    R.layout.item,//night_item��XMLʵ��
        		                                    
        		                                    //��̬������ImageItem��Ӧ������        
        		                                    new String[] {"ItemImage"}, 
        		                                    
        		                                    //ImageItem��XML�ļ������һ��ImageView,����TextView ID
        		                                    new int[] {R.id.ItemImage}
        );
        //��Ӳ�����ʾ
        gridview.setAdapter(saImageItems);
        //�����Ϣ����
        gridview.setOnItemClickListener(new ItemClickListener());
    }
    
  //��AdapterView������(���������߼���)���򷵻ص�Item�����¼�
    class  ItemClickListener implements OnItemClickListener
    {
    	public void onItemClick(AdapterView<?> arg0,//The AdapterView where the click happened 
    			                          View arg1,//The view within the AdapterView that was clicked
    			                          int arg2,//The position of the view in the adapter
    			                          long arg3//The row id of the item that was clicked
    			                          ) {
    		//�ڱ�����arg2=arg3
    		HashMap<String, Object> item=(HashMap<String, Object>) arg0.getItemAtPosition(arg2);
    		//��ʾ��ѡItem��ItemText
    		int index = (Integer) item.get("ItemIndex");
    		switch (index) {
    		case 2:
    	    	Intent mIntent = new Intent();
    	    	mIntent.setClass(CarDiagnosticsActivity.this, Temperature.class);
    	    	startActivity(mIntent);
    			break;
    		case 3:
    			Intent intentSpeed = new Intent();
    			intentSpeed.setClass(CarDiagnosticsActivity.this, CarSpeed.class);
    			startActivity(intentSpeed);
    			break;

    		default:
    			break;
    		}
    	}
    }  
	
	@Override
	protected void onResume() {

		// TODO Auto-generated method stub
		super.onResume();
    	if(JAY_DEBUG) {
    		Log.e(TAG, "**on resume**");
    	}

	}
	

	
	@Override
	protected void onDestroy() {

		// TODO Auto-generated method stub
		super.onDestroy();
    	if(JAY_DEBUG) {
    		Log.e(TAG, "**on destory**");
    	}
	}
    
}
