package edu.jsu.mcis.cs408.memo_pad;

public class Memo {

    private int id;
    private String memo;

    public Memo() {
    }

    public Memo(int id, String memo) {
        this.id = id;
        this.memo = memo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

}