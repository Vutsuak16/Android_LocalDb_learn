package echo.local_database_ipl;


import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

import echo.local_database_ipl.DBHelper;

public class Activity2 extends ActionBarActivity {

    Button add, view;
    EditText name, franchise, price;
    DBHelper myDb;
    static ArrayList arr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity2);
        myDb= new DBHelper(this);
        add = (Button) findViewById(R.id.add);
        view = (Button) findViewById(R.id.view);
        name = (EditText) findViewById(R.id.name);
        franchise = (EditText) findViewById(R.id.franchise);
        price = (EditText) findViewById(R.id.price);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Name = name.getText().toString();
                String Team = franchise.getText().toString();
                String Worth = price.getText().toString();
                myDb.insertPlayer(Name, Team, Worth);
                name.setText(null);
                franchise.setText(null);
                price.setText(null);
            }
        });
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Activity2.this, Activity1.class);
                startActivity(i);

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity2, menu);
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
