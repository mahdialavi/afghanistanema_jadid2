package StructPerson;


public class StructPerson {
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

//    public StructPerson(int id,String title,String fulltext,String introtext, String hits,String created,String catid){
//        this.id=id;
//        this.title=title;
//        this.fulltext=fulltext;
//        this.introtext=introtext;
//        this.hits=hits;
//        this.created=created;
//        this.catid=catid;
//
//    }



    public void load(StructPerson other) {
        id = other.id;
        title = other.title;
        fulltext = other.fulltext;
        introtext = other.introtext;
        featured = other.featured;
        hits = other.hits;
        created = other.created;
        catid = other.catid;
     //   male = other.male;
        stored = other.stored;
    }
}
