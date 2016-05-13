package echo.local_database_ipl;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;



import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Activity1 extends ActionBarActivity {

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
                ArrayList <String>brr=new ArrayList <String>();
                if(db.numberOfRows()!=0){
                    Map<String, Integer> map = new HashMap<String, Integer>();
                     arr_list = getIntent().getStringArrayListExtra("arr_list");
                    if(arr_list!=null) {
                        for (int i = 0; i < arr_list.size(); i++) {
                            String []s=arr_list.get(i).toString().split("\\s+");
                            map.put(Integer.toString(i),Integer.parseInt(s[s.length-1]));
                        }
                        Set<Map.Entry<String, Integer>> set = map.entrySet();
                        List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(set);
                        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
                            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                                return (o2.getValue()).compareTo(o1.getValue());
                            }
                        });
                        for(Map.Entry<String, Integer> entry:list){
                            //System.out.println(entry.getKey()+"=="+entry.getValue());
                            System.out.println(arr_list.get(Integer.parseInt(entry.getKey())).toString());
                            brr.add(arr_list.get(Integer.parseInt(entry.getKey())).toString());
                        }
                        ListView listview = (ListView) findViewById(R.id.lv);
                        ArrayAdapter adapter = new ArrayAdapter<String>(Activity1.this, R.layout.item, brr);
                        listview.setAdapter(adapter);

                    }
                    else{
                        Toast.makeText(Activity1.this,"No data added , add some info",
                                Toast.LENGTH_SHORT).show();
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
                        ListView listView=(ListView)findViewById(R.id.lv);
                        listView.setAdapter(null);

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
        getMenuInflater().inflate(R.menu.menu_activity1, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
