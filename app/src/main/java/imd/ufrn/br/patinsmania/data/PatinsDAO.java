package imd.ufrn.br.patinsmania.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class PatinsDAO {
    private static PatinsDAO instance;

    private SQLiteDatabase db;

    public static PatinsDAO getInstance(Context context) {
        if (instance == null) {
            instance = new PatinsDAO(context.getApplicationContext());
        }
        return instance;
    }

    private PatinsDAO(Context context) {
        DBHelper dbHelper = DBHelper.getInstance(context);
        db = dbHelper.getWritableDatabase();
    }

    public List<Patins> list() {
        String[] columns = {
                PatinsContract.Columns._ID,
                PatinsContract.Columns.BRAND,
                PatinsContract.Columns.MODEL,
                PatinsContract.Columns.SIZE
        };
        List<Patins> patins = new ArrayList<>();

        try(Cursor c = db.query(PatinsContract.TABLE_NAME, columns, null, null, null, null, PatinsContract.Columns.SIZE)) {
            if(c.moveToFirst()) {
                do {
                    Patins p = PatinsDAO.fromCursor(c);
                    patins.add(p);
                } while (c.moveToNext());
            }
        }

        return patins;
    }

    private static Patins fromCursor(Cursor c) {
        int id = c.getInt(c.getColumnIndex(PatinsContract.Columns._ID));
        String name = c.getString(c.getColumnIndex(PatinsContract.Columns.BRAND));
        String model = c.getString(c.getColumnIndex(PatinsContract.Columns.MODEL));
        int size = c.getInt(c.getColumnIndex(PatinsContract.Columns.SIZE));
        return new Patins(id, name, model, size);
    }

    public void save(Patins patins) {
        ContentValues values = new ContentValues();
        values.put(PatinsContract.Columns.BRAND, patins.getBrand());
        values.put(PatinsContract.Columns.MODEL, patins.getModel());
        values.put(PatinsContract.Columns.SIZE, patins.getSize());
        long id = db.insert(PatinsContract.TABLE_NAME, null, values);
        patins.setId((int) id);
    }

    public void update(Patins patins) {
        ContentValues values = new ContentValues();
        values.put(PatinsContract.Columns.BRAND, patins.getBrand());
        values.put(PatinsContract.Columns.MODEL, patins.getModel());
        values.put(PatinsContract.Columns.SIZE, patins.getSize());
        db.update(
                PatinsContract.TABLE_NAME,
                values,
                PatinsContract.Columns._ID + "=?",
                new String[]{String.valueOf(patins.getId())}
        );
    }

    public void delete(Patins patins) {
        db.delete(
                PatinsContract.TABLE_NAME,
                PatinsContract.Columns._ID + "=?",
                new String[]{String.valueOf(patins.getId())}
        );
    }
}
