package Database;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {
    private SQLiteDatabase db = this.getWritableDatabase();

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "FRCPowerUpScoutingApp";

    public static final String TABLE_TEAMS = "Teams";
    public static final String TABLE_MATCH = "Match";

    // TEAMS TABLE

    public static final String KEY_TEAMNUMBER = "_id";
    public static final String KEY_SWITCHES_TOTAL = "switches_delivered_total";
    public static final String KEY_SCALES_TOTAL = "scales_delivered_total";
    public static final String KEY_VAULTS_TOTAL = "vaults_delivered_total";
    public static final String KEY_CLIMBS_TOTAL = "climbs_delivered_total";
    public static final String KEY_CUBES_TOTAL = "cubes_total";
    public static final String KEY_MATCH_TOTAL = "match_total";
    public static final String KEY_SWITCHES_AVERAGE = "switches_average";
    public static final String KEY_SCALES_AVERAGE = "scales_average";
    public static final String KEY_VAULTS_AVERAGE = "vaults_average";
    public static final String KEY_CLIMBS_AVERAGE = "climbs_average";
    public static final String KEY_CUBES_AVERAGE = "cubes_average";

    // MATCH TABLE

    public static final String KEY_TEAMNUMBER_MATCH = "teamNumber";
    public static final String KEY_SWITCHES = "switches_delivered";
    public static final String KEY_SCALES = "scales_delivered";
    public static final String KEY_VAULTS = "vaults_delivered";
    public static final String KEY_CLIMBS = "climbs_delivered";
    public static final String KEY_MATCH = "match";


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TEAMS_TABLE = "CREATE TABLE " + TABLE_TEAMS +
                "(" + KEY_TEAMNUMBER + " INTEGER," +
                KEY_SWITCHES_TOTAL + " INTEGER," +
                KEY_SCALES_TOTAL + " INTEGER," +
                KEY_VAULTS_TOTAL + " INTEGER," +
                KEY_CLIMBS_TOTAL + " INTEGER," +
                KEY_CUBES_TOTAL + " INTEGER," +
                KEY_MATCH_TOTAL + " INTEGER," +
                KEY_SWITCHES_AVERAGE + " REAL," +
                KEY_SCALES_AVERAGE + " REAL," +
                KEY_VAULTS_AVERAGE + " REAL," +
                KEY_CLIMBS_AVERAGE + " REAL," +
                KEY_CUBES_AVERAGE + " REAL" + ")";
        db.execSQL(CREATE_TEAMS_TABLE);
        String CREATE_MATCH_TABLE = "CREATE TABLE " + TABLE_MATCH +
                "(" + KEY_TEAMNUMBER_MATCH + " INTEGER," +
                KEY_SWITCHES + " INTEGER," +
                KEY_SCALES + " INTEGER," +
                KEY_VAULTS + " INTEGER," +
                KEY_CLIMBS + " INTEGER," +
                KEY_MATCH + " INTEGER" + ")";
        db.execSQL(CREATE_MATCH_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TEAMS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MATCH);

        onCreate(db);
    }

    public void setTeamsTable(int teamNumber) {
        Cursor cursor_switches = db.rawQuery("SELECT SUM(switches_delivered)" + " AS switches FROM " + TABLE_MATCH + " WHERE " + KEY_TEAMNUMBER_MATCH + " = " + teamNumber, null);
        Cursor cursor_scales = db.rawQuery("SELECT SUM(scales_delivered)" + " AS scales FROM " + TABLE_MATCH + " WHERE " + KEY_TEAMNUMBER_MATCH + " = " + teamNumber, null);
        Cursor cursor_vaults = db.rawQuery("SELECT SUM(vaults_delivered)" + " AS vaults FROM " + TABLE_MATCH + " WHERE " + KEY_TEAMNUMBER_MATCH + " = " + teamNumber, null);
        Cursor cursor_climbs = db.rawQuery("SELECT SUM(climbs_delivered)" + " AS climbs FROM " + TABLE_MATCH + " WHERE " + KEY_TEAMNUMBER_MATCH + " = " + teamNumber, null);
        Cursor cursor_matches = db.rawQuery("SELECT MAX(match) AS matches_max FROM " + TABLE_MATCH + " WHERE " + KEY_TEAMNUMBER_MATCH + " = " + teamNumber, null);
        Cursor cursor_switches_average = db.rawQuery("SELECT AVG(switches_delivered)" + " AS switches_average FROM " + TABLE_MATCH + " WHERE " + KEY_TEAMNUMBER_MATCH + " = " + teamNumber, null);
        Cursor cursor_scales_average = db.rawQuery("SELECT AVG(scales_delivered)" + " AS scales_average FROM " + TABLE_MATCH + " WHERE " + KEY_TEAMNUMBER_MATCH + " = " + teamNumber, null);
        Cursor cursor_vaults_average = db.rawQuery("SELECT AVG(vaults_delivered)" + " AS vaults_average FROM " + TABLE_MATCH + " WHERE " + KEY_TEAMNUMBER_MATCH + " = " + teamNumber, null);
        Cursor cursor_climbs_average = db.rawQuery("SELECT AVG(climbs_delivered)" + " AS climbs_average FROM " + TABLE_MATCH + " WHERE " + KEY_TEAMNUMBER_MATCH + " = " + teamNumber, null);

        cursor_switches.moveToFirst();
        cursor_scales.moveToFirst();
        cursor_vaults.moveToFirst();
        cursor_climbs.moveToFirst();
        cursor_matches.moveToFirst();
        cursor_switches_average.moveToFirst();
        cursor_scales_average.moveToFirst();
        cursor_vaults_average.moveToFirst();
        cursor_climbs_average.moveToFirst();

        int switches = cursor_switches.getInt(cursor_switches.getColumnIndex("switches"));
        int scales = cursor_scales.getInt(cursor_scales.getColumnIndex("scales"));
        int vaults = cursor_vaults.getInt(cursor_vaults.getColumnIndex("vaults"));
        int climbs = cursor_climbs.getInt(cursor_climbs.getColumnIndex("climbs"));
        int matches = cursor_matches.getInt(cursor_matches.getColumnIndex("matches_max"));

        double switches_average = cursor_switches_average.getDouble(cursor_switches_average.getColumnIndex("switches_average"));
        double scales_average = cursor_scales_average.getDouble(cursor_scales_average.getColumnIndex("scales_average"));
        double vaults_average = cursor_vaults_average.getDouble(cursor_vaults_average.getColumnIndex("vaults_average"));
        double climbs_average = cursor_climbs_average.getDouble(cursor_climbs_average.getColumnIndex("climbs_average"));

        double switches_average_result = (double) Math.round(switches_average * 100d) / 100d;
        double scales_average_result = (double) Math.round(scales_average * 100d) / 100d;
        double vaults_average_result = (double) Math.round(vaults_average * 100d) / 100d;
        double climbs_average_result = (double) Math.round(climbs_average * 100d) / 100d;

        int cubes_total = switches + scales + vaults;
        double cubes_average = (double) cubes_total / (double) matches;

        ContentValues values = new ContentValues();
        values.put(KEY_TEAMNUMBER, teamNumber);
        values.put(KEY_SWITCHES_TOTAL, switches);
        values.put(KEY_SCALES_TOTAL, scales);
        values.put(KEY_VAULTS_TOTAL, vaults);
        values.put(KEY_CLIMBS_TOTAL, climbs);
        values.put(KEY_MATCH_TOTAL, matches);
        values.put(KEY_CUBES_TOTAL, cubes_total);
        values.put(KEY_SWITCHES_AVERAGE, switches_average_result);
        values.put(KEY_SCALES_AVERAGE, scales_average_result);
        values.put(KEY_VAULTS_AVERAGE, vaults_average_result);
        values.put(KEY_CLIMBS_AVERAGE, climbs_average_result);
        values.put(KEY_CUBES_AVERAGE, cubes_average);

        if (matches == 1) {
            db.insert(TABLE_TEAMS, null, values);
        } else {
            db.update(TABLE_TEAMS, values, "_id=" + teamNumber, null);
        }
    }

    public void addTeam(Team team) {
        Cursor cursor = db.rawQuery("SELECT MAX(match) AS max FROM " + TABLE_MATCH + " WHERE " + KEY_TEAMNUMBER_MATCH + " = " + team.getTeamNumber(), null);

        if (isCursorEmpty(cursor)) {
            ContentValues values = new ContentValues();

            values.put(KEY_TEAMNUMBER_MATCH, team.getTeamNumber());
            values.put(KEY_SWITCHES, team.getSwitches());
            values.put(KEY_SCALES, team.getScales());
            values.put(KEY_VAULTS, team.getVaults());
            values.put(KEY_CLIMBS, team.getClimbs());
            values.put(KEY_MATCH, 1);

            db.insert(TABLE_MATCH, null, values);
        } else {
            cursor.moveToFirst();
            ContentValues values = new ContentValues();

            values.put(KEY_TEAMNUMBER_MATCH, team.getTeamNumber());
            values.put(KEY_SWITCHES, team.getSwitches());
            values.put(KEY_SCALES, team.getScales());
            values.put(KEY_VAULTS, team.getVaults());
            values.put(KEY_CLIMBS, team.getClimbs());
            values.put(KEY_MATCH, cursor.getInt(cursor.getColumnIndex("max")) + 1);

            db.insert(TABLE_MATCH, null, values);
        }

        setTeamsTable(team.getTeamNumber());
    }

    public boolean isCursorEmpty(Cursor cursor) {
        return (!cursor.moveToFirst() || cursor.getCount() == 0);
    }

    public boolean deleteTeam(int teamNumber) {
        return db.delete(TABLE_TEAMS, KEY_TEAMNUMBER + "=" + teamNumber, null) > 0 && db.delete(TABLE_MATCH, KEY_TEAMNUMBER_MATCH + "=" + teamNumber, null) > 0;
    }

    public void editTeam(int teamNumber, int switches, int scales, int vaults, int climbs, int matchN) {
        ContentValues contentValues = new ContentValues();

        contentValues.put(KEY_TEAMNUMBER_MATCH, teamNumber);
        contentValues.put(KEY_SWITCHES, switches);
        contentValues.put(KEY_SCALES, scales);
        contentValues.put(KEY_VAULTS, vaults);
        contentValues.put(KEY_CLIMBS, climbs);
        contentValues.put(KEY_MATCH, matchN);

        db.update(TABLE_MATCH, contentValues, "teamNumber=" + teamNumber + " AND match=" + matchN, null);

        setTeamsTable(teamNumber);
    }

    public Cursor sortData(String type, String average_total) {
        Cursor cursor = db.rawQuery("", null);
        if (type.equals("Switches") && average_total.equals("Averages")) {
            cursor = db.query(TABLE_TEAMS, new String[]{
                    KEY_TEAMNUMBER,
                    KEY_SWITCHES_TOTAL,
                    KEY_SCALES_TOTAL,
                    KEY_VAULTS_TOTAL,
                    KEY_CLIMBS_TOTAL,
                    KEY_CUBES_TOTAL,
                    KEY_MATCH_TOTAL,
                    KEY_SWITCHES_AVERAGE,
                    KEY_SCALES_AVERAGE,
                    KEY_VAULTS_AVERAGE,
                    KEY_CLIMBS_AVERAGE,
                    KEY_CUBES_AVERAGE
            }, null, null, null, null, KEY_SWITCHES_AVERAGE + " DESC");
            if (cursor != null) {
                cursor.moveToFirst();
            }
        } else if (type.equals("Scales") && average_total.equals("Averages")) {
            cursor = db.query(TABLE_TEAMS, new String[]{
                    KEY_TEAMNUMBER,
                    KEY_SWITCHES_TOTAL,
                    KEY_SCALES_TOTAL,
                    KEY_VAULTS_TOTAL,
                    KEY_CLIMBS_TOTAL,
                    KEY_CUBES_TOTAL,
                    KEY_MATCH_TOTAL,
                    KEY_SWITCHES_AVERAGE,
                    KEY_SCALES_AVERAGE,
                    KEY_VAULTS_AVERAGE,
                    KEY_CLIMBS_AVERAGE,
                    KEY_CUBES_AVERAGE
            }, null, null, null, null, KEY_SCALES_AVERAGE + " DESC");
            if (cursor != null) {
                cursor.moveToFirst();
            }
        } else if (type.equals("Vaults") && average_total.equals("Averages")) {
            cursor = db.query(TABLE_TEAMS, new String[]{
                    KEY_TEAMNUMBER,
                    KEY_SWITCHES_TOTAL,
                    KEY_SCALES_TOTAL,
                    KEY_VAULTS_TOTAL,
                    KEY_CLIMBS_TOTAL,
                    KEY_CUBES_TOTAL,
                    KEY_MATCH_TOTAL,
                    KEY_SWITCHES_AVERAGE,
                    KEY_SCALES_AVERAGE,
                    KEY_VAULTS_AVERAGE,
                    KEY_CLIMBS_AVERAGE,
                    KEY_CUBES_AVERAGE
            }, null, null, null, null, KEY_VAULTS_AVERAGE + " DESC");
            if (cursor != null) {
                cursor.moveToFirst();
            }
        } else if (type.equals("Climbs") && average_total.equals("Averages")) {
            cursor = db.query(TABLE_TEAMS, new String[]{
                    KEY_TEAMNUMBER,
                    KEY_SWITCHES_TOTAL,
                    KEY_SCALES_TOTAL,
                    KEY_VAULTS_TOTAL,
                    KEY_CLIMBS_TOTAL,
                    KEY_CUBES_TOTAL,
                    KEY_MATCH_TOTAL,
                    KEY_SWITCHES_AVERAGE,
                    KEY_SCALES_AVERAGE,
                    KEY_VAULTS_AVERAGE,
                    KEY_CLIMBS_AVERAGE,
                    KEY_CUBES_AVERAGE
            }, null, null, null, null, KEY_CLIMBS_AVERAGE + " DESC");
            if (cursor != null) {
                cursor.moveToFirst();
            }
        } else if (type.equals("Cubes") && average_total.equals("Averages")) {
            cursor = db.query(TABLE_TEAMS, new String[]{
                    KEY_TEAMNUMBER,
                    KEY_SWITCHES_TOTAL,
                    KEY_SCALES_TOTAL,
                    KEY_VAULTS_TOTAL,
                    KEY_CLIMBS_TOTAL,
                    KEY_CUBES_TOTAL,
                    KEY_MATCH_TOTAL,
                    KEY_SWITCHES_AVERAGE,
                    KEY_SCALES_AVERAGE,
                    KEY_VAULTS_AVERAGE,
                    KEY_CLIMBS_AVERAGE,
                    KEY_CUBES_AVERAGE
            }, null, null, null, null, KEY_CUBES_AVERAGE + " DESC");
            if (cursor != null) {
                cursor.moveToFirst();
            }
        } else if (type.equals("Switches") && average_total.equals("Totals")) {
            cursor = db.query(TABLE_TEAMS, new String[]{
                    KEY_TEAMNUMBER,
                    KEY_SWITCHES_TOTAL,
                    KEY_SCALES_TOTAL,
                    KEY_VAULTS_TOTAL,
                    KEY_CLIMBS_TOTAL,
                    KEY_CUBES_TOTAL,
                    KEY_MATCH_TOTAL,
                    KEY_SWITCHES_AVERAGE,
                    KEY_SCALES_AVERAGE,
                    KEY_VAULTS_AVERAGE,
                    KEY_CLIMBS_AVERAGE,
                    KEY_CUBES_AVERAGE
            }, null, null, null, null, KEY_SWITCHES_TOTAL + " DESC");
            if (cursor != null) {
                cursor.moveToFirst();
            }
        } else if (type.equals("Scales") && average_total.equals("Totals")) {
            cursor = db.query(TABLE_TEAMS, new String[]{
                    KEY_TEAMNUMBER,
                    KEY_SWITCHES_TOTAL,
                    KEY_SCALES_TOTAL,
                    KEY_VAULTS_TOTAL,
                    KEY_CLIMBS_TOTAL,
                    KEY_CUBES_TOTAL,
                    KEY_MATCH_TOTAL,
                    KEY_SWITCHES_AVERAGE,
                    KEY_SCALES_AVERAGE,
                    KEY_VAULTS_AVERAGE,
                    KEY_CLIMBS_AVERAGE,
                    KEY_CUBES_AVERAGE
            }, null, null, null, null, KEY_SCALES_TOTAL + " DESC");
            if (cursor != null) {
                cursor.moveToFirst();
            }
        } else if (type.equals("Vaults") && average_total.equals("Totals")) {
            cursor = db.query(TABLE_TEAMS, new String[]{
                    KEY_TEAMNUMBER,
                    KEY_SWITCHES_TOTAL,
                    KEY_SCALES_TOTAL,
                    KEY_VAULTS_TOTAL,
                    KEY_CLIMBS_TOTAL,
                    KEY_CUBES_TOTAL,
                    KEY_MATCH_TOTAL,
                    KEY_SWITCHES_AVERAGE,
                    KEY_SCALES_AVERAGE,
                    KEY_VAULTS_AVERAGE,
                    KEY_CLIMBS_AVERAGE,
                    KEY_CUBES_AVERAGE
            }, null, null, null, null, KEY_VAULTS_TOTAL + " DESC");
            if (cursor != null) {
                cursor.moveToFirst();
            }
        } else if (type.equals("Climbs") && average_total.equals("Totals")) {
            cursor = db.query(TABLE_TEAMS, new String[]{
                    KEY_TEAMNUMBER,
                    KEY_SWITCHES_TOTAL,
                    KEY_SCALES_TOTAL,
                    KEY_VAULTS_TOTAL,
                    KEY_CLIMBS_TOTAL,
                    KEY_CUBES_TOTAL,
                    KEY_MATCH_TOTAL,
                    KEY_SWITCHES_AVERAGE,
                    KEY_SCALES_AVERAGE,
                    KEY_VAULTS_AVERAGE,
                    KEY_CLIMBS_AVERAGE,
                    KEY_CUBES_AVERAGE
            }, null, null, null, null, KEY_CLIMBS_TOTAL + " DESC");
            if (cursor != null) {
                cursor.moveToFirst();
            }
        } else if (type.equals("Cubes") && average_total.equals("Totals")) {
            cursor = db.query(TABLE_TEAMS, new String[]{
                    KEY_TEAMNUMBER,
                    KEY_SWITCHES_TOTAL,
                    KEY_SCALES_TOTAL,
                    KEY_VAULTS_TOTAL,
                    KEY_CLIMBS_TOTAL,
                    KEY_CUBES_TOTAL,
                    KEY_MATCH_TOTAL,
                    KEY_SWITCHES_AVERAGE,
                    KEY_SCALES_AVERAGE,
                    KEY_VAULTS_AVERAGE,
                    KEY_CLIMBS_AVERAGE,
                    KEY_CUBES_AVERAGE
            }, null, null, null, null, KEY_CUBES_TOTAL + " DESC");
            if (cursor != null) {
                cursor.moveToFirst();
            }
        }
        return cursor;
    }
}