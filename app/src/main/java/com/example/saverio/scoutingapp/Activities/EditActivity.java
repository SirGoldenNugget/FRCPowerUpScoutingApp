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
        final EditText hatches_lower = (EditText) findViewById(R.id.hatches_lower_edit);
        final EditText hatches_upper = (EditText) findViewById(R.id.hatches_upper_edit);
//        final EditText hatches_dropped = (EditText) findViewById(R.id.hatches_dropped_edit);
        final EditText cargo_lower = (EditText) findViewById(R.id.cargo_lower_edit);
        final EditText cargo_upper = (EditText) findViewById(R.id.cargo_upper_edit);
        final EditText sandstorm = (EditText) findViewById(R.id.sandstorm_edit);
        final EditText endgame = (EditText) findViewById(R.id.endgame_edit);
        final EditText nmatch = (EditText) findViewById(R.id.match_edit);

        final Button edit = (Button) findViewById(R.id.confirmEdit);
        final DatabaseHandler db = new DatabaseHandler(this);

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String _teamnumber = teamNumber.getText().toString();
                String _hatches_lower = hatches_lower.getText().toString();
                String _hatches_upper = hatches_upper.getText().toString();
//                String _hatches_dropped = hatches_dropped.getText().toString();
                String _cargo_lower = cargo_lower.getText().toString();
                String _cargo_upper = cargo_upper.getText().toString();
                String _sandstorm = sandstorm.getText().toString();
                String _endgame = endgame.getText().toString();
                String _nmatch = nmatch.getText().toString();

                if (_teamnumber.matches("") ||
                        _hatches_lower.matches("") ||
                        _hatches_upper.matches("") ||
//                        _hatches_dropped.matches("") ||
                        _cargo_lower.matches("") ||
                        _cargo_upper.matches("") ||
                        _sandstorm.matches("") ||
                        _endgame.matches("") ||
                        _nmatch.matches("")) {
                    Toast.makeText(EditActivity.this, "Complete All The Fields", Toast.LENGTH_SHORT).show();
                } else {
                    if (isInteger(_teamnumber) && isInteger(_hatches_lower) &&
                            isInteger(_hatches_upper) &&
//                            isInteger(_hatches_dropped) &&
                            isInteger(_cargo_lower) &&
                            isInteger(_cargo_upper) &&
                            isInteger(_sandstorm) &&
                            isInteger(_endgame) &&
                            isInteger(_nmatch)) {
                        db.editTeam(Integer.parseInt(_teamnumber),
                                Integer.parseInt(_hatches_lower),
                                Integer.parseInt(_hatches_upper),
//                                Integer.parseInt(_hatches_dropped),
                                0,
                                Integer.parseInt(_cargo_lower),
                                Integer.parseInt(_cargo_upper),
                                Integer.parseInt(_sandstorm),
                                Integer.parseInt(_endgame),
                                Integer.parseInt(_nmatch));
                        Toast.makeText(EditActivity.this, "Team Edited", Toast.LENGTH_SHORT).show();
                        teamNumber.setText("");
                        hatches_lower.setText("");
                        hatches_upper.setText("");
//                        hatches_dropped.setText("");
                        cargo_lower.setText("");
                        cargo_upper.setText("");
                        sandstorm.setText("");
                        endgame.setText("");
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
