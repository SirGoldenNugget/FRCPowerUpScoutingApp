package com.example.saverio.scoutingapp.Activities;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.saverio.scoutingapp.R;

import Database.DatabaseHandler;
import Database.Team;

public class InsertActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        final EditText teamNumber = (EditText) findViewById(R.id.team_number);
        final EditText switches = (EditText) findViewById(R.id.switches);
        final EditText scales = (EditText) findViewById(R.id.scales);
        final EditText vaults = (EditText) findViewById(R.id.vaults);
        final EditText climbs = (EditText) findViewById(R.id.climbs);
        final Button save = (Button) findViewById(R.id.confirmInsertion);
        final DatabaseHandler db = new DatabaseHandler(this);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String _teamNumber = teamNumber.getText().toString();
                String _switches = switches.getText().toString();
                String _scales = scales.getText().toString();
                String _vaults = vaults.getText().toString();
                String _climbs = climbs.getText().toString();

                if (_teamNumber.matches("") || _switches.matches("") || _scales.matches("") || _vaults.matches("") || _climbs.matches("")) {
                    Toast.makeText(InsertActivity.this, "Complete All The Fields", Toast.LENGTH_SHORT).show();
                } else {
                    if (isInteger(_teamNumber) && isInteger(_switches)) {
                        db.addTeam(new Team(Integer.parseInt(_teamNumber), Integer.parseInt(_switches), Integer.parseInt(_scales), Integer.parseInt(_vaults), Integer.parseInt(_climbs)));
                        Toast.makeText(InsertActivity.this, "Team Added", Toast.LENGTH_SHORT).show();
                        teamNumber.setText("");
                        switches.setText("");
                        scales.setText("");
                        vaults.setText("");
                        climbs.setText("");
                    } else {
                        Toast.makeText(InsertActivity.this, "Only Numbers Are Accepted", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
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
    protected void onPause() {
        super.onPause();
        finish();
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
                startActivity(new Intent(InsertActivity.this, MainActivity.class));
                break;
            case R.id.insert_menu:
                break;
            case R.id.delete_menu:
                startActivity(new Intent(InsertActivity.this, DeleteActivity.class));
                break;
            case R.id.edit_menu:
                startActivity(new Intent(InsertActivity.this, EditActivity.class));
                break;
        }
        return false;
    }

}