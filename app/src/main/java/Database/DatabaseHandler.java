package Database;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {
    private SQLiteDatabase db = this.getWritableDatabase();

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "FRCDeepSpaceScoutingApp";

    public static final String TABLE_TEAMS = "Teams";
    public static final String TABLE_MATCH = "Match";

    // TEAMS TABLE

    public static final String KEY_TEAMNUMBER = "_id";
    public static final String KEY_HATCHES_TOTAL = "hatches_delivered_total";
    public static final String KEY_HATCHES_LOWER_TOTAL = "hatches_lower_delivered_total";
    public static final String KEY_HATCHES_UPPER_TOTAL = "hatches_upper_delivered_total";
    public static final String KEY_CARGO_TOTAL = "cargo_delivered_total";
    public static final String KEY_CARGO_LOWER_TOTAL = "cargo_lower_delivered_total";
    public static final String KEY_CARGO_UPPER_TOTAL = "cargo_upper_delivered_total";
    public static final String KEY_SANDSTORM_TOTAL = "sandstorm_delivered_total";
    public static final String KEY_ENDGAME_TOTAL = "endgame_delivered_total";
    public static final String KEY_POINTS_TOTAL = "points_total";
    public static final String KEY_MATCH_TOTAL = "match_total";

    public static final String KEY_HATCHES_AVERAGE = "hatches_average";
    public static final String KEY_HATCHES_LOWER_AVERAGE = "hatches_lower_delivered_average";
    public static final String KEY_HATCHES_UPPER_AVERAGE = "hatches_upper_delivered_average";
    public static final String KEY_CARGO_AVERAGE = "cargo_average";
    public static final String KEY_CARGO_LOWER_AVERAGE = "cargo_lower_delivered_average";
    public static final String KEY_CARGO_UPPER_AVERAGE = "cargo_upper_delivered_average";
    public static final String KEY_SANDSTORM_AVERAGE = "sandstorm_average";
    public static final String KEY_ENDGAME_AVERAGE = "endgame_average";
    public static final String KEY_POINTS_AVERAGE = "points_average";

    // MATCH TABLE

    public static final String KEY_TEAMNUMBER_MATCH = "teamNumber";
    public static final String KEY_HATCHES = "hatches_delivered";
    public static final String KEY_HATCHES_LOWER = "hatches_lower_delivered";
    public static final String KEY_HATCHES_UPPER = "hatches_upper_delivered";
    public static final String KEY_CARGO = "cargo_delivered";
    public static final String KEY_CARGO_LOWER = "cargo_lower_delivered";
    public static final String KEY_CARGO_UPPER = "cargo_upper_delivered";
    public static final String KEY_SANDSTORM = "sandstorm_delivered";
    public static final String KEY_ENDGAME = "endgame_delivered";
    public static final String KEY_MATCH = "match";


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TEAMS_TABLE = "CREATE TABLE " + TABLE_TEAMS +
                "(" + KEY_TEAMNUMBER + " INTEGER," +
                KEY_HATCHES_TOTAL + " INTEGER," +
                KEY_HATCHES_LOWER_TOTAL + " INTEGER," +
                KEY_HATCHES_UPPER_TOTAL + " INTEGER," +
                KEY_CARGO_TOTAL + " INTEGER," +
                KEY_CARGO_LOWER_TOTAL + " INTEGER," +
                KEY_CARGO_UPPER_TOTAL + " INTEGER," +
                KEY_SANDSTORM_TOTAL + " INTEGER," +
                KEY_ENDGAME_TOTAL + " INTEGER," +
                KEY_POINTS_TOTAL + " INTEGER," +
                KEY_MATCH_TOTAL + " INTEGER," +
                KEY_HATCHES_AVERAGE + " REAL," +
                KEY_HATCHES_LOWER_AVERAGE + " REAL," +
                KEY_HATCHES_UPPER_AVERAGE + " REAL," +
                KEY_CARGO_AVERAGE + " REAL," +
                KEY_CARGO_LOWER_AVERAGE + " REAL," +
                KEY_CARGO_UPPER_AVERAGE + " REAL," +
                KEY_SANDSTORM_AVERAGE + " REAL," +
                KEY_ENDGAME_AVERAGE + " REAL," +
                KEY_POINTS_AVERAGE + " REAL" + ")";
        db.execSQL(CREATE_TEAMS_TABLE);

        String CREATE_MATCH_TABLE = "CREATE TABLE " + TABLE_MATCH +
                "(" + KEY_TEAMNUMBER_MATCH + " INTEGER," +
                KEY_HATCHES + " INTEGER," +
                KEY_HATCHES_LOWER + " INTEGER," +
                KEY_HATCHES_UPPER + " INTEGER," +
                KEY_CARGO + " INTEGER," +
                KEY_CARGO_LOWER + " INTEGER," +
                KEY_CARGO_UPPER + " INTEGER," +
                KEY_SANDSTORM + " INTEGER," +
                KEY_ENDGAME + " INTEGER," +
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
        Cursor cursor_hatches = db.rawQuery("SELECT SUM(hatches_delivered)" + " AS hatches FROM " + TABLE_MATCH + " WHERE " + KEY_TEAMNUMBER_MATCH + " = " + teamNumber, null);
        Cursor cursor_hatches_lower = db.rawQuery("SELECT SUM(hatches_lower_delivered)" + " AS hatches_lower FROM " + TABLE_MATCH + " WHERE " + KEY_TEAMNUMBER_MATCH + " = " + teamNumber, null);
        Cursor cursor_hatches_upper = db.rawQuery("SELECT SUM(hatches_upper_delivered)" + " AS hatches_upper FROM " + TABLE_MATCH + " WHERE " + KEY_TEAMNUMBER_MATCH + " = " + teamNumber, null);
//        Cursor cursor_hatches_dropped = db.rawQuery("SELECT SUM(hatches_dropped_delivered)" + " AS hatches_dropped FROM " + TABLE_MATCH + " WHERE " + KEY_TEAMNUMBER_MATCH + " = " + teamNumber, null);
        Cursor cursor_cargo = db.rawQuery("SELECT SUM(cargo_delivered)" + " AS cargo FROM " + TABLE_MATCH + " WHERE " + KEY_TEAMNUMBER_MATCH + " = " + teamNumber, null);
        Cursor cursor_cargo_lower = db.rawQuery("SELECT SUM(cargo_lower_delivered)" + " AS cargo_lower FROM " + TABLE_MATCH + " WHERE " + KEY_TEAMNUMBER_MATCH + " = " + teamNumber, null);
        Cursor cursor_cargo_upper = db.rawQuery("SELECT SUM(cargo_upper_delivered)" + " AS cargo_upper FROM " + TABLE_MATCH + " WHERE " + KEY_TEAMNUMBER_MATCH + " = " + teamNumber, null);
        Cursor cursor_sandstorm = db.rawQuery("SELECT SUM(sandstorm_delivered)" + " AS sandstorm FROM " + TABLE_MATCH + " WHERE " + KEY_TEAMNUMBER_MATCH + " = " + teamNumber, null);
        Cursor cursor_endgame = db.rawQuery("SELECT SUM(endgame_delivered)" + " AS endgame FROM " + TABLE_MATCH + " WHERE " + KEY_TEAMNUMBER_MATCH + " = " + teamNumber, null);

        Cursor cursor_matches = db.rawQuery("SELECT MAX(match) AS matches_max FROM " + TABLE_MATCH + " WHERE " + KEY_TEAMNUMBER_MATCH + " = " + teamNumber, null);

        Cursor cursor_hatches_average = db.rawQuery("SELECT AVG(hatches_delivered)" + " AS hatches_average FROM " + TABLE_MATCH + " WHERE " + KEY_TEAMNUMBER_MATCH + " = " + teamNumber, null);
        Cursor cursor_hatches_lower_average = db.rawQuery("SELECT AVG(hatches_lower_delivered)" + " AS hatches_lower_average FROM " + TABLE_MATCH + " WHERE " + KEY_TEAMNUMBER_MATCH + " = " + teamNumber, null);
        Cursor cursor_hatches_upper_average = db.rawQuery("SELECT AVG(hatches_upper_delivered)" + " AS hatches_upper_average FROM " + TABLE_MATCH + " WHERE " + KEY_TEAMNUMBER_MATCH + " = " + teamNumber, null);
//        Cursor cursor_hatches_dropped_average = db.rawQuery("SELECT AVG(hatches_dropped_delivered)" + " AS hatches_dropped_average FROM " + TABLE_MATCH + " WHERE " + KEY_TEAMNUMBER_MATCH + " = " + teamNumber, null);
        Cursor cursor_cargo_average = db.rawQuery("SELECT AVG(cargo_delivered)" + " AS cargo_average FROM " + TABLE_MATCH + " WHERE " + KEY_TEAMNUMBER_MATCH + " = " + teamNumber, null);
        Cursor cursor_cargo_lower_average = db.rawQuery("SELECT AVG(cargo_lower_delivered)" + " AS cargo_lower_average FROM " + TABLE_MATCH + " WHERE " + KEY_TEAMNUMBER_MATCH + " = " + teamNumber, null);
        Cursor cursor_cargo_upper_average = db.rawQuery("SELECT AVG(cargo_upper_delivered)" + " AS cargo_upper_average FROM " + TABLE_MATCH + " WHERE " + KEY_TEAMNUMBER_MATCH + " = " + teamNumber, null);
        Cursor cursor_sandstorm_average = db.rawQuery("SELECT AVG(sandstorm_delivered)" + " AS sandstorm_average FROM " + TABLE_MATCH + " WHERE " + KEY_TEAMNUMBER_MATCH + " = " + teamNumber, null);
        Cursor cursor_endgame_average = db.rawQuery("SELECT AVG(endgame_delivered)" + " AS endgame_average FROM " + TABLE_MATCH + " WHERE " + KEY_TEAMNUMBER_MATCH + " = " + teamNumber, null);

        cursor_hatches.moveToFirst();
        cursor_hatches_lower.moveToFirst();
        cursor_hatches_upper.moveToFirst();
//        cursor_hatches_dropped.moveToFirst();
        cursor_cargo.moveToFirst();
        cursor_cargo_lower.moveToFirst();
        cursor_cargo_upper.moveToFirst();
        cursor_cargo.moveToFirst();
        cursor_sandstorm.moveToFirst();
        cursor_endgame.moveToFirst();
        cursor_matches.moveToFirst();
        cursor_hatches_average.moveToFirst();
        cursor_hatches_lower_average.moveToFirst();
        cursor_hatches_upper_average.moveToFirst();
//        cursor_hatches_dropped_average.moveToFirst();
        cursor_cargo_average.moveToFirst();
        cursor_cargo_lower_average.moveToFirst();
        cursor_cargo_upper_average.moveToFirst();
        cursor_sandstorm_average.moveToFirst();
        cursor_endgame_average.moveToFirst();

        int hatches = cursor_hatches.getInt(cursor_hatches.getColumnIndex("hatches"));
        int hatches_lower = cursor_hatches_lower.getInt(cursor_hatches_lower.getColumnIndex("hatches_lower"));
        int hatches_upper = cursor_hatches_upper.getInt(cursor_hatches_upper.getColumnIndex("hatches_upper"));
//        int hatches_dropped = cursor_hatches_dropped.getInt(cursor_hatches_dropped.getColumnIndex("hatches_dropped"));
        int cargo = cursor_cargo.getInt(cursor_cargo.getColumnIndex("cargo"));
        int cargo_lower = cursor_cargo_lower.getInt(cursor_cargo_lower.getColumnIndex("cargo_lower"));
        int cargo_upper = cursor_cargo_upper.getInt(cursor_cargo_upper.getColumnIndex("cargo_upper"));
        int sandstorm = cursor_sandstorm.getInt(cursor_sandstorm.getColumnIndex("sandstorm"));
        int endgame = cursor_endgame.getInt(cursor_endgame.getColumnIndex("endgame"));
        int matches = cursor_matches.getInt(cursor_matches.getColumnIndex("matches_max"));

        double hatches_average = cursor_hatches_average.getDouble(cursor_hatches_average.getColumnIndex("hatches_average"));
        double hatches_lower_average = cursor_hatches_lower_average.getDouble(cursor_hatches_lower_average.getColumnIndex("hatches_lower_average"));
        double hatches_upper_average = cursor_hatches_upper_average.getDouble(cursor_hatches_upper_average.getColumnIndex("hatches_upper_average"));
//        double hatches_dropped_average = cursor_hatches_dropped_average.getDouble(cursor_hatches_dropped_average.getColumnIndex("hatches_dropped_average"));
        double cargo_average = cursor_cargo_average.getDouble(cursor_cargo_average.getColumnIndex("cargo_average"));
        double cargo_lower_average = cursor_cargo_lower_average.getDouble(cursor_cargo_lower_average.getColumnIndex("cargo_lower_average"));
        double cargo_upper_average = cursor_cargo_upper_average.getDouble(cursor_cargo_upper_average.getColumnIndex("cargo_upper_average"));
        double sandstorm_average = cursor_sandstorm_average.getDouble(cursor_sandstorm_average.getColumnIndex("sandstorm_average"));
        double endgame_average = cursor_endgame_average.getDouble(cursor_endgame_average.getColumnIndex("endgame_average"));

        double hatches_average_result = (double) Math.round(hatches_average * 100d) / 100d;
        double hatches_lower_average_result = (double) Math.round(hatches_lower_average * 100d) / 100d;
        double hatches_upper_average_result = (double) Math.round(hatches_upper_average * 100d) / 100d;
//        double hatches_dropped_average_result = (double) Math.round(hatches_dropped_average * 100d) / 100d;
        double cargo_average_result = (double) Math.round(cargo_average * 100d) / 100d;
        double cargo_lower_average_result = (double) Math.round(cargo_lower_average * 100d) / 100d;
        double cargo_upper_average_result = (double) Math.round(cargo_upper_average * 100d) / 100d;
        double sandstorm_average_result = (double) Math.round(sandstorm_average * 100d) / 100d;
        double endgame_average_result = (double) Math.round(endgame_average * 100d) / 100d;

        int sandstorm_points = 0;

        if (sandstorm == 1) {
            sandstorm_points = 3;
        } else if (sandstorm == 2) {
            sandstorm_points = 6;
        }

        int endgame_points = 0;

        if (endgame == 1) {
            endgame_points = 3;
        } else if (endgame == 2) {
            endgame_points = 6;
        } else if (endgame == 3) {
            endgame_points = 12;
        }

        int points_total = hatches * 2 + cargo * 3 + sandstorm_points + endgame_points;
        double points_average = (double) points_total / (double) matches;

        ContentValues values = new ContentValues();
        values.put(KEY_TEAMNUMBER, teamNumber);
        values.put(KEY_HATCHES_TOTAL, hatches);
        values.put(KEY_HATCHES_LOWER_TOTAL, hatches_lower);
        values.put(KEY_HATCHES_UPPER_TOTAL, hatches_upper);
//        values.put(KEY_HATCHES_DROPPED_TOTAL, hatches_dropped);
        values.put(KEY_CARGO_TOTAL, cargo);
        values.put(KEY_CARGO_LOWER_TOTAL, cargo_lower);
        values.put(KEY_CARGO_UPPER_TOTAL, cargo_upper);
        values.put(KEY_SANDSTORM_TOTAL, sandstorm);
        values.put(KEY_ENDGAME_TOTAL, endgame);
        values.put(KEY_MATCH_TOTAL, matches);
        values.put(KEY_POINTS_TOTAL, points_total);
        values.put(KEY_HATCHES_AVERAGE, hatches_average_result);
        values.put(KEY_HATCHES_LOWER_AVERAGE, hatches_lower_average_result);
        values.put(KEY_HATCHES_UPPER_AVERAGE, hatches_upper_average_result);
//        values.put(KEY_HATCHES_DROPPED_AVERAGE, hatches_dropped_average_result);
        values.put(KEY_CARGO_AVERAGE, cargo_average_result);
        values.put(KEY_CARGO_LOWER_AVERAGE, cargo_lower_average_result);
        values.put(KEY_CARGO_UPPER_AVERAGE, cargo_upper_average_result);
        values.put(KEY_SANDSTORM_AVERAGE, sandstorm_average_result);
        values.put(KEY_ENDGAME_AVERAGE, endgame_average_result);
        values.put(KEY_POINTS_AVERAGE, points_average);

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
            values.put(KEY_HATCHES, team.getHatches());
            values.put(KEY_HATCHES_LOWER, team.getHatchesLower());
            values.put(KEY_HATCHES_UPPER, team.getHatchesUpper());
//            values.put(KEY_HATCHES_DROPPED, team.getHatchesDropped());
            values.put(KEY_CARGO, team.getCargo());
            values.put(KEY_CARGO_LOWER, team.getCargoLower());
            values.put(KEY_CARGO_UPPER, team.getCargoUpper());
            values.put(KEY_SANDSTORM, team.getSandstorm());
            values.put(KEY_ENDGAME, team.getEndgame());
            values.put(KEY_MATCH, 1);

            db.insert(TABLE_MATCH, null, values);
        } else {
            cursor.moveToFirst();
            ContentValues values = new ContentValues();

            values.put(KEY_TEAMNUMBER_MATCH, team.getTeamNumber());
            values.put(KEY_HATCHES, team.getHatches());
            values.put(KEY_HATCHES_LOWER, team.getHatchesLower());
            values.put(KEY_HATCHES_UPPER, team.getHatchesUpper());
//            values.put(KEY_HATCHES_DROPPED, team.getHatchesDropped());
            values.put(KEY_CARGO, team.getCargo());
            values.put(KEY_CARGO_LOWER, team.getCargoLower());
            values.put(KEY_CARGO_UPPER, team.getCargoUpper());
            values.put(KEY_SANDSTORM, team.getSandstorm());
            values.put(KEY_ENDGAME, team.getEndgame());
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

    public void editTeam(int teamNumber, int hatches_lower, int hatches_upper, int hatches_dropped, int cargo_lower, int cargo_upper, int sandstorm, int endgame, int matchN) {
        ContentValues contentValues = new ContentValues();

        contentValues.put(KEY_TEAMNUMBER_MATCH, teamNumber);
        contentValues.put(KEY_HATCHES, hatches_lower + hatches_upper);
        contentValues.put(KEY_HATCHES_LOWER, hatches_lower);
        contentValues.put(KEY_HATCHES_UPPER, hatches_upper);
//        contentValues.put(KEY_HATCHES_DROPPED, hatches_dropped);
        contentValues.put(KEY_CARGO, cargo_lower + cargo_upper);
        contentValues.put(KEY_CARGO_LOWER, cargo_lower);
        contentValues.put(KEY_CARGO_UPPER, cargo_upper);
        contentValues.put(KEY_SANDSTORM, sandstorm);
        contentValues.put(KEY_ENDGAME, endgame);
        contentValues.put(KEY_MATCH, matchN);

        db.update(TABLE_MATCH, contentValues, "teamNumber=" + teamNumber + " AND match=" + matchN, null);

        setTeamsTable(teamNumber);
    }

    public Cursor findData(String teamNumber) {
        return db.rawQuery("SELECT * FROM " + TABLE_TEAMS + " ORDER BY CAST(" + teamNumber + " AS INTEGER)", null);

//        Cursor cursor = db.query(TABLE_TEAMS, new String[]{
//                KEY_TEAMNUMBER,
//                KEY_HATCHES_TOTAL,
//                KEY_HATCHES_LOWER_TOTAL,
//                KEY_HATCHES_UPPER_TOTAL,
////                    KEY_HATCHES_DROPPED_TOTAL,
//                KEY_CARGO_TOTAL,
//                KEY_CARGO_LOWER_TOTAL,
//                KEY_CARGO_UPPER_TOTAL,
//                KEY_SANDSTORM_TOTAL,
//                KEY_ENDGAME_TOTAL,
//                KEY_POINTS_TOTAL,
//                KEY_MATCH_TOTAL,
//                KEY_HATCHES_AVERAGE,
//                KEY_HATCHES_LOWER_AVERAGE,
//                KEY_HATCHES_UPPER_AVERAGE,
////                    KEY_HATCHES_DROPPED_AVERAGE,
//                KEY_CARGO_AVERAGE,
//                KEY_CARGO_LOWER_AVERAGE,
//                KEY_CARGO_UPPER_AVERAGE,
//                KEY_SANDSTORM_AVERAGE,
//                KEY_ENDGAME_AVERAGE,
//                KEY_POINTS_AVERAGE,
//        }, null, null, null, null, KEY_TEAMNUMBER + " DESC");
//        if (cursor != null) {
//            cursor.moveToFirst();
//        }
//
//        return cursor;
    }

    public Cursor sortData(String type, String average_total) {
        Cursor cursor = db.rawQuery("", null);
        if (type.equals("Search")) {
            cursor = db.query(TABLE_TEAMS, new String[]{
                    KEY_TEAMNUMBER,
                    KEY_HATCHES_TOTAL,
                    KEY_HATCHES_LOWER_TOTAL,
                    KEY_HATCHES_UPPER_TOTAL,
//                    KEY_HATCHES_DROPPED_TOTAL,
                    KEY_CARGO_TOTAL,
                    KEY_CARGO_LOWER_TOTAL,
                    KEY_CARGO_UPPER_TOTAL,
                    KEY_SANDSTORM_TOTAL,
                    KEY_ENDGAME_TOTAL,
                    KEY_POINTS_TOTAL,
                    KEY_MATCH_TOTAL,
                    KEY_HATCHES_AVERAGE,
                    KEY_HATCHES_LOWER_AVERAGE,
                    KEY_HATCHES_UPPER_AVERAGE,
//                    KEY_HATCHES_DROPPED_AVERAGE,
                    KEY_CARGO_AVERAGE,
                    KEY_CARGO_LOWER_AVERAGE,
                    KEY_CARGO_UPPER_AVERAGE,
                    KEY_SANDSTORM_AVERAGE,
                    KEY_ENDGAME_AVERAGE,
                    KEY_POINTS_AVERAGE,
            }, null, null, null, null, KEY_TEAMNUMBER + " ASC");
            if (cursor != null) {
                cursor.moveToFirst();
            }
        } else if (type.equals("Hatches Both") && average_total.equals("Averages")) {
            cursor = db.query(TABLE_TEAMS, new String[]{
                    KEY_TEAMNUMBER,
                    KEY_HATCHES_TOTAL,
                    KEY_HATCHES_LOWER_TOTAL,
                    KEY_HATCHES_UPPER_TOTAL,
//                    KEY_HATCHES_DROPPED_TOTAL,
                    KEY_CARGO_TOTAL,
                    KEY_CARGO_LOWER_TOTAL,
                    KEY_CARGO_UPPER_TOTAL,
                    KEY_SANDSTORM_TOTAL,
                    KEY_ENDGAME_TOTAL,
                    KEY_POINTS_TOTAL,
                    KEY_MATCH_TOTAL,
                    KEY_HATCHES_AVERAGE,
                    KEY_HATCHES_LOWER_AVERAGE,
                    KEY_HATCHES_UPPER_AVERAGE,
//                    KEY_HATCHES_DROPPED_AVERAGE,
                    KEY_CARGO_AVERAGE,
                    KEY_CARGO_LOWER_AVERAGE,
                    KEY_CARGO_UPPER_AVERAGE,
                    KEY_SANDSTORM_AVERAGE,
                    KEY_ENDGAME_AVERAGE,
                    KEY_POINTS_AVERAGE,
            }, null, null, null, null, KEY_HATCHES_AVERAGE + " DESC");
            if (cursor != null) {
                cursor.moveToFirst();
            }
        } else if (type.equals("Hatches Lower") && average_total.equals("Averages")) {
            cursor = db.query(TABLE_TEAMS, new String[]{
                    KEY_TEAMNUMBER,
                    KEY_HATCHES_TOTAL,
                    KEY_HATCHES_LOWER_TOTAL,
                    KEY_HATCHES_UPPER_TOTAL,
//                    KEY_HATCHES_DROPPED_TOTAL,
                    KEY_CARGO_TOTAL,
                    KEY_CARGO_LOWER_TOTAL,
                    KEY_CARGO_UPPER_TOTAL,
                    KEY_SANDSTORM_TOTAL,
                    KEY_ENDGAME_TOTAL,
                    KEY_POINTS_TOTAL,
                    KEY_MATCH_TOTAL,
                    KEY_HATCHES_AVERAGE,
                    KEY_HATCHES_LOWER_AVERAGE,
                    KEY_HATCHES_UPPER_AVERAGE,
//                    KEY_HATCHES_DROPPED_AVERAGE,
                    KEY_CARGO_AVERAGE,
                    KEY_CARGO_LOWER_AVERAGE,
                    KEY_CARGO_UPPER_AVERAGE,
                    KEY_SANDSTORM_AVERAGE,
                    KEY_ENDGAME_AVERAGE,
                    KEY_POINTS_AVERAGE,
            }, null, null, null, null, KEY_HATCHES_LOWER_AVERAGE + " DESC");
            if (cursor != null) {
                cursor.moveToFirst();
            }
        } else if (type.equals("Hatches Upper") && average_total.equals("Averages")) {
            cursor = db.query(TABLE_TEAMS, new String[]{
                    KEY_TEAMNUMBER,
                    KEY_HATCHES_TOTAL,
                    KEY_HATCHES_LOWER_TOTAL,
                    KEY_HATCHES_UPPER_TOTAL,
//                    KEY_HATCHES_DROPPED_TOTAL,
                    KEY_CARGO_TOTAL,
                    KEY_CARGO_LOWER_TOTAL,
                    KEY_CARGO_UPPER_TOTAL,
                    KEY_SANDSTORM_TOTAL,
                    KEY_ENDGAME_TOTAL,
                    KEY_POINTS_TOTAL,
                    KEY_MATCH_TOTAL,
                    KEY_HATCHES_AVERAGE,
                    KEY_HATCHES_LOWER_AVERAGE,
                    KEY_HATCHES_UPPER_AVERAGE,
//                    KEY_HATCHES_DROPPED_AVERAGE,
                    KEY_CARGO_AVERAGE,
                    KEY_CARGO_LOWER_AVERAGE,
                    KEY_CARGO_UPPER_AVERAGE,
                    KEY_SANDSTORM_AVERAGE,
                    KEY_ENDGAME_AVERAGE,
                    KEY_POINTS_AVERAGE,
            }, null, null, null, null, KEY_HATCHES_UPPER_AVERAGE + " DESC");
            if (cursor != null) {
                cursor.moveToFirst();
            }
//        } else if (type.equals("Hatches Dropped") && average_total.equals("Averages")) {
//            cursor = db.query(TABLE_TEAMS, new String[]{
//                    KEY_TEAMNUMBER,
//                    KEY_HATCHES_TOTAL,
//                    KEY_HATCHES_LOWER_TOTAL,
//                    KEY_HATCHES_UPPER_TOTAL,
//                    KEY_HATCHES_DROPPED_TOTAL,
//                    KEY_CARGO_TOTAL,
//                    KEY_CARGO_LOWER_TOTAL,
//                    KEY_CARGO_UPPER_TOTAL,
//                    KEY_SANDSTORM_TOTAL,
//                    KEY_ENDGAME_TOTAL,
//                    KEY_POINTS_TOTAL,
//                    KEY_MATCH_TOTAL,
//                    KEY_HATCHES_AVERAGE,
//                    KEY_HATCHES_LOWER_AVERAGE,
//                    KEY_HATCHES_UPPER_AVERAGE,
//                    KEY_HATCHES_DROPPED_AVERAGE,
//                    KEY_CARGO_AVERAGE,
//                    KEY_CARGO_LOWER_AVERAGE,
//                    KEY_CARGO_UPPER_AVERAGE,
//                    KEY_SANDSTORM_AVERAGE,
//                    KEY_ENDGAME_AVERAGE,
//                    KEY_POINTS_AVERAGE,
//            }, null, null, null, null, KEY_HATCHES_DROPPED_AVERAGE + " DESC");
//            if (cursor != null) {
//                cursor.moveToFirst();
//            }
        } else if (type.equals("Cargo Both") && average_total.equals("Averages")) {
            cursor = db.query(TABLE_TEAMS, new String[]{
                    KEY_TEAMNUMBER,
                    KEY_HATCHES_TOTAL,
                    KEY_HATCHES_LOWER_TOTAL,
                    KEY_HATCHES_UPPER_TOTAL,
//                    KEY_HATCHES_DROPPED_TOTAL,
                    KEY_CARGO_TOTAL,
                    KEY_CARGO_LOWER_TOTAL,
                    KEY_CARGO_UPPER_TOTAL,
                    KEY_SANDSTORM_TOTAL,
                    KEY_ENDGAME_TOTAL,
                    KEY_POINTS_TOTAL,
                    KEY_MATCH_TOTAL,
                    KEY_HATCHES_AVERAGE,
                    KEY_HATCHES_LOWER_AVERAGE,
                    KEY_HATCHES_UPPER_AVERAGE,
//                    KEY_HATCHES_DROPPED_AVERAGE,
                    KEY_CARGO_AVERAGE,
                    KEY_CARGO_LOWER_AVERAGE,
                    KEY_CARGO_UPPER_AVERAGE,
                    KEY_SANDSTORM_AVERAGE,
                    KEY_ENDGAME_AVERAGE,
                    KEY_POINTS_AVERAGE,
            }, null, null, null, null, KEY_CARGO_AVERAGE + " DESC");
            if (cursor != null) {
                cursor.moveToFirst();
            }
        } else if (type.equals("Cargo Lower") && average_total.equals("Averages")) {
            cursor = db.query(TABLE_TEAMS, new String[]{
                    KEY_TEAMNUMBER,
                    KEY_HATCHES_TOTAL,
                    KEY_HATCHES_LOWER_TOTAL,
                    KEY_HATCHES_UPPER_TOTAL,
//                    KEY_HATCHES_DROPPED_TOTAL,
                    KEY_CARGO_TOTAL,
                    KEY_CARGO_LOWER_TOTAL,
                    KEY_CARGO_UPPER_TOTAL,
                    KEY_SANDSTORM_TOTAL,
                    KEY_ENDGAME_TOTAL,
                    KEY_POINTS_TOTAL,
                    KEY_MATCH_TOTAL,
                    KEY_HATCHES_AVERAGE,
                    KEY_HATCHES_LOWER_AVERAGE,
                    KEY_HATCHES_UPPER_AVERAGE,
//                    KEY_HATCHES_DROPPED_AVERAGE,
                    KEY_CARGO_AVERAGE,
                    KEY_CARGO_LOWER_AVERAGE,
                    KEY_CARGO_UPPER_AVERAGE,
                    KEY_SANDSTORM_AVERAGE,
                    KEY_ENDGAME_AVERAGE,
                    KEY_POINTS_AVERAGE,
            }, null, null, null, null, KEY_CARGO_LOWER_AVERAGE + " DESC");
            if (cursor != null) {
                cursor.moveToFirst();
            }
        } else if (type.equals("Cargo Upper") && average_total.equals("Averages")) {
            cursor = db.query(TABLE_TEAMS, new String[]{
                    KEY_TEAMNUMBER,
                    KEY_HATCHES_TOTAL,
                    KEY_HATCHES_LOWER_TOTAL,
                    KEY_HATCHES_UPPER_TOTAL,
//                    KEY_HATCHES_DROPPED_TOTAL,
                    KEY_CARGO_TOTAL,
                    KEY_CARGO_LOWER_TOTAL,
                    KEY_CARGO_UPPER_TOTAL,
                    KEY_SANDSTORM_TOTAL,
                    KEY_ENDGAME_TOTAL,
                    KEY_POINTS_TOTAL,
                    KEY_MATCH_TOTAL,
                    KEY_HATCHES_AVERAGE,
                    KEY_HATCHES_LOWER_AVERAGE,
                    KEY_HATCHES_UPPER_AVERAGE,
//                    KEY_HATCHES_DROPPED_AVERAGE,
                    KEY_CARGO_AVERAGE,
                    KEY_CARGO_LOWER_AVERAGE,
                    KEY_CARGO_UPPER_AVERAGE,
                    KEY_SANDSTORM_AVERAGE,
                    KEY_ENDGAME_AVERAGE,
                    KEY_POINTS_AVERAGE,
            }, null, null, null, null, KEY_CARGO_UPPER_AVERAGE + " DESC");
            if (cursor != null) {
                cursor.moveToFirst();
            }
        } else if (type.equals("Sandstorm") && average_total.equals("Averages")) {
            cursor = db.query(TABLE_TEAMS, new String[]{
                    KEY_TEAMNUMBER,
                    KEY_HATCHES_TOTAL,
                    KEY_HATCHES_LOWER_TOTAL,
                    KEY_HATCHES_UPPER_TOTAL,
//                    KEY_HATCHES_DROPPED_TOTAL,
                    KEY_CARGO_TOTAL,
                    KEY_CARGO_LOWER_TOTAL,
                    KEY_CARGO_UPPER_TOTAL,
                    KEY_SANDSTORM_TOTAL,
                    KEY_ENDGAME_TOTAL,
                    KEY_POINTS_TOTAL,
                    KEY_MATCH_TOTAL,
                    KEY_HATCHES_AVERAGE,
                    KEY_HATCHES_LOWER_AVERAGE,
                    KEY_HATCHES_UPPER_AVERAGE,
//                    KEY_HATCHES_DROPPED_AVERAGE,
                    KEY_CARGO_AVERAGE,
                    KEY_CARGO_LOWER_AVERAGE,
                    KEY_CARGO_UPPER_AVERAGE,
                    KEY_SANDSTORM_AVERAGE,
                    KEY_ENDGAME_AVERAGE,
                    KEY_POINTS_AVERAGE,
            }, null, null, null, null, KEY_SANDSTORM_AVERAGE + " DESC");
            if (cursor != null) {
                cursor.moveToFirst();
            }
        } else if (type.equals("Endgame") && average_total.equals("Averages")) {
            cursor = db.query(TABLE_TEAMS, new String[]{
                    KEY_TEAMNUMBER,
                    KEY_HATCHES_TOTAL,
                    KEY_HATCHES_LOWER_TOTAL,
                    KEY_HATCHES_UPPER_TOTAL,
//                    KEY_HATCHES_DROPPED_TOTAL,
                    KEY_CARGO_TOTAL,
                    KEY_CARGO_LOWER_TOTAL,
                    KEY_CARGO_UPPER_TOTAL,
                    KEY_SANDSTORM_TOTAL,
                    KEY_ENDGAME_TOTAL,
                    KEY_POINTS_TOTAL,
                    KEY_MATCH_TOTAL,
                    KEY_HATCHES_AVERAGE,
                    KEY_HATCHES_LOWER_AVERAGE,
                    KEY_HATCHES_UPPER_AVERAGE,
//                    KEY_HATCHES_DROPPED_AVERAGE,
                    KEY_CARGO_AVERAGE,
                    KEY_CARGO_LOWER_AVERAGE,
                    KEY_CARGO_UPPER_AVERAGE,
                    KEY_SANDSTORM_AVERAGE,
                    KEY_ENDGAME_AVERAGE,
                    KEY_POINTS_AVERAGE,
            }, null, null, null, null, KEY_ENDGAME_AVERAGE + " DESC");
            if (cursor != null) {
                cursor.moveToFirst();
            }
        } else if (type.equals("Points") && average_total.equals("Averages")) {
            cursor = db.query(TABLE_TEAMS, new String[]{
                    KEY_TEAMNUMBER,
                    KEY_HATCHES_TOTAL,
                    KEY_HATCHES_LOWER_TOTAL,
                    KEY_HATCHES_UPPER_TOTAL,
//                    KEY_HATCHES_DROPPED_TOTAL,
                    KEY_CARGO_TOTAL,
                    KEY_CARGO_LOWER_TOTAL,
                    KEY_CARGO_UPPER_TOTAL,
                    KEY_SANDSTORM_TOTAL,
                    KEY_ENDGAME_TOTAL,
                    KEY_POINTS_TOTAL,
                    KEY_MATCH_TOTAL,
                    KEY_HATCHES_AVERAGE,
                    KEY_HATCHES_LOWER_AVERAGE,
                    KEY_HATCHES_UPPER_AVERAGE,
//                    KEY_HATCHES_DROPPED_AVERAGE,
                    KEY_CARGO_AVERAGE,
                    KEY_CARGO_LOWER_AVERAGE,
                    KEY_CARGO_UPPER_AVERAGE,
                    KEY_SANDSTORM_AVERAGE,
                    KEY_ENDGAME_AVERAGE,
                    KEY_POINTS_AVERAGE,
            }, null, null, null, null, KEY_POINTS_AVERAGE + " DESC");
            if (cursor != null) {
                cursor.moveToFirst();
            }
        } else if (type.equals("Hatches Both") && average_total.equals("Totals")) {
            cursor = db.query(TABLE_TEAMS, new String[]{
                    KEY_TEAMNUMBER,
                    KEY_HATCHES_TOTAL,
                    KEY_HATCHES_LOWER_TOTAL,
                    KEY_HATCHES_UPPER_TOTAL,
//                    KEY_HATCHES_DROPPED_TOTAL,
                    KEY_CARGO_TOTAL,
                    KEY_CARGO_LOWER_TOTAL,
                    KEY_CARGO_UPPER_TOTAL,
                    KEY_SANDSTORM_TOTAL,
                    KEY_ENDGAME_TOTAL,
                    KEY_POINTS_TOTAL,
                    KEY_MATCH_TOTAL,
                    KEY_HATCHES_AVERAGE,
                    KEY_HATCHES_LOWER_AVERAGE,
                    KEY_HATCHES_UPPER_AVERAGE,
//                    KEY_HATCHES_DROPPED_AVERAGE,
                    KEY_CARGO_AVERAGE,
                    KEY_CARGO_LOWER_AVERAGE,
                    KEY_CARGO_UPPER_AVERAGE,
                    KEY_SANDSTORM_AVERAGE,
                    KEY_ENDGAME_AVERAGE,
                    KEY_POINTS_AVERAGE,
            }, null, null, null, null, KEY_HATCHES_TOTAL + " DESC");
            if (cursor != null) {
                cursor.moveToFirst();
            }
        } else if (type.equals("Hatches Lower") && average_total.equals("Totals")) {
            cursor = db.query(TABLE_TEAMS, new String[]{
                    KEY_TEAMNUMBER,
                    KEY_HATCHES_TOTAL,
                    KEY_HATCHES_LOWER_TOTAL,
                    KEY_HATCHES_UPPER_TOTAL,
//                    KEY_HATCHES_DROPPED_TOTAL,
                    KEY_CARGO_TOTAL,
                    KEY_CARGO_LOWER_TOTAL,
                    KEY_CARGO_UPPER_TOTAL,
                    KEY_SANDSTORM_TOTAL,
                    KEY_ENDGAME_TOTAL,
                    KEY_POINTS_TOTAL,
                    KEY_MATCH_TOTAL,
                    KEY_HATCHES_AVERAGE,
                    KEY_HATCHES_LOWER_AVERAGE,
                    KEY_HATCHES_UPPER_AVERAGE,
//                    KEY_HATCHES_DROPPED_AVERAGE,
                    KEY_CARGO_AVERAGE,
                    KEY_CARGO_LOWER_AVERAGE,
                    KEY_CARGO_UPPER_AVERAGE,
                    KEY_SANDSTORM_AVERAGE,
                    KEY_ENDGAME_AVERAGE,
                    KEY_POINTS_AVERAGE,
            }, null, null, null, null, KEY_HATCHES_LOWER_TOTAL + " DESC");
            if (cursor != null) {
                cursor.moveToFirst();
            }
        } else if (type.equals("Hatches Upper") && average_total.equals("Totals")) {
            cursor = db.query(TABLE_TEAMS, new String[]{
                    KEY_TEAMNUMBER,
                    KEY_HATCHES_TOTAL,
                    KEY_HATCHES_LOWER_TOTAL,
                    KEY_HATCHES_UPPER_TOTAL,
//                    KEY_HATCHES_DROPPED_TOTAL,
                    KEY_CARGO_TOTAL,
                    KEY_CARGO_LOWER_TOTAL,
                    KEY_CARGO_UPPER_TOTAL,
                    KEY_SANDSTORM_TOTAL,
                    KEY_ENDGAME_TOTAL,
                    KEY_POINTS_TOTAL,
                    KEY_MATCH_TOTAL,
                    KEY_HATCHES_AVERAGE,
                    KEY_HATCHES_LOWER_AVERAGE,
                    KEY_HATCHES_UPPER_AVERAGE,
//                    KEY_HATCHES_DROPPED_AVERAGE,
                    KEY_CARGO_AVERAGE,
                    KEY_CARGO_LOWER_AVERAGE,
                    KEY_CARGO_UPPER_AVERAGE,
                    KEY_SANDSTORM_AVERAGE,
                    KEY_ENDGAME_AVERAGE,
                    KEY_POINTS_AVERAGE,
            }, null, null, null, null, KEY_HATCHES_UPPER_TOTAL + " DESC");
            if (cursor != null) {
                cursor.moveToFirst();
            }
//        } else if (type.equals("Hatches Dropped") && average_total.equals("Totals")) {
//            cursor = db.query(TABLE_TEAMS, new String[]{
//                    KEY_TEAMNUMBER,
//                    KEY_HATCHES_TOTAL,
//                    KEY_HATCHES_LOWER_TOTAL,
//                    KEY_HATCHES_UPPER_TOTAL,
////                    KEY_HATCHES_DROPPED_TOTAL,
//                    KEY_CARGO_TOTAL,
//                    KEY_CARGO_LOWER_TOTAL,
//                    KEY_CARGO_UPPER_TOTAL,
//                    KEY_SANDSTORM_TOTAL,
//                    KEY_ENDGAME_TOTAL,
//                    KEY_POINTS_TOTAL,
//                    KEY_MATCH_TOTAL,
//                    KEY_HATCHES_AVERAGE,
//                    KEY_HATCHES_LOWER_AVERAGE,
//                    KEY_HATCHES_UPPER_AVERAGE,
////                    KEY_HATCHES_DROPPED_AVERAGE,
//                    KEY_CARGO_AVERAGE,
//                    KEY_CARGO_LOWER_AVERAGE,
//                    KEY_CARGO_UPPER_AVERAGE,
//                    KEY_SANDSTORM_AVERAGE,
//                    KEY_ENDGAME_AVERAGE,
//                    KEY_POINTS_AVERAGE,
//            }, null, null, null, null, KEY_HATCHES_DROPPED_TOTAL + " DESC");
//            if (cursor != null) {
//                cursor.moveToFirst();
//            }
        } else if (type.equals("Cargo Both") && average_total.equals("Totals")) {
            cursor = db.query(TABLE_TEAMS, new String[]{
                    KEY_TEAMNUMBER,
                    KEY_HATCHES_TOTAL,
                    KEY_HATCHES_LOWER_TOTAL,
                    KEY_HATCHES_UPPER_TOTAL,
//                    KEY_HATCHES_DROPPED_TOTAL,
                    KEY_CARGO_TOTAL,
                    KEY_CARGO_LOWER_TOTAL,
                    KEY_CARGO_UPPER_TOTAL,
                    KEY_SANDSTORM_TOTAL,
                    KEY_ENDGAME_TOTAL,
                    KEY_POINTS_TOTAL,
                    KEY_MATCH_TOTAL,
                    KEY_HATCHES_AVERAGE,
                    KEY_HATCHES_LOWER_AVERAGE,
                    KEY_HATCHES_UPPER_AVERAGE,
//                    KEY_HATCHES_DROPPED_AVERAGE,
                    KEY_CARGO_AVERAGE,
                    KEY_CARGO_LOWER_AVERAGE,
                    KEY_CARGO_UPPER_AVERAGE,
                    KEY_SANDSTORM_AVERAGE,
                    KEY_ENDGAME_AVERAGE,
                    KEY_POINTS_AVERAGE,
            }, null, null, null, null, KEY_CARGO_TOTAL + " DESC");
            if (cursor != null) {
                cursor.moveToFirst();
            }
        } else if (type.equals("Cargo Lower") && average_total.equals("Totals")) {
            cursor = db.query(TABLE_TEAMS, new String[]{
                    KEY_TEAMNUMBER,
                    KEY_HATCHES_TOTAL,
                    KEY_HATCHES_LOWER_TOTAL,
                    KEY_HATCHES_UPPER_TOTAL,
//                    KEY_HATCHES_DROPPED_TOTAL,
                    KEY_CARGO_TOTAL,
                    KEY_CARGO_LOWER_TOTAL,
                    KEY_CARGO_UPPER_TOTAL,
                    KEY_SANDSTORM_TOTAL,
                    KEY_ENDGAME_TOTAL,
                    KEY_POINTS_TOTAL,
                    KEY_MATCH_TOTAL,
                    KEY_HATCHES_AVERAGE,
                    KEY_HATCHES_LOWER_AVERAGE,
                    KEY_HATCHES_UPPER_AVERAGE,
//                    KEY_HATCHES_DROPPED_AVERAGE,
                    KEY_CARGO_AVERAGE,
                    KEY_CARGO_LOWER_AVERAGE,
                    KEY_CARGO_UPPER_AVERAGE,
                    KEY_SANDSTORM_AVERAGE,
                    KEY_ENDGAME_AVERAGE,
                    KEY_POINTS_AVERAGE,
            }, null, null, null, null, KEY_CARGO_LOWER_TOTAL + " DESC");
            if (cursor != null) {
                cursor.moveToFirst();
            }
        } else if (type.equals("Cargo Upper") && average_total.equals("Totals")) {
            cursor = db.query(TABLE_TEAMS, new String[]{
                    KEY_TEAMNUMBER,
                    KEY_HATCHES_TOTAL,
                    KEY_HATCHES_LOWER_TOTAL,
                    KEY_HATCHES_UPPER_TOTAL,
//                    KEY_HATCHES_DROPPED_TOTAL,
                    KEY_CARGO_TOTAL,
                    KEY_CARGO_LOWER_TOTAL,
                    KEY_CARGO_UPPER_TOTAL,
                    KEY_SANDSTORM_TOTAL,
                    KEY_ENDGAME_TOTAL,
                    KEY_POINTS_TOTAL,
                    KEY_MATCH_TOTAL,
                    KEY_HATCHES_AVERAGE,
                    KEY_HATCHES_LOWER_AVERAGE,
                    KEY_HATCHES_UPPER_AVERAGE,
//                    KEY_HATCHES_DROPPED_AVERAGE,
                    KEY_CARGO_AVERAGE,
                    KEY_CARGO_LOWER_AVERAGE,
                    KEY_CARGO_UPPER_AVERAGE,
                    KEY_SANDSTORM_AVERAGE,
                    KEY_ENDGAME_AVERAGE,
                    KEY_POINTS_AVERAGE,
            }, null, null, null, null, KEY_CARGO_UPPER_TOTAL + " DESC");
            if (cursor != null) {
                cursor.moveToFirst();
            }
        } else if (type.equals("Sandstorm") && average_total.equals("Totals")) {
            cursor = db.query(TABLE_TEAMS, new String[]{
                    KEY_TEAMNUMBER,
                    KEY_HATCHES_TOTAL,
                    KEY_HATCHES_LOWER_TOTAL,
                    KEY_HATCHES_UPPER_TOTAL,
//                    KEY_HATCHES_DROPPED_TOTAL,
                    KEY_CARGO_TOTAL,
                    KEY_CARGO_LOWER_TOTAL,
                    KEY_CARGO_UPPER_TOTAL,
                    KEY_SANDSTORM_TOTAL,
                    KEY_ENDGAME_TOTAL,
                    KEY_POINTS_TOTAL,
                    KEY_MATCH_TOTAL,
                    KEY_HATCHES_AVERAGE,
                    KEY_HATCHES_LOWER_AVERAGE,
                    KEY_HATCHES_UPPER_AVERAGE,
//                    KEY_HATCHES_DROPPED_AVERAGE,
                    KEY_CARGO_AVERAGE,
                    KEY_CARGO_LOWER_AVERAGE,
                    KEY_CARGO_UPPER_AVERAGE,
                    KEY_SANDSTORM_AVERAGE,
                    KEY_ENDGAME_AVERAGE,
                    KEY_POINTS_AVERAGE,
            }, null, null, null, null, KEY_SANDSTORM_TOTAL + " DESC");
            if (cursor != null) {
                cursor.moveToFirst();
            }
        } else if (type.equals("Endgame") && average_total.equals("Totals")) {
            cursor = db.query(TABLE_TEAMS, new String[]{
                    KEY_TEAMNUMBER,
                    KEY_HATCHES_TOTAL,
                    KEY_HATCHES_LOWER_TOTAL,
                    KEY_HATCHES_UPPER_TOTAL,
//                    KEY_HATCHES_DROPPED_TOTAL,
                    KEY_CARGO_TOTAL,
                    KEY_CARGO_LOWER_TOTAL,
                    KEY_CARGO_UPPER_TOTAL,
                    KEY_SANDSTORM_TOTAL,
                    KEY_ENDGAME_TOTAL,
                    KEY_POINTS_TOTAL,
                    KEY_MATCH_TOTAL,
                    KEY_HATCHES_AVERAGE,
                    KEY_HATCHES_LOWER_AVERAGE,
                    KEY_HATCHES_UPPER_AVERAGE,
//                    KEY_HATCHES_DROPPED_AVERAGE,
                    KEY_CARGO_AVERAGE,
                    KEY_CARGO_LOWER_AVERAGE,
                    KEY_CARGO_UPPER_AVERAGE,
                    KEY_SANDSTORM_AVERAGE,
                    KEY_ENDGAME_AVERAGE,
                    KEY_POINTS_AVERAGE,
            }, null, null, null, null, KEY_ENDGAME_TOTAL + " DESC");
            if (cursor != null) {
                cursor.moveToFirst();
            }
        } else if (type.equals("Points") && average_total.equals("Totals")) {
            cursor = db.query(TABLE_TEAMS, new String[]{
                    KEY_TEAMNUMBER,
                    KEY_HATCHES_TOTAL,
                    KEY_HATCHES_LOWER_TOTAL,
                    KEY_HATCHES_UPPER_TOTAL,
//                    KEY_HATCHES_DROPPED_TOTAL,
                    KEY_CARGO_TOTAL,
                    KEY_CARGO_LOWER_TOTAL,
                    KEY_CARGO_UPPER_TOTAL,
                    KEY_SANDSTORM_TOTAL,
                    KEY_ENDGAME_TOTAL,
                    KEY_POINTS_TOTAL,
                    KEY_MATCH_TOTAL,
                    KEY_HATCHES_AVERAGE,
                    KEY_HATCHES_LOWER_AVERAGE,
                    KEY_HATCHES_UPPER_AVERAGE,
//                    KEY_HATCHES_DROPPED_AVERAGE,
                    KEY_CARGO_AVERAGE,
                    KEY_CARGO_LOWER_AVERAGE,
                    KEY_CARGO_UPPER_AVERAGE,
                    KEY_SANDSTORM_AVERAGE,
                    KEY_ENDGAME_AVERAGE,
                    KEY_POINTS_AVERAGE,
            }, null, null, null, null, KEY_POINTS_TOTAL + " DESC");
            if (cursor != null) {
                cursor.moveToFirst();
            }
        }
        return cursor;
    }
}