/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity.Person;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author xboxm
 */
public class FacadeTest {
    
    static IFacade facade;
    static int id;
    
    public FacadeTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        Person p = new Person();
        p.setFname("a");
        p.setLname("");
        p.setPnumber("123");
        facade = new Facade();
        facade.addEntityManagerFactory(Persistence.createEntityManagerFactory("persistenceunit"));
        Person returned = facade.addPerson(p);
        id = returned.getPersonid();
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of deletePerson method, of class Facade.
     */
    @Test
    public void testDeletePerson() {
        int beforeTestSize = facade.getPeople().size();
        facade.deletePerson(id);
        int afterTestSize = facade.getPeople().size();
        assertEquals(beforeTestSize, afterTestSize+1);
    }
    
}
