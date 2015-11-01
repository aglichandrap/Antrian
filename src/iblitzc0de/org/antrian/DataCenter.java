package iblitzc0de.org.antrian;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;


public class DataCenter extends SQLiteOpenHelper {
	
	private static final String DATABASE_NAME = "inventory.db";
	private static final int DATABASE_VERSION = 1;
	public DataCenter(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		String sql = "create table barang(kode integer primary key not null, nama text null, ket text null);";
		Log.d("Data", "onCreate: " + sql);
		db.execSQL(sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}

}
