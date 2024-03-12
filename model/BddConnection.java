/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Studio
 */
public class BddConnection {
    private String driver="com.mysql.jdbc.Driver";
    private String url="jdbc:mysql://localhost:3306/3ILM";
    private String user="root";
    private String pwd="";
    private Connection con;
    private Statement st;

    public BddConnection() {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException ex) {
            System.out.println("Problème Driver");
        }
        try {
            con=DriverManager.getConnection(url, user, pwd);
            st=con.createStatement();
        } catch (SQLException e) {
            System.out.println("Problème Connection");
        }

    }
    public int executerAction(String s){
       int nbre=0;
        try {
            nbre= st.executeUpdate(s);
        } catch (SQLException e) {
            System.out.println("Problème requette action");
        }

       return nbre;
    }
    public ResultSet executerSelect(String s){
        ResultSet rs=null;
        try {
            rs=st.executeQuery(s);
        } catch (SQLException e) {
            System.out.println("Problème requete select");
        }
        return rs;
    }

}
