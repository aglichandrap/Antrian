package iblitzc0de.org.antrian;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import org.iblitzc0de.antrian.R;

public class MainActivity extends Activity {
	String[] daftar; 
	ListView ListView01;
	Menu menu;
	protected Cursor cursor;
	DataCenter dbcenter;
	public static MainActivity ma;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Button ton=(Button)findViewById(R.id.button2);
		Button back=(Button)findViewById(R.id.button1);
		ton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent inte = new Intent(MainActivity.this, BaruActivity.class);
				startActivity(inte);
			}
		});
		back.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		
		ma = this;
        dbcenter = new DataCenter(this);
        RefreshList();
	}
	
	public void RefreshList(){
    	SQLiteDatabase db = dbcenter.getReadableDatabase();
    	cursor = db.rawQuery("SELECT * FROM barang ORDER BY kode DESC",null);
    	daftar = new String[cursor.getCount()];
    	cursor.moveToFirst();
    	for (int cc=0; cc < cursor.getCount(); cc++){
    		cursor.moveToPosition(cc);
    		daftar[cc] = cursor.getString(1).toString();
    	}
    	ListView01 = (ListView)findViewById(R.id.listView1);
    	ListView01.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, daftar));
    	ListView01.setSelected(true);
    	ListView01.setOnItemClickListener(new OnItemClickListener() {
    
    		
    		@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
    			final String selection = daftar[arg2]; //.getItemAtPosition(arg2).toString();
    			final CharSequence[] dialogitem = {"View", "Delete"};
    			AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
    			builder.setTitle("Pilih Menu");
    			builder.setItems(dialogitem, new DialogInterface.OnClickListener() {
    				@Override
					public void onClick(DialogInterface dialog, int item) {
    					switch(item){
    					case 0 :
    						Intent i = new Intent(getApplicationContext(), LihatActivity.class);
    						i.putExtra("nama", selection);
    						startActivity(i);
    						break;
    					case 1 :
    						SQLiteDatabase db = dbcenter.getWritableDatabase();
    						db.execSQL("delete from barang where nama = '"+selection+"'");
    						RefreshList();
    						break;

    					}
    				}
    			});
    			builder.create().show();
    		}});
    		((ArrayAdapter)ListView01.getAdapter()).notifyDataSetInvalidated();
    	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
