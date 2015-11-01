package iblitzc0de.org.antrian;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.iblitzc0de.antrian.R;

public class LihatActivity extends Activity {
	protected Cursor cursor;
	DataCenter dbHelper;
	Button ton2,BtnNext;
	TextView text1, text2, text3;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lihat);
		
		dbHelper = new DataCenter(this);
		text1 = (TextView) findViewById(R.id.textView1);
		text2 = (TextView) findViewById(R.id.textView2);
		text3 = (TextView) findViewById(R.id.textView3);

		SQLiteDatabase db = dbHelper.getReadableDatabase();
		cursor = db.rawQuery("SELECT * FROM barang WHERE nama = '" +
		getIntent().getStringExtra("nama") + "'",null);
		cursor.moveToFirst();
		if (cursor.getCount()>0)
		{
			cursor.moveToPosition(0);
			text1.setText(cursor.getString(0).toString());
			text2.setText(cursor.getString(1).toString());
			text3.setText(cursor.getString(2).toString());

		}
		ton2 = (Button) findViewById(R.id.button1);
		BtnNext=(Button) findViewById(R.id.BtnNext);
		ton2.setOnClickListener(new View.OnClickListener() 
		{	
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.lihat, menu);
		return true;
	}

}
