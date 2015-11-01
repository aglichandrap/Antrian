package iblitzc0de.org.antrian;

import org.iblitzc0de.antrian.R;

import android.os.Bundle;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class BaruActivity extends Activity {
	protected Cursor cursor;
	DataCenter dbHelper;
	Button ton1, ton2,btngbr;
	EditText text1, text2, text3;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_baru);
		
		dbHelper = new DataCenter(this);
		text1 = (EditText) findViewById(R.id.editText1);
		text2 = (EditText) findViewById(R.id.editText2);
		text3 = (EditText) findViewById(R.id.editText3);

		ton1 = (Button) findViewById(R.id.button1);
		ton2 = (Button) findViewById(R.id.button2);
		btngbr=(Button) findViewById(R.id.btngbr);
		// daftarkan even onClick pada btnSimpan
		ton1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				SQLiteDatabase db = dbHelper.getWritableDatabase();
				db.execSQL("insert into barang(kode, nama, ket) values('" +
						text1.getText().toString()+"','"+
						text2.getText().toString() +"','" +
						text3.getText().toString()+"')");
						Toast.makeText(getApplicationContext(), "Berhasil", Toast.LENGTH_LONG).show();
						MainActivity.ma.RefreshList();
						finish();
				}
			});
		ton2.setOnClickListener(new View.OnClickListener() {
			
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
		getMenuInflater().inflate(R.menu.baru, menu);
		return true;
	}

}
