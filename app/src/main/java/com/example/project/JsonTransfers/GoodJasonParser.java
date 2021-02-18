package com.example.project.JsonTransfers;

import com.example.project.Modules.Good;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
public class GoodJasonParser {
    public static List<Good> getObjectFromJason(String jason) {
        List<Good> goods;
        try {
            JSONArray jsonArray = new JSONArray(jason);
            goods = new ArrayList<>();
            for (int i = 0; i <jsonArray.length(); i++) {
                JSONObject jsonObject ;
                jsonObject = (JSONObject) jsonArray.get(i);
                Good good = new Good();
                good.setTitle(jsonObject.getString("title"));
                good.setType(jsonObject.getString("type"));
                good.setDescription(jsonObject.getString("description"));
                good.setImageurl(jsonObject.getString("imageurl"));
                good.setHeight(jsonObject.getInt("height"));
                good.setWidth(jsonObject.getInt("width"));
                good.setPrice(jsonObject.getDouble("price"));
                good.setRating(jsonObject.getInt("rating"));
                goods.add(good);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return goods;
    }
}
