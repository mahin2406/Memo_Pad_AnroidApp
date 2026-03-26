package edu.jsu.mcis.cs408.memo_pad.model;

import android.content.Context;

import java.util.List;

import edu.jsu.mcis.cs408.memo_pad.model.dao.DAOFactory;
import edu.jsu.mcis.cs408.memo_pad.model.dao.MemoDAO;

public class MemoPadModel extends AbstractModel {

    private final MemoDAO memoDAO;

    public MemoPadModel(Context context) {
        DAOFactory daoFactory = new DAOFactory(context, null, null, 1);
        memoDAO = daoFactory.getMemoDao();
    }

    public void createMemo(String text) {
        if (text != null) {
            String trimmed = text.trim();
            if (!trimmed.isEmpty()) {
                memoDAO.create(new Memo(trimmed));
            }
        }
    }

    public void deleteMemo(Integer id) {
        if (id != null) {
            memoDAO.delete(id);
        }
    }

    public List<Memo> listMemos() {
        return memoDAO.list();
    }
}