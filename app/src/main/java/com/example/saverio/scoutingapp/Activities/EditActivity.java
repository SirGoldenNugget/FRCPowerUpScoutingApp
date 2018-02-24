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

public class EditActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        final EditText teamNumber = (EditText) findViewById(R.id.team_number_edit);
        final EditText switches = (EditText) findViewById(R.id.switches_edit);
        final EditText scales = (EditText) findViewById(R.id.scales_edit);
        final EditText vaults = (EditText) findViewById(R.id.vaults_edit);
        final EditText climbs = (EditText) findViewById(R.id.climbs_edit);
        final EditText nmatch = (EditText) findViewById(R.id.match_edit);

        final Button edit = (Button) findViewById(R.id.confirmEdit);
        final DatabaseHandler db = new DatabaseHandler(this);

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String _teamnumber = teamNumber.getText().toString();
                String _switches = switches.getText().toString();
                String _scales = scales.getText().toString();
                String _vaults = vaults.getText().toString();
                String _climbs = climbs.getText().toString();
                String _nmatch = nmatch.getText().toString();

                if (_teamnumber.matches("") || _switches.matches("") || _scales.matches("") || _vaults.matches("") || _climbs.matches("") || _nmatch.matches("")) {
                    Toast.makeText(EditActivity.this, "Complete All The Fields", Toast.LENGTH_SHORT).show();
                } else {
                    if (isInteger(_teamnumber) && isInteger(_switches) && isInteger(_scales) && isInteger(_vaults) && isInteger(_climbs) && isInteger(_nmatch)) {
                        db.editTeam(Integer.parseInt(_teamnumber), Integer.parseInt(_switches), Integer.parseInt(_scales), Integer.parseInt(_vaults), Integer.parseInt(_climbs), Integer.parseInt(_nmatch));
                        Toast.makeText(EditActivity.this, "Team Edited", Toast.LENGTH_SHORT).show();
                        teamNumber.setText("");
                        switches.setText("");
                        scales.setText("");
                        vaults.setText("");
                        climbs.setText("");
                        nmatch.setText("");
                    } else {
                        Toast.makeText(EditActivity.this, "Only Numbers Accepted", Toast.LENGTH_SHORT).show();
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
                startActivity(new Intent(EditActivity.this, MainActivity.class));
                break;
            case R.id.insert_menu:
                startActivity(new Intent(EditActivity.this, InsertActivity.class));
                break;
            case R.id.delete_menu:
                startActivity(new Intent(EditActivity.this, DeleteActivity.class));
                break;
            case R.id.edit_menu:
                break;
        }
        return false;
    }
}
