package com.example.fingerprint;


import com.example.fingerprint.fragment.AcquisitionFragment;
import com.example.fingerprint.fragment.IdentificationFragment;
import com.rscja.deviceapi.Fingerprint;
import com.rscja.deviceapi.RFIDWithUHF;
import com.rscja.deviceapi.exception.ConfigurationException;
import com.rscja.utility.StringUtility;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTabHost;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.os.Build;

/**
 * 指纹模块使用demo
 * 
 * 1、使用前请确认您的机器已安装此模块。 
 * 2、要正常使用模块需要在\libs\armeabi\目录放置libDeviceAPI.so文件，同时在\libs\目录下放置DeviceAPI.jar文件。 
 * 3、在操作设备前需要调用 init()打开设备，使用完后调用 free() 关闭设备
 * 
 * 
 * 更多函数的使用方法请查看API说明文档
 * 
 * @author liuruifeng 
 */
public class MainActivity extends FragmentActivity {

	private final static String TAG = "MainActivity";

	public Fingerprint mFingerprint;

	private FragmentTabHost mTabHost;
	private FragmentManager fm;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);

		fm = getSupportFragmentManager();
		mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
		mTabHost.setup(this, fm, R.id.realtabcontent);

		mTabHost.addTab(
				mTabHost.newTabSpec("identification").setIndicator(
						getString(R.string.fingerprint_tab_identification)),
				IdentificationFragment.class, null);
		mTabHost.addTab(
				mTabHost.newTabSpec("acquisition").setIndicator(
						getString(R.string.fingerprint_tab_acquisition)),
				AcquisitionFragment.class, null);
		

		try {

			mFingerprint = Fingerprint.getInstance();

		} catch (Exception ex) {
			Toast.makeText(MainActivity.this, ex.getMessage(),
					Toast.LENGTH_SHORT).show();
			return;
		}

	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();

		if (mFingerprint != null) {
			mFingerprint.free();
		}
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

		new InitTask().execute();
	}

	/**
	 * 设备上电异步类
	 * 
	 * @author liuruifeng 
	 */	
	public class InitTask extends AsyncTask<String, Integer, Boolean> {
		ProgressDialog mypDialog;
		
		@Override
		protected Boolean doInBackground(String... params) {
			// TODO Auto-generated method stub
			return mFingerprint.init();
		}

		@Override
		protected void onPostExecute(Boolean result) {
			super.onPostExecute(result);

			mypDialog.cancel();

			if (!result) {
				Toast.makeText(MainActivity.this, "init fail",
						Toast.LENGTH_SHORT).show();
			}
		}

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();

			mypDialog = new ProgressDialog(MainActivity.this);
			mypDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
			mypDialog.setMessage("init...");
			mypDialog.setCanceledOnTouchOutside(false);
			mypDialog.show();
		}

	}

	

}
