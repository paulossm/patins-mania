package imd.ufrn.br.patinsmania.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "patinsdb";
    public static final int DB_VERSION = 1;
    private static final String SQL_DROP = "DROP TABLE IF EXISTS " + PatinsContract.TABLE_NAME;
    private static final String SQL_CREATE = String.format(
            "CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "%s TEXT NOT NULL, %s TEXT NOT NULL, %s INTEGER NOT NULL)",
            PatinsContract.TABLE_NAME,
            PatinsContract.Columns._ID,
            PatinsContract.Columns.BRAND,
            PatinsContract.Columns.MODEL,
            PatinsContract.Columns.SIZE
    );

    private static DBHelper instance;

    public static DBHelper getInstance(Context context) {
        if(instance == null) {
            instance = new DBHelper(context);
        }
        return instance;
    }

    private DBHelper(Context context) { super(context, DB_NAME, null, DB_VERSION); }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_DROP);
        db.execSQL(SQL_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }
}
