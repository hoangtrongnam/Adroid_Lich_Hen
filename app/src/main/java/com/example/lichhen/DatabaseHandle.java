package com.example.lichhen;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandle {

	private static final String DATABASE_NAME = "lichhenbacsi";
	private static final int DATABASE_ver = 1;
	static final String TABLE_NAME = "LichHen";
	static final String COLUM_ID = "id";
	static final String COLUM_NGAYHEN = "ngayhen";
	static final String COLUM_TEN = "ten";
	static final String COLUM_CHUTHICH = "chuthich";
	static final String COLUM_NGAY = "ngay";
	static final String COLUM_THANG = "thang";
	static final String COLUM_NAM = "nam";
	private Context _context;
	private static Context context;
	static SQLiteDatabase db;
	SQLiteDatabase db1;
	static OpenHelper openHelper;

	public DatabaseHandle(Context context) {
		DatabaseHandle.context = context;
	}
	public DatabaseHandle open() throws SQLException {
		openHelper = new OpenHelper(context);
		db = openHelper.getWritableDatabase();

		return this;
	}
	public void close() {
		openHelper.close();
	}
	public void Deletels(String ngayhen, String ten, String chuthich) {

		db.execSQL("DELETE FROM " + TABLE_NAME + " WHERE " + COLUM_NGAYHEN
				+ "='" + ngayhen + "'" + " AND " + COLUM_TEN + " = '"
				+ ten + "'" + " AND " + COLUM_CHUTHICH + " = '" + chuthich + "'"
		);
		db.close();
	}
	public long themlichhen(String ngayhen, String ten, String chuthich,
							String ngay, String thang, String nam) {
		ContentValues cv1 = new ContentValues();
		cv1.put(COLUM_NGAYHEN, ngayhen);
		cv1.put(COLUM_TEN, ten);
		cv1.put(COLUM_CHUTHICH, chuthich);
		cv1.put(COLUM_NGAY, ngay);
		cv1.put(COLUM_THANG, thang);
		cv1.put(COLUM_NAM, nam);

		return db.insert(TABLE_NAME, null, cv1);

	}
	static class OpenHelper extends SQLiteOpenHelper {

		public OpenHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_ver);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			db.execSQL("create table " + TABLE_NAME + " ( " + COLUM_ID
					+ " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ,"
					+ COLUM_NGAYHEN + " text,"
					+ COLUM_TEN + " text,"
					+ COLUM_CHUTHICH + " text,"
					+ COLUM_NGAY + " text,"
					+ COLUM_THANG + " text,"
					+ COLUM_NAM + " text);"

			);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			db.execSQL("DROP TABLE IF EXITS " + TABLE_NAME);
			onCreate(db);
		}

	}
	///////////////////////////////////////////
	public List<LichHen> lichhen() {
		// TODO Auto-generated method stub
		ArrayList<LichHen> lichhen = new ArrayList<LichHen>();
		String selectQuery = "SELECT " + COLUM_NGAYHEN + "," + COLUM_TEN
				+ "," + COLUM_CHUTHICH + " FROM "
				+ TABLE_NAME;
		Cursor cursor = db.rawQuery(selectQuery, null);

		if (cursor.moveToFirst()) {
			do {

				LichHen contacts = new LichHen();
				contacts.setTime(cursor.getString(0));
				contacts.setTen(cursor.getString(1));
				contacts.setChuthich(cursor.getString(2));

				lichhen.add(contacts);

			} while (cursor.moveToNext());
		}
		cursor.close();
		return lichhen;
	}
	///////////////////////////////////////////
}
