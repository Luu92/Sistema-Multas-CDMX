/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author DevLuu
 */
public class ConeccionDBTest {
   
    /**
     * Test of getConecction method, of class ConeccionDB.
     */
    @Test
    public void testGetConecction() throws Exception {
        System.out.println("> Entrando a testGetConnection() <");
        Connection expResult = null;
        Connection result = ConeccionDB.getConecction();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        if(result == null)
            fail("Error en la conección!!!");
    }

    /**
     * Test of close method, of class ConeccionDB.
     */
    @Test
    public void testClose_ResultSet() throws Exception {
        System.out.println("> Entrando a testClose_ResultSet() <");
        ResultSet rs = null;
        ConeccionDB.close(rs);
        // TODO review the generated test code and remove the default call to fail.
        if(rs == null)
            fail("Error al Cerrar la Base.");
    }

    /**
     * Test of close method, of class ConeccionDB.
     */
    @Test
    public void testClose_Statement() throws Exception {
        System.out.println("> Entrando a testClose_Statement() <");
        Statement smtm = null;
        ConeccionDB.close(smtm);
        // TODO review the generated test code and remove the default call to fail.
        if(smtm == null)
            fail("Error al Cerrar la Base.");
    }

    /**
     * Test of close method, of class ConeccionDB.
     */
    @Test
    public void testClose_PreparedStatement() throws Exception {
        System.out.println("> Entrando a testClose_PreparedStatement() <");
        PreparedStatement smtm = null;
        ConeccionDB.close(smtm);
        // TODO review the generated test code and remove the default call to fail.
        //if(smtm == null)
            fail("Error al Cerrar la Base.");
    }

    /**
     * Test of close method, of class ConeccionDB.
     */
    @Test
    public void testClose_Connection() throws Exception {
        System.out.println("> Entrando a testClose_Connection() <");
        Connection conn = null;
        ConeccionDB.close(conn);
        // TODO review the generated test code and remove the default call to fail.
        if(conn == null)
            fail("Error al Cerrar la Base.");
    }
    
}
