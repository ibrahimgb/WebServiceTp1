package Databases;


import java.sql.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import sample.Service;

import javax.swing.*;
import java.sql.*;
import java.sql.Statement;

public class Databases {






//import java.sql.*;
//import  jdk.nashorn.internal.ir.Statement;



    private static Databases Handler;

    private static final String Db_url = "jdbc:derby:database;create=true";
    private static Connection conn = null;

    public static void setStatement(Statement statement) {
        Databases.statement = statement;
    }
/*
    public static Statement getStatement() {
        statement=conn.createStatement();

        return statement;
    }*/

    public static Statement statement = null;


    public Databases() throws SQLException, ClassNotFoundException {
        createConnection();
        //setupBookTable();
    }

    public static void createConnection() throws SQLException, ClassNotFoundException {
        String url = "jdbc:postgresql://localhost:5432/WebServices";
        String name = "ibrahimgb";
        String pass = "igamo2017";

        //Class.forName("com.mysql.cj.jdbc.Driver");
        Class.forName("org.postgresql.Driver");
        conn = DriverManager.getConnection(url, name, pass);
        statement = conn.createStatement();


    }

    public void closeConnection() throws SQLException {
        conn.close();
        statement.close();
    }

    public void insert(String requete) throws SQLException, ClassNotFoundException {
        createConnection();
        statement.executeUpdate(requete);
        closeConnection();


    }



    public ResultSet execQuery(String req) throws SQLException, ClassNotFoundException {
        ResultSet r;

        createConnection();
        statement=conn.createStatement();
        r = statement.executeQuery(req);




        return r;


    }


    public  boolean execAction(String action) throws ClassNotFoundException {
        try {
            createConnection();
            statement=conn.createStatement();
            statement.execute(action);
            return true;
        } catch (SQLException e) {
            //TODO hadi 3lach dertha
            JOptionPane.showMessageDialog(null,"Error:"+e.getMessage(),"Error Occured",JOptionPane.ERROR_MESSAGE);
            System.out.println("Exeception at execQuery : datahandler"+e.getLocalizedMessage());
            return false;
        }



    }





/*
        public boolean updateBook(Liste.Book book)
        {

            try {
                String r="UPDATE book SET title=?,author=?,publisher=? WHERE id=?";

                PreparedStatement stm=conn.prepareStatement(r);
                stm.setString(1,book.getTitle());
                stm.setString(2,book.getAuthor());
                stm.setString(3,book.getPublisher());
                stm.setString(4,book.getId());
                int s=stm.executeUpdate();
                return (s>0);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return false;
        }
        */



    public boolean upadate(Service membr)  {
        //String query="UPDATE Services SET nom = ?,emplacement = ? WHERE nService = ? ;";
        String query="UPDATE Services SET nom = '"+membr.getNom()+"',emplacement = '"+membr.getEmplacement()+"' WHERE nService = '"+membr.getNService()+"' ;";
        //SELECT * FROM Services WHERE nom LIKE '%"+serviceToSerch+"%' OR emplacement
        PreparedStatement stm= null;
        try {
            stm = conn.prepareStatement(query);
            System.out.println("printing info to update:");
           /* stm.setInt(1,membr.getNService());
            System.out.println(membr.getNService());
            stm.setString(2,membr.getNom());
            System.out.println(membr.getNom());
            stm.setString(3,membr.getEmplacement());
            System.out.println(membr.getEmplacement());
            System.out.println(query);*/
            int r=stm.executeUpdate();
            return (r>0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;





    }


}









