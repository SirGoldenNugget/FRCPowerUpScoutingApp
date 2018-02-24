package com.example.saverio.scoutingapp.Activities;


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;


import com.example.saverio.scoutingapp.R;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import Database.DatabaseHandler;


public class SingleTeamActivity extends AppCompatActivity {

    DatabaseHandler database;
    SQLiteDatabase db;

    int teamNumber;

    DataPoint[] dataset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singleteam);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        database = new DatabaseHandler(this);

        Bundle data = getIntent().getExtras();
        teamNumber = data.getInt("teamNumber");

        db = database.getReadableDatabase();

        graphSwitches();
        graphScales();
        graphVaults();
        graphClimbs();
    }

    public void graphSwitches() {
        GraphView graph = (GraphView) findViewById(R.id.switch_graph);

        Cursor cursor = db.rawQuery("SELECT * FROM " + DatabaseHandler.TABLE_MATCH + " WHERE " + DatabaseHandler.KEY_TEAMNUMBER_MATCH + " = " + teamNumber, null);
        Cursor cursor1 = db.rawQuery("SELECT MAX(match) AS matches_max FROM " + DatabaseHandler.TABLE_MATCH + " WHERE " + DatabaseHandler.KEY_TEAMNUMBER_MATCH + " = " + teamNumber, null);
        cursor1.moveToFirst();
        int max_match = cursor1.getInt(cursor1.getColumnIndex("matches_max"));

        dataset = new DataPoint[max_match];

        int counter = 0;


        while (cursor.moveToNext()) {
            int switches = cursor.getInt(1);
            int match = cursor.getInt(5);

            dataset[counter] = new DataPoint(match, switches);
            counter++;
        }

        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(dataset);
        series.setDrawDataPoints(true);
        series.setDataPointsRadius(6);

        graph.getViewport().setYAxisBoundsManual(true);
        graph.getViewport().setMinY(0);
        graph.getViewport().setMaxY(10);
        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMinX(1);
        graph.getViewport().setMaxX(8);
        graph.getGridLabelRenderer().setNumVerticalLabels(11);
        graph.getGridLabelRenderer().setNumHorizontalLabels(8);
        graph.getViewport().setScrollable(false);
        graph.setTitle("Switches");
        graph.setTitleTextSize(40f);
        graph.addSeries(series);
    }

    public void graphScales() {
        GraphView graph = (GraphView) findViewById(R.id.scale_graph);

        Cursor cursor = db.rawQuery("SELECT * FROM " + DatabaseHandler.TABLE_MATCH + " WHERE " + DatabaseHandler.KEY_TEAMNUMBER_MATCH + " = " + teamNumber, null);
        Cursor cursor1 = db.rawQuery("SELECT MAX(match) AS matches_max FROM " + DatabaseHandler.TABLE_MATCH + " WHERE " + DatabaseHandler.KEY_TEAMNUMBER_MATCH + " = " + teamNumber, null);
        cursor1.moveToFirst();
        int max_match = cursor1.getInt(cursor1.getColumnIndex("matches_max"));

        dataset = new DataPoint[max_match];

        int counter = 0;


        while (cursor.moveToNext()) {
            int scales = cursor.getInt(2);
            int match = cursor.getInt(5);

            dataset[counter] = new DataPoint(match, scales);
            counter++;
        }

        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(dataset);
        series.setDrawDataPoints(true);
        series.setDataPointsRadius(6);

        graph.getViewport().setYAxisBoundsManual(true);
        graph.getViewport().setMinY(0);
        graph.getViewport().setMaxY(10);
        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMinX(1);
        graph.getViewport().setMaxX(8);
        graph.getGridLabelRenderer().setNumVerticalLabels(11);
        graph.getGridLabelRenderer().setNumHorizontalLabels(8);
        graph.getViewport().setScrollable(false);
        graph.setTitle("Scales");
        graph.setTitleTextSize(40f);
        graph.addSeries(series);
    }

    public void graphVaults() {
        GraphView graph = (GraphView) findViewById(R.id.vault_graph);

        Cursor cursor = db.rawQuery("SELECT * FROM " + DatabaseHandler.TABLE_MATCH + " WHERE " + DatabaseHandler.KEY_TEAMNUMBER_MATCH + " = " + teamNumber, null);
        Cursor cursor1 = db.rawQuery("SELECT MAX(match) AS matches_max FROM " + DatabaseHandler.TABLE_MATCH + " WHERE " + DatabaseHandler.KEY_TEAMNUMBER_MATCH + " = " + teamNumber, null);
        cursor1.moveToFirst();
        int max_match = cursor1.getInt(cursor1.getColumnIndex("matches_max"));

        dataset = new DataPoint[max_match];

        int counter = 0;


        while (cursor.moveToNext()) {
            int vaults = cursor.getInt(3);
            int match = cursor.getInt(5);

            dataset[counter] = new DataPoint(match, vaults);
            counter++;
        }

        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(dataset);
        series.setDrawDataPoints(true);
        series.setDataPointsRadius(6);

        graph.getViewport().setYAxisBoundsManual(true);
        graph.getViewport().setMinY(0);
        graph.getViewport().setMaxY(10);
        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMinX(1);
        graph.getViewport().setMaxX(8);
        graph.getGridLabelRenderer().setNumVerticalLabels(11);
        graph.getGridLabelRenderer().setNumHorizontalLabels(8);
        graph.getViewport().setScrollable(false);
        graph.setTitle("Vaults");
        graph.setTitleTextSize(40f);
        graph.addSeries(series);
    }

    public void graphClimbs() {
        GraphView graph = (GraphView) findViewById(R.id.climb_graph);

        Cursor cursor = db.rawQuery("SELECT * FROM " + DatabaseHandler.TABLE_MATCH + " WHERE " + DatabaseHandler.KEY_TEAMNUMBER_MATCH + " = " + teamNumber, null);
        Cursor cursor1 = db.rawQuery("SELECT MAX(match) AS matches_max FROM " + DatabaseHandler.TABLE_MATCH + " WHERE " + DatabaseHandler.KEY_TEAMNUMBER_MATCH + " = " + teamNumber, null);
        cursor1.moveToFirst();
        int max_match = cursor1.getInt(cursor1.getColumnIndex("matches_max"));

        dataset = new DataPoint[max_match];

        int counter = 0;

        while (cursor.moveToNext()) {
            int climbs = cursor.getInt(4);
            int match = cursor.getInt(5);

            dataset[counter] = new DataPoint(match, climbs);
            counter++;
        }

        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(dataset);
        series.setDrawDataPoints(true);
        series.setDataPointsRadius(6);

        graph.getViewport().setYAxisBoundsManual(true);
        graph.getViewport().setMinY(0);
        graph.getViewport().setMaxY(3);
        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMinX(1);
        graph.getViewport().setMaxX(8);
        graph.getGridLabelRenderer().setNumVerticalLabels(4);
        graph.getGridLabelRenderer().setNumHorizontalLabels(8);
        graph.getViewport().setScrollable(false);
        graph.setTitle("Climbs");
        graph.setTitleTextSize(40f);
        graph.addSeries(series);
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                startActivity(new Intent(SingleTeamActivity.this, MainActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

}
