package edu.jsu.mcis.cs408.memo_pad.model.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import edu.jsu.mcis.cs408.memo_pad.model.Memo;

public class MemoDAO {

    private final DAOFactory daoFactory;

    public MemoDAO(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public void create(Memo m) {
        SQLiteDatabase db = daoFactory.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DAOFactory.KEY_MEMO, m.getMemo());

        db.insert(DAOFactory.TABLE_MEMOS, null, values);
        db.close();
    }

    public void delete(Integer id) {
        SQLiteDatabase db = daoFactory.getWritableDatabase();
        db.delete(DAOFactory.TABLE_MEMOS,
                DAOFactory.KEY_ID + "=?",
                new String[]{String.valueOf(id)});
        db.close();
    }

    public Memo find(int id) {
        SQLiteDatabase db = daoFactory.getReadableDatabase();

        Cursor cursor = db.query(
                DAOFactory.TABLE_MEMOS,
                new String[]{DAOFactory.KEY_ID, DAOFactory.KEY_MEMO},
                DAOFactory.KEY_ID + "=?",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null
        );

        Memo memo = null;

        if (cursor != null && cursor.moveToFirst()) {
            memo = new Memo(
                    cursor.getInt(0),
                    cursor.getString(1)
            );
        }

        if (cursor != null) {
            cursor.close();
        }

        db.close();
        return memo;
    }

    public List<Memo> list() {
        List<Memo> memoList = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + DAOFactory.TABLE_MEMOS
                + " ORDER BY " + DAOFactory.KEY_ID + " ASC";

        SQLiteDatabase db = daoFactory.getReadableDatabase();
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