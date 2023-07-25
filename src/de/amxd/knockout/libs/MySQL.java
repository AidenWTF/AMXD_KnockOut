package de.amxd.knockout.libs;



import de.amxd.knockout.main.Main;

import java.sql.*;
import java.util.logging.Level;

public class MySQL {
    private String HOST;
    private String DATABASE;
    private String USER;
    private String PASSWORD;

    private Connection con;

    public MySQL(String HOST,String DATABASE,String USER, String PASSWORD){
        this.HOST = HOST;
        this.DATABASE = DATABASE;
        this.PASSWORD = PASSWORD;
        this.USER = USER;
    }
    public void connect(){
        try {


            con = DriverManager.getConnection("jdbc:mysql://"+HOST+":3306/"+DATABASE,USER,PASSWORD);

            Main.pl.getLogger().log(Level.INFO,"[KnockOut] Erfolgreich zur Datenbank verbunden");
        }
        catch (SQLException e){
            e.printStackTrace();

            Main.pl.getLogger().log(Level.WARNING,"[KnockOut] Fehler beim verbinden zur Datenbank");
        }
    }
    public void close(){
        try{
            if(con != null){
                con.close();
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }

    }
    public void update(String qry){
        try{
            Statement st = con.createStatement();
            st.executeUpdate(qry);
        }
        catch (SQLException e){
            connect();
            e.printStackTrace();
        }

    }
    public ResultSet query(String qry) {
        ResultSet rs = null;
        try {
            Statement st = con.createStatement();
            rs = st.executeQuery(qry);
        } catch (SQLException e) {
            connect();
            e.printStackTrace();
        }
        return rs;
    }

}
