package com.codingblocks.stocks;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;


public class BatchActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    BatchArrayAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_batch);
        Date d = new Date();
        ArrayList<Batch> batches = new ArrayList<>();
        batches.add(new Batch("Pandora Dec", "Pandora", 0, 28, true));
        batches.add(new Batch("Launchpad Dec", "Launchpad", 5, 50, true));

        batches.add(new Batch("Pandora Aug", "Pandora", 26, 28, false));
        batches.add(new Batch("Launchpad Aug", "Launchpad", 55, 50, false));
        batches.add(new Batch("Launchpad Aug Extension", "Launchpad", 21, 18, false));
        batches.add(new Batch("Crux Aug 1", "Crux", 30, 28, false));
        batches.add(new Batch("Elixir August", "Elixir", 26, 28, false));

        ListView lv = (ListView) findViewById(R.id.batchListView);
        adapter = new BatchArrayAdapter(this, batches);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_batch, menu);
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
        } else if (id == R.id.action_call) {
            Intent i = new Intent();
            i.setAction(Intent.ACTION_CALL);
            i.setData(Uri.parse("tel:9971489388"));
            if (i.resolveActivity(getPackageManager()) != null) {
                startActivity(i);
            }
        } else if (id == R.id.action_web)  {
            Intent i = new Intent();
            i.setAction(Intent.ACTION_VIEW);
            i.setData(Uri.parse("http://www.codingblocks.com"));
            if (i.resolveActivity(getPackageManager()) != null) {
                startActivity(i);
            }
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Batch b = adapter.getItem(position);

        Intent i = new Intent();
        i.setClass(this, StudentActivity.class);
        i.putExtra("numStudents", b.currentlyFilled);
        i.putExtra("studentArray", new Student[10]);
        startActivityForResult(i, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null)
            return;
        Bundle b = data.getExtras();
        String studentName = b.getString("studentName");
        Toast.makeText(this, "student clicked " + studentName, Toast.LENGTH_LONG).show();
    }
}
