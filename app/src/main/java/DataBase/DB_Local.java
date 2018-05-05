package DataBase;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.sma_rasanehsoft.phonebook2.App;

import java.util.ArrayList;

/**
 * Created by alavi on 12/10/2017.
 */

public class DB_Local extends SQLiteOpenHelper {
    private static final String DB_NAME = "pblocal.sqlite";
    private static final ArrayList<String> TABLE_SCHEMA = new ArrayList<>();
    private static int DB_VERSION = 1;


    public DB_Local() {
        super(App.CONTEXT, DB_NAME, null, DB_VERSION);
        TABLE_SCHEMA.add("CREATE  TABLE  IF NOT EXISTS \"personslocal\" (" +
                "\"id\" INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL , " +
                "\"title\" TEXT NOT NULL  UNIQUE , " +
                "\"fulltext\" TEXT, " +
                "\"introtext\" TEXT, " +
                "\"hits\" TEXT, " +
                "\"created\" TEXT, " +
                "\"catid\" TEXT, " +
                "\"featured\" BOOL, " +
                "\"stored\" INTEGER" +
                ")");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        create(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        drop(db);
        create(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        drop(db);
        create(db);
    }
    private void create(SQLiteDatabase db) {
        db.beginTransaction();
        for (String createTable : TABLE_SCHEMA) {
            db.execSQL(createTable);
        }
        db.setTransactionSuccessful();
        db.endTransaction();
    }
    private void drop(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS \"personslocal\"");
    }
}
