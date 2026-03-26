package edu.jsu.mcis.cs408.memo_pad.controller;

import java.util.List;

import edu.jsu.mcis.cs408.memo_pad.model.Memo;
import edu.jsu.mcis.cs408.memo_pad.model.MemoPadModel;

public class MemoPadController extends AbstractController {

    private final MemoPadModel model;

    public MemoPadController(MemoPadModel model) {
        this.model = model;
    }

    public void createMemo(String text) {
        model.createMemo(text);
    }

    public void deleteMemo(Integer id) {
        model.deleteMemo(id);
    }

    public List<Memo> listMemos() {
        return model.listMemos();
    }
}