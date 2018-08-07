package android.attendancecalculator;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by The Dark Knight on 01-08-2016.
 */
public class DatabaseHandler extends SQLiteOpenHelper{


    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "AttendanceDatabase.db";
    public static final String TABLE_ATTENDANCE = "attendance";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_LEC = "lecturesAttended";


    //We need to pass database information along to superclass
    public DatabaseHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_ATTENDANCE +
                "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_DATE + " TEXT," + COLUMN_LEC + " INTEGER" + ");";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ATTENDANCE);
        onCreate(db);
    }

    //Method to add a new row to the database
    public void addAttendance(Attendance attendance){

            ContentValues values = new ContentValues();
            values.put(COLUMN_DATE, attendance.get_date());
            values.put(COLUMN_LEC, attendance.get_lecturesAttended());
            SQLiteDatabase db = getWritableDatabase();
            db.insert(TABLE_ATTENDANCE, null, values);
            db.close();

    }


    // Method to convert database to string
    public String databaseToString(){
        String dbString = "";

        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_ATTENDANCE;


        //Cursor points to a location in your results
        Cursor c = db.rawQuery(query, null);
        //Move to the first row in your results
        c.moveToFirst();
        //Position after the last row means the end of the results
        while (!c.isAfterLast()) {
            if (c.getString(c.getColumnIndex("_id")) != null) {

                dbString += c.getString(c.getColumnIndex("date"));
                dbString += " ";
                dbString += "|";
                dbString += " ";

                dbString += c.getString(c.getColumnIndex("lecturesAttended"));
                dbString += "\n";
            }
            c.moveToNext();
        }
        db.close();

        return dbString;
    }

    public double calculateAttendance() {

        int entries = getEntriesCount();

        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_ATTENDANCE;
        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();
        int sumOfLectures = 0;

        while (!c.isAfterLast()) {
            if (c.getString(c.getColumnIndex("_id")) != null) {
                sumOfLectures += c.getInt(c.getColumnIndex("lecturesAttended"));
            }

            c.moveToNext();
        }

        try {
            double myAttendance = ((double)sumOfLectures/((double)entries * 8)) * 100; // calculates user's attendance
            return myAttendance;
        }

        catch (Exception e){
            return 0;
        }
    }

    public int getEntriesCount() {
        String countQuery = "SELECT  * FROM " + TABLE_ATTENDANCE;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        // return count
        return cursor.getCount();
    }

}
