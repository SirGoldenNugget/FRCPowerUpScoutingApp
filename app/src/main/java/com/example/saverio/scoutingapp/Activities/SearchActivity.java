package com.example.saverio.scoutingapp.Activities;


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.saverio.scoutingapp.R;

import Database.DatabaseHandler;
import Database.Team;

public class SearchActivity extends AppCompatActivity {
    private DatabaseHandler db;
    private SimpleCursorAdapter dataAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        final EditText search_input = (EditText) findViewById(R.id.search_input);
        final Button search = (Button) findViewById(R.id.search_button);
        db = new DatabaseHandler(this);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String _search_input = search_input.getText().toString();
                if (_search_input.matches("")) {
                    Toast.makeText(SearchActivity.this, "Complete All The Fields", Toast.LENGTH_SHORT).show();
                } else {
                    if (isInteger(_search_input)) {
                        Cursor cursor = db.findData(_search_input);

                        String[] columns = new String[]{
                                DatabaseHandler.KEY_TEAMNUMBER,
                                DatabaseHandler.KEY_HATCHES_TOTAL,
                                DatabaseHandler.KEY_HATCHES_LOWER_TOTAL,
                                DatabaseHandler.KEY_HATCHES_UPPER_TOTAL,
                                DatabaseHandler.KEY_CARGO_TOTAL,
                                DatabaseHandler.KEY_CARGO_LOWER_TOTAL,
                                DatabaseHandler.KEY_CARGO_UPPER_TOTAL,
                                DatabaseHandler.KEY_SANDSTORM_TOTAL,
                                DatabaseHandler.KEY_ENDGAME_TOTAL,
                                DatabaseHandler.KEY_POINTS_TOTAL,
                                DatabaseHandler.KEY_HATCHES_AVERAGE,
                                DatabaseHandler.KEY_HATCHES_LOWER_AVERAGE,
                                DatabaseHandler.KEY_HATCHES_UPPER_AVERAGE,
                                DatabaseHandler.KEY_CARGO_AVERAGE,
                                DatabaseHandler.KEY_CARGO_LOWER_AVERAGE,
                                DatabaseHandler.KEY_CARGO_UPPER_AVERAGE,
                                DatabaseHandler.KEY_SANDSTORM_AVERAGE,
                                DatabaseHandler.KEY_ENDGAME_AVERAGE,
                                DatabaseHandler.KEY_POINTS_AVERAGE
                        };

                        int[] to = new int[]{
                                R.id.teamNumberTextView,
                                R.id.hatchesTotalTextView,
                                R.id.hatchesLowerTotalTextView,
                                R.id.hatchesUpperTotalTextView,
                                R.id.cargoTotalTextView,
                                R.id.cargoLowerTotalTextView,
                                R.id.cargoUpperTotalTextView,
                                R.id.sandstormTotalTextView,
                                R.id.endgameTotalTextView,
                                R.id.pointsTotalTextView,
                                R.id.hatchesAverageTextView,
                                R.id.hatchesLowerAverageTextView,
                                R.id.hatchesUpperAverageTextView,
                                R.id.cargoAverageTextView,
                                R.id.cargoLowerAverageTextView,
                                R.id.cargoUpperAverageTextView,
                                R.id.sandstormAverageTextView,
                                R.id.endgameAverageTextView,
                                R.id.pointsAverageTextView
                        };

                        dataAdapter = new SimpleCursorAdapter(
                                SearchActivity.this, R.layout.team_list_layout,
                                cursor,
                                columns,
                                to,
                                0);

                        ListView listView = (ListView) findViewById(R.id.teamList_search);
                        listView.setAdapter(dataAdapter);
                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                                Cursor cur = (Cursor) dataAdapter.getItem(position);
                                cur.moveToPosition(position);
                                int id_ = cur.getInt(cur.getColumnIndexOrThrow("_id"));

                                Intent intent = new Intent(SearchActivity.this, SingleTeamActivity.class);
                                intent.putExtra("teamNumber", id_);
                                startActivity(intent);
                            }
                        });
                    } else {
                        Toast.makeText(SearchActivity.this, "Only Numbers Are Accepted", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return false;
        } catch (NullPointerException e) {
            return false;
        }
        // only got here if we didn't return false
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.list_menu:
                startActivity(new Intent(SearchActivity.this, MainActivity.class));
                break;
            case R.id.insert_menu:
                startActivity(new Intent(SearchActivity.this, InsertActivity.class));
                break;
            case R.id.delete_menu:
                startActivity(new Intent(SearchActivity.this, DeleteActivity.class));
                break;
            case R.id.edit_menu:
                startActivity(new Intent(SearchActivity.this, EditActivity.class));
                break;
            case R.id.search_menu:
                break;
        }
        return false;
    }

}