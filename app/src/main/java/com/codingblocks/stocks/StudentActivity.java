package com.codingblocks.stocks;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class StudentActivity extends AppCompatActivity {

    ArrayList<Student> data;
    StudentArrayAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        int numStudents = b.getInt("numStudents");

        data = new ArrayList<>();
        for (int i = 0; i < numStudents/2; i++) {
            data.add(new Student("Student " + i, "Crux"));
        }

        for (int i = numStudents/2; i < numStudents; i++) {
            data.add(new Student("Student " + i, "Pandora"));
        }

        //adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data);
        adapter = new StudentArrayAdapter(this, data, getLayoutInflater());
        ListView lv = (ListView) findViewById(R.id.listview);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String name = adapter.getItem(position).name;
                Intent intent = new Intent();
                intent.putExtra("studentName", name);
                setResult(0, intent);
                finish();
            }
        });
    }

    @Override
    public void finish() {
        super.finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
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
        } else if (id == R.id.action_delete) {
            AlertDialog.Builder b = new AlertDialog.Builder(this);
            b.setTitle("Confirm");
            //b.setMessage("Are you sure you want to delete?");
            LayoutInflater inflater = getLayoutInflater();
            View v = inflater.inflate(R.layout.alert, null);
            TextView tv = (TextView)v.findViewById(R.id.dialogtextview);
            tv.setText("Delete " + data.get(data.size() - 1).name + "?");
            b.setView(v);
            b.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    data.remove(data.size() - 1);
                    adapter.notifyDataSetChanged();
                }
            });
            b.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            b.create().show();
        } else if (id == R.id.action_add) {
            data.add(new Student("New Student", "Pandora"));
            adapter.notifyDataSetChanged();
        }

        return super.onOptionsItemSelected(item);
    }
}
