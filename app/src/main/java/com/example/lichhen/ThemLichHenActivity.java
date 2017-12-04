package com.example.lichhen;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ThemLichHenActivity extends Activity {
Button luu;
EditText ngaythang , ten, chuthich;
Button pickerthemhen;
static final int DATE_DIALOG_ID = 0;
private int mYear,mMonth,mDay;
DatabaseHandle db;
String ngay;
String thang;
Toast mToast;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_them_lich_hen);
		db = new DatabaseHandle(this);
		db.open();
		luu =(Button)findViewById(R.id.savehen);
		ngaythang = (EditText)findViewById(R.id.edngayhen);
		ten = (EditText) findViewById(R.id.edten);
		chuthich = (EditText) findViewById(R.id.ednote);

		pickerthemhen = (Button) findViewById(R.id.daypickerthemlichhen);
		ngaythang.setEnabled(false);
		ngaythang.setFocusable(false);
		Calendar c = Calendar.getInstance();
		mYear = c.get(Calendar.YEAR);
		mMonth = c.get(Calendar.MONTH);
		mDay = c.get(Calendar.DATE);

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		ngaythang.setText(sdf.format(c.getTime()));

		pickerthemhen.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				showDialog(DATE_DIALOG_ID);


			}
		});
		luu.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				if(ngaythang.getText().length()<1||ten.getText().length()<1||chuthich.getText().length()<1){
					Toast.makeText(getApplicationContext(), "Bạn cần nhập đầy đủ thông tin.", Toast.LENGTH_SHORT).show();
					ten.requestFocus();

				}else{
					if(mDay<10){
						ngay="0"+mDay;

					}else{
						ngay=mDay+"";

					}
					if((mMonth+1)<10){
						thang="0"+(mMonth+1);

					}else{
						thang=(mMonth+1)+"";

					}


					db.themlichhen(
							ngaythang.getText().toString(),
							ten.getText().toString(),
							chuthich.getText().toString(),
							ngay+"",""+thang,mYear+""
					);
					db.close();

					LayoutInflater inflater = getLayoutInflater();
					View mToastView = inflater.inflate(R.layout.luuthanhcong,
							null);
					mToast = new Toast(ThemLichHenActivity.this);
					mToast.setView(mToastView);
					mToast.show();
					Intent intent = new Intent(getApplicationContext(), MainActivity.class);
					startActivityForResult(intent, 8);
					finish();
				}

			}
		});
	}

	 protected Dialog onCreateDialog(int id) {
         switch (id) {
         case DATE_DIALOG_ID:
             return new DatePickerDialog(this,
                         mDateSetListener,
                         mYear, mMonth, mDay);

         }

         return null;

     }
     private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {

         public void onDateSet(DatePicker view, int year, int monthOfYear,
                 int dayOfMonth) {
             mYear = year;
             mMonth = monthOfYear;
             mDay = dayOfMonth;
             ngaythang.setText(new StringBuilder().append(mDay).append("/").append(mMonth+1).append("/").append(mYear));

         }

     };




	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub

	}
	
}
