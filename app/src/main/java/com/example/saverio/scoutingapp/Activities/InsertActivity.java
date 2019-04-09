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
import android.widget.Spinner;
import android.widget.Toast;

import com.example.saverio.scoutingapp.R;

import Database.DatabaseHandler;
import Database.Team;

public class InsertActivity extends AppCompatActivity {

//    private static final String[] sandstorm = {"Level 1", "Level 2"};
//    private static final String[] endgame = {"Level 1", "Level 2", "Level 3"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);


//        Spinner sandstorm_spinner = (Spinner) findViewById(R.id.sandstorm_spinner);
//        Spinner endgame_spinner = (Spinner) findViewById(R.id.endgame_spinner);

        final EditText teamNumber = (EditText) findViewById(R.id.team_number);
        final EditText hatches_lower = (EditText) findViewById(R.id.hatches_lower);
        final EditText hatches_upper = (EditText) findViewById(R.id.hatches_upper);
//        final EditText hatches_dropped = (EditText) findViewById(R.id.hatches_dropped);
        final EditText cargo_lower = (EditText) findViewById(R.id.cargo_lower);
        final EditText cargo_upper = (EditText) findViewById(R.id.cargo_upper);
        final EditText sandstorm = (EditText) findViewById(R.id.sandstorm);
        final EditText endgame = (EditText) findViewById(R.id.endgame);
        final Button save = (Button) findViewById(R.id.confirmInsertion);
        final DatabaseHandler db = new DatabaseHandler(this);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String _teamNumber = teamNumber.getText().toString();
                String _hatches_lower = hatches_lower.getText().toString();
                String _hatches_upper = hatches_upper.getText().toString();
//                String _hatches_dropped = hatches_dropped.getText().toString();
                String _cargo_lower = cargo_lower.getText().toString();
                String _cargo_upper = cargo_upper.getText().toString();
                String _sandstorm = sandstorm.getText().toString();
                String _endgame = endgame.getText().toString();

                if (_teamNumber.matches("") ||
                        _hatches_lower.matches("") ||
                        _hatches_upper.matches("") ||
//                        _hatches_dropped.matches("") ||
                        _cargo_lower.matches("") ||
                        _cargo_upper.matches("") ||
                        _sandstorm.matches("") ||
                        _endgame.matches("")) {
                    Toast.makeText(InsertActivity.this, "Complete All The Fields", Toast.LENGTH_SHORT).show();
                } else {
                    if (isInteger(_teamNumber) &&
                            isInteger(_hatches_lower) &&
                            isInteger(_hatches_upper) &&
//                            isInteger(_hatches_dropped) &&
                            isInteger(_cargo_lower) &&
                            isInteger(_cargo_upper) &&
                            isInteger(_sandstorm) &&
                            isInteger(_endgame)) {
                        db.addTeam(new Team(Integer.parseInt(_teamNumber),
                                Integer.parseInt(_hatches_lower),
                                Integer.parseInt(_hatches_upper),
                                0,
                                Integer.parseInt(_cargo_lower),
                                Integer.parseInt(_cargo_upper),
                                Integer.parseInt(_sandstorm),
                                Integer.parseInt(_endgame)));
                        Toast.makeText(InsertActivity.this, "Team Added", Toast.LENGTH_SHORT).show();
                        teamNumber.setText("");
                        hatches_lower.setText("");
                        hatches_upper.setText("");
//                        hatches_dropped.setText("");
                        cargo_lower.setText("");
                        cargo_upper.setText("");
                        sandstorm.setText("");
                        endgame.setText("");
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