package database;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

class Database extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "Doz.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "dozenten";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_VORNAME = "dozent_vorname";
    private static final String COLUMN_NACHNAME = "dozent_nachname";
    private static final String COLUMN_GEBURTSDATUM = "dozent_geburtsdatum";
    private static final String COLUMN_STRASSE = "dozent_strasse";
    private static final String COLUMN_HAUSNUMMER = "dozent_hausnummer";
    private static final String COLUMN_PLZ = "dozent_plz";
    private static final String COLUMN_STADT = "dozent_stadt";
    private static final String COLUMN_EMAIL = "dozent_email";

    public Database(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query =
                "CREATE TABLE " + TABLE_NAME +
                        " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_VORNAME + " TEXT, " +
                        COLUMN_NACHNAME + " TEXT, " +
                        COLUMN_GEBURTSDATUM + " DATE, " +
                        COLUMN_STRASSE + " TEXT, " +
                        COLUMN_HAUSNUMMER + " INTEGER, " +
                        COLUMN_PLZ + " TEXT, " +
                        COLUMN_STADT + " TEXT, " +
                        COLUMN_EMAIL + " TEXT);";

        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
