package com.example.housemanagementsystem.tool;

public class person {
    private Integer id;
    private String house;
    private String name;
    private String p_id;
    private String phone;
    private String money;
    private String start;
    private String end;
    private String time;
    private String content;

    public void setContent(String content) {
        this.content = content;
    }
    public void setId(Integer id){
        this.id=id;
    }

    public Integer getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public String getEnd() {
        return end;
    }

    public String getHouse() {
        return house;
    }

    public String getMoney() {
        return money;
    }

    public String getName() {
        return name;
    }

    public String getP_id() {
        return p_id;
    }

    public String getPhone() {
        return phone;
    }

    public String getStart() {
        return start;
    }

    public String getTime() {
        return time;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setP_id(String p_id) {
        this.p_id = p_id;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
