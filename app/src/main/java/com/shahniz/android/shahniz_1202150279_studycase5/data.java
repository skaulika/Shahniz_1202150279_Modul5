package com.shahniz.android.shahniz_1202150279_studycase5;

/**
 * Created by Shahniz on 25/03/2018.
 */

public class data {
    //mendeklarasikan variabel yang dibutuhkan
    String todo, desc, prior;
    //konstruktor
    public data(String todo, String desc, String prior) {
        this.todo = todo;
        this.desc = desc;
        this.prior = prior;
    }
    //method setter dan getter nya

    public String getTodo() {
        return todo;
    }

    public void setTodo(String todo) {
        this.todo = todo;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPrior() {
        return prior;
    }

    public void setPrior(String prior) {
        this.prior = prior;
    }
}
