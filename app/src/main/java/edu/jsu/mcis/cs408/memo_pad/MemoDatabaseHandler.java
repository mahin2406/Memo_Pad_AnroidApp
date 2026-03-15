package edu.jsu.mcis.cs408.memo_pad;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class MemoDatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "memoDatabase";
    private static final String TABLE_MEMOS = "memos";
    private static final String KEY_ID = "_id";
    private static final String KEY_MEMO = "memo";

    public MemoDatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_MEMOS_TABLE = "CREATE TABLE " + TABLE_MEMOS + " ("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KEY_MEMO + " TEXT NOT NULL)";
        db.execSQL(CREATE_MEMOS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MEMOS);
        onCreate(db);
    }

    public void addMemo(Memo memo) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_MEMO, memo.getMemo());

        db.insert(TABLE_MEMOS, null, values);
        db.close();
    }

    public ArrayList<Memo> getAllMemosAsList() {
        ArrayList<Memo> memoList = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + TABLE_MEMOS;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Memo memo = new Memo();
                memo.setId(cursor.getInt(0));
                memo.setMemo(cursor.getString(1));
                memoList.add(memo);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return memoList;
    }
}