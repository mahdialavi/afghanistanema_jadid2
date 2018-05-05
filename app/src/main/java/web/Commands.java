package web;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import StructPerson.StructPerson;

/**
 * Created by alavi on 11/20/2017.
 */

public class Commands {
    public static int create(StructPerson person) {
        HashMap<String, String> params = new HashMap<>();
        params.put("action", "create");
        params.put("title", person.title);
        params.put("fulltext", person.fulltext);
        params.put("introtext", person.introtext);
        params.put("hits", person.hits);
        params.put("catid", person.catid);
        params.put("created", person.created);
     //  params.put("male", person.male ? "1" : "0");
        params.put("featured", person.featured ? "1" : "0");
        String json = WebService.post(params);
        if (json != null) {
            try {
                JSONObject obj = new JSONObject(json);
                if (obj.getInt("code") == 1) {
                    return obj.getInt("data");
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    public static ArrayList<StructPerson> read(int id) {
        ArrayList<StructPerson> result = new ArrayList<>();
        HashMap<String, String> params = new HashMap<>();
        params.put("action", "read");
        if (id != 0) {
            params.put("id", String.valueOf(id));
        }
        String json = WebService.post(params);
        if (json != null) {
            try {
                JSONObject obj = new JSONObject(json);
                if (obj.getInt("code") == 1) {
                    JSONArray data = obj.getJSONArray("data");
                    int len = data.length();
                    for (int i = 0; i < len; i++) {
                        JSONObject item = data.getJSONObject(i);
                        StructPerson person = new StructPerson();
                        person.id = item.getInt("id");
                        person.title = item.getString("title");
                        person.fulltext = item.getString("fulltext");
                        person.introtext = item.getString("introtext");
                        person.catid = item.getString("catid");
                        person.hits= item.getString("hits");
                        person.created= item.getString("created");
               //         person.male = item.getInt("male") == 1;
                        person.featured = item.getInt("featured") == 1;
                        person.stored = true;
                        result.add(person);
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static boolean update(StructPerson person) {
        HashMap<String, String> params = new HashMap<>();
        params.put("action", "update");
        params.put("id", String.valueOf(person.id));
        params.put("title", person.title);
        params.put("fulltext", person.fulltext);
        params.put("introtext", person.introtext);
        params.put("hits", person.hits);
        params.put("catid", person.catid);
        params.put("created", person.created);
   //     params.put("male", person.male ? "1" : "0");
        params.put("featured", person.featured ? "1" : "0");
        String json = WebService.post(params);
        if (json != null) {
            try {
                return new JSONObject(json).getInt("code") == 1;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static boolean delete(int id) {
        HashMap<String, String> params = new HashMap<>();
        params.put("action", "delete");
        params.put("id", String.valueOf(id));
        String json = WebService.post(params);
        if (json != null) {
            try {
                return new JSONObject(json).getInt("code") == 1;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
