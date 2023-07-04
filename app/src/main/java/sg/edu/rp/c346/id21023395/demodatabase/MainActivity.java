package sg.edu.rp.c346.id21023395.demodatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnInsert, btnGetTasks;
    TextView tvResults;
    ListView lv;
    EditText etDescpt, etDate;
    boolean isasc = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnInsert = findViewById(R.id.btnInsert);
        btnGetTasks = findViewById(R.id.btnGetTasks);
        tvResults = findViewById(R.id.tvResults);
        lv = findViewById(R.id.lv);
        etDescpt = findViewById(R.id.etDescrpt);
        etDate = findViewById(R.id.etDate);

        btnInsert.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String task = etDescpt.getText().toString();
                String date = etDate.getText().toString();
                // Create the DBHelper object, passing in the
                // activity's Context
                DBHelper db = new DBHelper(MainActivity.this);
                // Insert a task
                db.insertTask(task, date);
                db.close();
            }
        });

        btnGetTasks.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // Create the DBHelper object, passing in the
                // activity's Context
                DBHelper db = new DBHelper(MainActivity.this);
                // Insert a task
                ArrayList<String> data = db.getTaskContent();
                db.close();

                String txt = "";
                for (int i = 0; i < data.size(); i++) {
                    Log.d("Database Content", i +". "+data.get(i));
                    txt += i + ". " + data.get(i) + "\n";
                }
                tvResults.setText(txt);

                // Display listView
                DBHelper db2 = new DBHelper(MainActivity.this);
                ArrayList<Task> al = db2.getTasks(isasc);
                db.close();
                isasc = !isasc; // change isasc from true to false OR false to true
                ArrayAdapter<String> aa = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, al);
                lv.setAdapter(aa);
            }
        });
    }
}