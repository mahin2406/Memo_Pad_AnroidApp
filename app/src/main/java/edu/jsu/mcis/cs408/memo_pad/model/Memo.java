package edu.jsu.mcis.cs408.memo_pad.model;

public class Memo {

    private Integer id;
    private String memo;

    public Memo() {
    }

    public Memo(String memo) {
        this.memo = memo;
    }

    public Memo(Integer id, String memo) {
        this.id = id;
        this.memo = memo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}