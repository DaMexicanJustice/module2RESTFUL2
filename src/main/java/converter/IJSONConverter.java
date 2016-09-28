/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import entity.Person;
import java.util.List;

/**
 *
 * @author xboxm
 */
public interface IJSONConverter {
    
    public String PersonToJson(Person p);
    public Person JsonToPerson(String json);
    public String ListToJson(List list);
    
}
