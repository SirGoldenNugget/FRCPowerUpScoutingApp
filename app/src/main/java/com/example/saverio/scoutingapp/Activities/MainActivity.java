package com.example.saverio.scoutingapp.Activities;

import android.content.Intent;
import android.database.Cursor;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.saverio.scoutingapp.R;

import Database.DatabaseHandler;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private DatabaseHandler db;
    private SimpleCursorAdapter dataAdapter;
    private static final String[] types = {"Cubes", "Switches", "Scales", "Vaults", "Climbs"};
    private static final String[] averages_totals = {"Averages", "Totals"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DatabaseHandler(this);

        Spinner type_spinner = (Spinner) findViewById(R.id.type_spinner);
        Spinner average_total_spinner = (Spinner) findViewById(R.id.average_total_spinner);

        type_spinner.bringToFront();
        average_total_spinner.bringToFront();

        ArrayAdapter<String> type_adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_spinner_item, types);
        ArrayAdapter<String> average_total_adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_spinner_item, averages_totals);

        type_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        average_total_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        type_spinner.setAdapter(type_adapter);
        average_total_spinner.setAdapter(average_total_adapter);

        type_spinner.setOnItemSelectedListener(this);
        average_total_spinner.setOnItemSelectedListener(this);

        displayListViewSorted(types[0], averages_totals[0]);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Spinner type_spinner = (Spinner) findViewById(R.id.type_spinner);
        Spinner average_total_spinner = (Spinner) findViewById(R.id.average_total_spinner);
        displayListViewSorted(types[type_spinner.getSelectedItemPosition()], averages_totals[average_total_spinner.getSelectedItemPosition()]);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    private void displayListViewSorted(String type, String average_total) {
        Cursor cursor = db.sortData(type, average_total);

        String[] columns = new String[]{
                DatabaseHandler.KEY_TEAMNUMBER,
                DatabaseHandler.KEY_SWITCHES_TOTAL,
                DatabaseHandler.KEY_SCALES_TOTAL,
                DatabaseHandler.KEY_VAULTS_TOTAL,
                DatabaseHandler.KEY_CLIMBS_TOTAL,
                DatabaseHandler.KEY_CUBES_TOTAL,
                DatabaseHandler.KEY_SWITCHES_AVERAGE,
                DatabaseHandler.KEY_SCALES_AVERAGE,
                DatabaseHandler.KEY_VAULTS_AVERAGE,
                DatabaseHandler.KEY_CLIMBS_AVERAGE,
                DatabaseHandler.KEY_CUBES_AVERAGE
        };

        int[] to = new int[]{
                R.id.teamNumberTextView,
                R.id.switchTotalTextView,
                R.id.scaleTotalTextView,
                R.id.vaultTotalTextView,
                R.id.climbTotalTextView,
                R.id.cubesTotalTextView,
                R.id.switchAverageTextView,
                R.id.scaleAverageTextView,
                R.id.vaultAverageTextView,
                R.id.climbAverageTextView,
                R.id.cubesAverageTextView
        };

        dataAdapter = new SimpleCursorAdapter(
                this, R.layout.team_list_layout,
                cursor,
                columns,
                to,
                0);

        ListView listView = (ListView) findViewById(R.id.teamList);
        listView.setAdapter(dataAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Cursor cur = (Cursor) dataAdapter.getItem(position);
                cur.moveToPosition(position);
                int id_ = cur.getInt(cur.getColumnIndexOrThrow("_id"));

                Intent intent = new Intent(MainActivity.this, SingleTeamActivity.class);
                intent.putExtra("teamNumber", id_);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.list_menu:
                break;
            case R.id.insert_menu:
                startActivity(new Intent(MainActivity.this, InsertActivity.class));
                break;
            case R.id.delete_menu:
                startActivity(new Intent(MainActivity.this, DeleteActivity.class));
                break;
            case R.id.edit_menu:
                startActivity(new Intent(MainActivity.this, EditActivity.class));
                break;
        }
        return false;
    }
}
