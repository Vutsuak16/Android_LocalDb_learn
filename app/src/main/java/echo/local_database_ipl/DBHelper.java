package echo.local_database_ipl;

import java.util.ArrayList;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

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
                        "( name text,franchise,price)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS contacts");
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
        Cursor res = db.rawQuery("select * from players", null);
        res.moveToFirst();
        return array_list;
    }
}