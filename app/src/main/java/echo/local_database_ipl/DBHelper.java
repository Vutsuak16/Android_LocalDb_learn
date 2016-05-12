package echo.local_database_ipl;

import java.util.ArrayList;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "IPL.db";
    public static final String PLAYER_INFO = "players";
    public static final String PLAYER_NAME = "name";
    public static final String PLAYER_FRANCHISE = "franchise";
    public static final String PLAYER_PRICE = "price";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "create table players " +
                        "(name,franchise,price)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS players");
        onCreate(db);
    }

    public boolean insertPlayer(String name, String franchise, String price) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("franchise", franchise);
        contentValues.put("price", price);
        db.insert("players", null, contentValues);
        return true;
    }


    public ArrayList<String> getAllplayers() {
        ArrayList<String> array_list = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from players", null);
        if (cursor.moveToFirst()) {
            do {
                String s=cursor.getString(0)+" "+cursor.getString(1)+" "+cursor.getString(2);
                array_list.add(s);
            } while (cursor.moveToNext());
        }

        // return contact list
        cursor.close();
        db.close();
        return array_list;
    }
    public void removeAll()
    {

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("players", null, null);

    }
    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, "players");
        return numRows;
    }

}
