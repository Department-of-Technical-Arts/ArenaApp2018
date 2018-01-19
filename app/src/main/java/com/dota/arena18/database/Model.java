package com.dota.arena18.database;

import io.realm.RealmObject;

/**
 * Created by ashwik on 1/1/18.
 */

public class Model extends RealmObject {

    private int id;
    private  String db_eventname;
    private  String db_rules;
    private String db_prizemoney;
    private String api_id;
    private String db_venue;

    public String getApi_id() {
        return api_id;
    }

    public void setApi_id(String api_id) {
        this.api_id = api_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDb_eventname() {
        return db_eventname;
    }

    public void setDb_eventname(String db_eventname) {
        this.db_eventname = db_eventname;
    }

    public String getDb_rules() {
        return db_rules;
    }

    public void setDb_rules(String db_rules) {
        this.db_rules = db_rules;
    }

    public String getDb_prizemoney() {
        return db_prizemoney;
    }

    public void setDb_prizemoney(String db_prizemoney) {
        this.db_prizemoney = db_prizemoney;
    }

    public String getDb_venue() {
        return db_venue;
    }

    public void setDb_venue(String db_venue) {
        this.db_venue = db_venue;
    }
}
