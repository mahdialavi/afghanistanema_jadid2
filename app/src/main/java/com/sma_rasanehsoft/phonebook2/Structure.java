package com.sma_rasanehsoft.phonebook2;

/**
 * Created by alavi on 12/3/2017.
 */

public class Structure {

        public int id;
        public String title;
        public String fulltext;
        public String introtext;
        public boolean featured;
        public String hits;
        public String created;
        public String catid;
        //  public boolean male;
        public boolean stored;



    public Structure(int id, String title, String fulltext, String introtext, String hits, String created, String catid){
        this.id=id;
        this.title=title;
        this.fulltext=fulltext;
        this.introtext=introtext;
        this.hits=hits;
        this.created=created;
        this.catid=catid;

    }

}
