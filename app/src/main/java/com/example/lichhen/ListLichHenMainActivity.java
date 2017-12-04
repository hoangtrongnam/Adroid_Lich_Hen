package com.example.lichhen;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by hoang on 12/3/2017.
 */

@SuppressLint("NewApi")public class ListLichHenMainActivity extends Activity {

    static final int DATE_DIALOG_ID=0;
    private CustomAdapter mCustomAdapter;
    private ListView mListView;
    Toast mToast;
    private ArrayList<LichHen> _Contacts = new ArrayList<LichHen>();


    DatabaseHandle db;
    LichHen contacts;

    @SuppressLint("NewApi") @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_lich_hen_main);
        ActionBar actionBar = getActionBar();
//        actionBar.setDisplayHomeAsUpEnabled(true);
        db = new DatabaseHandle(this);
        db.open();


        for (int i = 0; i < db.lichhen().size(); i++) {
            contacts = new LichHen();
            contacts.setTime(db.lichhen().get(i).getTime());
            contacts.setTen(db.lichhen().get(i).getTen());
            contacts.setChuthich(db.lichhen().get(i).getChuthich());
            _Contacts.add(contacts);

        }
        mListView = (ListView) findViewById(R.id.listView1);

        mCustomAdapter = new CustomAdapter(ListLichHenMainActivity.this,
                _Contacts);
        mListView.setAdapter(mCustomAdapter);
        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view,
                                           int position, long id) {
                // TODO Auto-generated method stub
                delcaidat(position);

                return false;
            }
        });

    }



    //xữ lý khi tì vào hiện lên dialog hỏi xác nhận có xóa không
    public void delcaidat(final int position) {
        new AlertDialog.Builder(ListLichHenMainActivity.this)
                .setTitle("Chú ý")
                .setMessage("Bạn có chắc xóa không")
                .setIcon(R.drawable.xoa)
                .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @TargetApi(11)
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        LayoutInflater inflater = getLayoutInflater();
                        View mToastView = inflater.inflate(R.layout.xoa_lichhen,
                                null);
                        mToast = new Toast(ListLichHenMainActivity.this);
                        mToast.setView(mToastView);
                        mToast.show();
                        db.Deletels(
                                db.lichhen().get(position).getTime(),
                                db.lichhen().get(position).getTen(),
                                db.lichhen().get(position).getChuthich()
                        );
                        Intent intent = new Intent(getApplicationContext(),
                                MainActivity.class);
                        finish();
                        startActivity(intent);
                    }
                })
                .setNegativeButton("Không",
                        new DialogInterface.OnClickListener() {
                            @TargetApi(11)
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        }).show();

    }
}
