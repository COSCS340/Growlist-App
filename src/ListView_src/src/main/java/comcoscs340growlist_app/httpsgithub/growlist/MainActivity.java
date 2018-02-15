package comcoscs340growlist_app.httpsgithub.growlist;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] plants = {"plant1", "plant2", "plant3", "plant4", "plant5", "plant6"};
        //ListAdapter growlistAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, plants);
        ListAdapter growlistAdapter = new growlistAdapter(this, plants); // pass plants to constructor
        ListView growListView = (ListView) findViewById(R.id.growListView);
        growListView.setAdapter(growlistAdapter);

        growListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener(){
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String plant = String.valueOf(parent.getItemAtPosition(position));
                        Toast.makeText(MainActivity.this, plant, Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

}