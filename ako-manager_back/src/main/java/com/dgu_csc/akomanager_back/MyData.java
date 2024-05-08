package com.dgu_csc.akomanager_back;

public class MyData {

    private Long id;
    private String value1;
    private String value2;

    public MyData(long id, String column1, String column2) {
    }

    public String getValue1(){
        return this.value1;
    }
    public String getValue2(){
        return this.value2;
    }
}