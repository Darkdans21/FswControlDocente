package com.example.fingerprint.fragment;


import com.example.fingerprint.MainActivity;
import com.example.fingerprint.R;
import com.rscja.deviceapi.Fingerprint.BufferEnum;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;

public class IdentificationFragment extends Fragment {
	private MainActivity mContext;

	private static final String TAG = "IdentificationFragment";

	EditText etPageId;
	Button btnIdent;
	CheckBox cbShowImg;
	ImageView ivFinger;
	EditText etScore;


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		Log.i(TAG, "onCreateView()");

		View v = inflater.inflate(R.layout.fingerprint_identification_fragment,
				container, false);
		return v;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		mContext = (MainActivity) getActivity();
		init();
	}

	private void init() {

		etPageId= (EditText) mContext.findViewById(R.id.etPageId);		
		btnIdent= (Button) mContext.findViewById(R.id.btnIdent);		
		cbShowImg= (CheckBox) mContext.findViewById(R.id.cbShowImg);		
		ivFinger= (ImageView) mContext.findViewById(R.id.ivFinger);		
		etScore= (EditText) mContext.findViewById(R.id.etScore);		
		
		btnIdent.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				btnIdent_onClick(v);
				
			}
		});


		
	}

	public void btnIdent_onClick(View v) {
		new IdentTask().execute(cbShowImg.isChecked() ? 1 : 0);
	}

	class IdentTask extends AsyncTask<Integer, Integer, String> {
		ProgressDialog mypDialog;

		int searchPageID = -1;
		int searchScore = -1;
		String searchName = "";

		@Override
		protected String doInBackground(Integer... params) {

			if (!mContext.mFingerprint.getImage()) {
				return null;
			}

			if (mContext.mFingerprint.genChar(BufferEnum.B1)) {
				int[] result = null;
				int exeCount = 0;

				do {
					exeCount++;
					result = mContext.mFingerprint
							.search(BufferEnum.B1, 0, 255);

				} while (result == null && exeCount < 3);

				Log.i(TAG, "exeCount=" + exeCount);

				if (result != null) {
					searchPageID = result[0];
					searchScore = result[1];

					if (params[0] == 1) {
						// 显示指纹图片
						if (mContext.mFingerprint.getImage()) {
							if (mContext.mFingerprint.upImage(1,
									mContext.getFilesDir() + "/finger.bmp") != -1) {
								return "img-ok";

							} else {
								return "img-fail";
							}
						} else {
							return "img-fail";
						}
					}
					return "ok";
				}

				Log.i(TAG, "search result Empty");
			}

			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);

			mypDialog.cancel();

			if (TextUtils.isEmpty(result)) {
				Toast.makeText(mContext, R.string.fingerprint_msg_ident_fail,
						Toast.LENGTH_SHORT).show();

				
				return;
			} else if (result.equals("img-fail")) {

				
				Toast.makeText(mContext, R.string.fingerprint_title_get_img_fail,
						Toast.LENGTH_SHORT).show();
				
				return;

			} else if (result.equals("img-ok")) {
				Bitmap bitmap = BitmapFactory.decodeFile(mContext.getFilesDir()
						+ "/finger.bmp");
				ivFinger.setImageBitmap(bitmap);
			}

			etPageId.setText(searchPageID + "");
			etScore.setText(searchScore + "");

			
			Toast.makeText(mContext, R.string.fingerprint_msg_ident_succ,
					Toast.LENGTH_SHORT).show();

			
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();

			mypDialog = new ProgressDialog(mContext);
			mypDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
			mypDialog.setCanceledOnTouchOutside(false);
			mypDialog.show();

			etPageId.setText(null);
			etScore.setText(null);

			ivFinger.setImageBitmap(null);
		}

		@Override
		protected void onProgressUpdate(Integer... values) {
			super.onProgressUpdate(values);
		}

	};

}
