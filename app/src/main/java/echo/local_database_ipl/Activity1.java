package echo.local_database_ipl;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import java.util.ArrayList;

public class Activity1 extends ActionBarActivity {

    static int k=0;
    Button info,disp,clear;
    DBHelper db;
    ArrayList arr_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity1);
        info=(Button)findViewById(R.id.info);
        disp=(Button)findViewById(R.id.disp);
        clear=(Button)findViewById(R.id.clr);
        db= new DBHelper(this);
        disp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(db.numberOfRows()!=0){
                     arr_list = getIntent().getStringArrayListExtra("arr_list");
                    if(!arr_list.isEmpty()) {
                        for (int i = 0; i < arr_list.size(); i++) {
                            System.out.println((arr_list.get(i)));
                        }
                    }
                }
                else{
                    Toast.makeText(Activity1.this,"No data added , add some info",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent i= new Intent(Activity1.this,Activity2.class);
                startActivity(i);


            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(db.numberOfRows()!=0){
                        db.removeAll();
                }
                else{
                    Toast.makeText(Activity1.this,"List already empty",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity1, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
