/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import com.google.gson.Gson;
import entity.Person;
import java.util.List;

/**
 *
 * @author xboxm
 */
public class JSONConverter implements IJSONConverter {
    
    private Gson gson = new com.google.gson.GsonBuilder().setPrettyPrinting().create();


    @Override
    public String PersonToJson(Person p) {
        return gson.toJson(p);
    }

    @Override
    public Person JsonToPerson(String json) {
        return gson.fromJson(json, Person.class);
    }

    @Override
    public String ListToJson(List list) {
        return gson.toJson(list);
    }
    
}
