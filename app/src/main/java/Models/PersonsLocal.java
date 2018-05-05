package Models;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.annotation.NonNull;

import com.sma_rasanehsoft.phonebook2.App;

import java.util.ArrayList;

import StructPerson.StructPerson;

/**
 * Created by alavi on 12/10/2017.
 */

public class PersonsLocal {
    public static String favState;
    public static String tableName = "personslocal";

    public static ArrayList<StructPerson> all() {
        return all(false);
    }

    public static ArrayList<StructPerson> all(boolean onlyFriends) {

        ArrayList<StructPerson> result = new ArrayList<>();
        Cursor cursor;
        if (!onlyFriends) {
            cursor = App.DB_LOCAL.rawQuery("SELECT * FROM \"" + tableName + "\"" , null);
        } else {
            cursor = App.DB_LOCAL.rawQuery("SELECT * FROM \"" + tableName + "\" WHERE (\"featured\"=1)" , null);
        }
        while (cursor.moveToNext()) {
            result.add(extract(cursor));
        }
        cursor.close();
        return result;
    }



    public static boolean selectFavState(String id){
        Cursor cursor = App.DB_LOCAL.rawQuery("SELECT * FROM \"" + tableName + "\" WHERE (\"id\"=?)", new String[]{"" + id});
        while (cursor.moveToNext()){
            int takeFav=cursor.getInt(cursor.getColumnIndex("featured"));

            favState=String.valueOf(takeFav);
        }
        if(favState.equals("1")){
            return true;
        }else{
            return false;
        }
    }
    public static boolean setFeatured(String id){
        ContentValues cv = new ContentValues();
        cv.put("featured", 1 );
        return App.DB_LOCAL.update(tableName, cv,  "\"id\"=?", new String[]{"" + id}) > 0;
    }

    public static boolean setUnFeatured(String id){
        ContentValues cv = new ContentValues();
        cv.put("featured", 0 );

        return App.DB_LOCAL.update(tableName, cv,  "\"id\"=?", new String[]{"" + id}) > 0;
    }

    public static boolean save(StructPerson person) {
        if (!exists(person.id)) {
            return insert_db_local(person);
        }
        return delete(person.id) ;
    }

    public static boolean exists(int id) {
        return one(id) != null;
    }

    public static boolean delete(int id) {
        return App.DB_LOCAL.delete(tableName, "\"id\"=?", new String[]{"" + id}) > 0;
    }

    public static boolean insert(StructPerson person) {
        ContentValues values = getContentValues(person);
        return App.DB_LOCAL.insert(tableName, null, values) != -1;
    }
    public static boolean insert_db_local(StructPerson person) {
        ContentValues values = getContentValues(person);
        return App.DB_LOCAL.insert(tableName, null, values) != -1;

    }

    public static boolean update(StructPerson person) {
        ContentValues values = getContentValues(person);
        return App.DB_LOCAL.update(tableName, values, "\"id\"=?", new String[]{"" + person.id}) > 0;
    }
    public static void clear() {
        App.DB_LOCAL.delete(tableName,"1" , null);
    }

    public static StructPerson one(int id) {
        StructPerson result = null;
        Cursor cursor = App.DB_LOCAL.rawQuery("SELECT * FROM \"" + tableName + "\" WHERE (\"id\"=?)", new String[]{"" + id});
        if (cursor.moveToNext()) {
            result = extract(cursor);
        }
        cursor.close();
        return result;
    }

    @NonNull
    private static ContentValues getContentValues(StructPerson person) {
        ContentValues values = new ContentValues();
        if (person.id != 0) {
            values.put("id", person.id);
        }
        values.put("title", person.title);
        values.put("fulltext", person.fulltext);
        values.put("introtext", person.introtext);
        values.put("hits", person.hits);
        values.put("created", person.created);
        values.put("catid", person.catid);
        values.put("featured", person.featured ? 1 : 0);
        values.put("stored", person.stored ? 1 : 0);
        return values;
    }

    public static StructPerson extract(Cursor cursor) {
        StructPerson result = new StructPerson();
        result.id = cursor.getInt(cursor.getColumnIndex("id"));
        result.title = cursor.getString(cursor.getColumnIndex("title"));
        result.fulltext = cursor.getString(cursor.getColumnIndex("fulltext"));
        result.introtext = cursor.getString(cursor.getColumnIndex("introtext"));
        result.hits = cursor.getString(cursor.getColumnIndex("hits"));
        result.created = cursor.getString(cursor.getColumnIndex("created"));
        result.catid= cursor.getString(cursor.getColumnIndex("catid"));
        result.featured = cursor.getInt(cursor.getColumnIndex("featured")) == 1;
        result.stored = cursor.getInt(cursor.getColumnIndex("stored")) == 1;
        return result;
    }
}
