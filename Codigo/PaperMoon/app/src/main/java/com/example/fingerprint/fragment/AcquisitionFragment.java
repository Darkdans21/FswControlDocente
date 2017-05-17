package com.example.fingerprint.fragment;

import java.util.List;

import com.example.fingerprint.MainActivity;
import com.example.fingerprint.R;
import com.rscja.deviceapi.Fingerprint.BufferEnum;
import com.rscja.utility.StringUtility;

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
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

public class AcquisitionFragment extends Fragment {

	private static final String TAG = "AcquisitionFragment";

	private MainActivity mContext;

	EditText etPageId;
	Button btnSave;
	CheckBox cbShowImg;
	ImageView ivFinger;
	RadioButton rbDef;
	RadioButton rbIso;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fingerprint_acquisition_fragment,
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

		etPageId = (EditText) mContext.findViewById(R.id.etPageId);
		btnSave = (Button) mContext.findViewById(R.id.btnSave);
		cbShowImg = (CheckBox) mContext.findViewById(R.id.cbShowImg);
		ivFinger = (ImageView) mContext.findViewById(R.id.ivFinger);
		rbDef = (RadioButton) mContext.findViewById(R.id.rbDef);
		rbIso = (RadioButton) mContext.findViewById(R.id.rbIso);

		btnSave.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				btnSave_onClick(v);

			}
		});

	}

	public void btnSave_onClick(View v) {
		Log.i(TAG, "btnSave_onClick()");

		String pageId = etPageId.getText().toString().trim();

		if (TextUtils.isEmpty(pageId)) {

			Toast.makeText(mContext, R.string.fingerprint_msg_page_id_not_null,
					Toast.LENGTH_SHORT).show();

			return;
		}

		if (!TextUtils.isDigitsOnly(pageId)) {

			Toast.makeText(mContext,
					R.string.fingerprint_msg_page_id_need_digits,
					Toast.LENGTH_SHORT).show();

			return;
		}

		int iPageId = Integer.parseInt(pageId);

		if (iPageId < 0 || iPageId > 254) {

			Toast.makeText(mContext,
					R.string.fingerprint_msg_page_id_need_0_to_254,
					Toast.LENGTH_SHORT).show();

			return;
		}

		new AcqTask(iPageId, "", cbShowImg.isChecked()).execute(iPageId,
				cbShowImg.isChecked() ? 1 : 0);
	}

	class AcqTask extends AsyncTask<Integer, Integer, String> {
		ProgressDialog mypDialog;

		int pid;
		String uname;
		boolean isShowImg;
		String data;

		public AcqTask(int pageId, String name, boolean showImg) {
			pid = pageId;
			uname = name;
			isShowImg = showImg;
		}

		@Override
		protected String doInBackground(Integer... params) {

			boolean exeSucc = false;

			// 采集指纹
			if (!mContext.mFingerprint.getImage()) {
				return null;
			}

			// 生成特征值到B1
			if (mContext.mFingerprint.genChar(BufferEnum.B1)) {
				exeSucc = true;
			}

			// 再次采集指纹
			if (!mContext.mFingerprint.getImage()) {
				return null;
			}

			// 生成特征值到B2
			if (mContext.mFingerprint.genChar(BufferEnum.B2)) {
				exeSucc = true;
			}

			// 合并两个缓冲区到B1
			if (mContext.mFingerprint.regModel()) {
				exeSucc = true;
			}

			if (exeSucc) {
				if (mContext.mFingerprint.storChar(BufferEnum.B1, pid)) {

					// 判断是否定制模块
					if (rbIso.isChecked()) {
						data = mContext.mFingerprint.upChar(BufferEnum.B11);
					} else {
						data = mContext.mFingerprint.upChar(BufferEnum.B1);
					}

					if (isShowImg) {
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
				}
				return "ok";
			}

			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);

			mypDialog.cancel();

			if (TextUtils.isEmpty(result)) {

				Toast.makeText(mContext, R.string.fingerprint_msg_acq_fail,
						Toast.LENGTH_SHORT).show();
				return;
			} else if (result.equals("img-fail")) {

				Toast.makeText(mContext,
						R.string.fingerprint_title_get_img_fail,
						Toast.LENGTH_SHORT).show();
				return;

			} else if (result.equals("img-ok")) {
				Bitmap bitmap = BitmapFactory.decodeFile(mContext.getFilesDir()
						+ "/finger.bmp");
				ivFinger.setImageBitmap(bitmap);
			}

			Toast.makeText(mContext, R.string.fingerprint_msg_acq_succ,
					Toast.LENGTH_SHORT).show();

		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();

			mypDialog = new ProgressDialog(mContext);
			mypDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
			mypDialog.setCanceledOnTouchOutside(false);
			mypDialog.show();

			ivFinger.setImageBitmap(null);
		}

		@Override
		protected void onProgressUpdate(Integer... values) {
			super.onProgressUpdate(values);
		}

	};
}
