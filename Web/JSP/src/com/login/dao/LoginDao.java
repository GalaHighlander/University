package com.login.dao;
import java.sql.*;
public class LoginDao {

    String url="jdbc:mysql://localhost:3306/jsp_lab";
    String UserName="root";
    String password="gala";
    String query="Select * from user_table where Username=? and password=?";
    String query_register="Select * from user_table where Username=?";
    String register_q="Insert into user_table (`Username`, `password`, `email`)  values(?,?,?)";

    public  boolean check(String username,String pass) throws InstantiationException, IllegalAccessException, ClassNotFoundException
    {


        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn= DriverManager.getConnection(url, UserName, password);

            PreparedStatement st=conn.prepareStatement(query);
            st.setString(1,username);
            st.setString(2, pass);

            ResultSet rs=st.executeQuery();

            if(rs.next())
            {
                //System.out.println("OKKKKKKKKKKKK");
                return true;
            }

        }
        catch ( SQLException e) {
            e.printStackTrace();
            return false;
        }
        return false;

    }

    public boolean register(String username,String pass,String email)
    {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn= DriverManager.getConnection(url, UserName, password);

            PreparedStatement st=conn.prepareStatement(register_q);
            st.setString(1,username);
            st.setString(2, pass);
            st.setString(3, email);
            ResultSet rs=st.executeQuery();
            if(rs.next())
            {
                //System.out.println("OKKKKKKKKKKKK");
                return true;
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();

        }
        return false;
    }
    public boolean valid_password(String pass,String cpass){
        if(pass.equals(cpass)){
            return true;
        }
        return false;
    }

    public  boolean check_register(String username,String pass) throws InstantiationException, IllegalAccessException, ClassNotFoundException
    {


        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn= DriverManager.getConnection(url, UserName, password);

            PreparedStatement st=conn.prepareStatement(query_register);
            st.setString(1,username);
            ResultSet rs=st.executeQuery();

            if(rs.next())
            {
                return true;
            }

        }
        catch ( SQLException e) {
            e.printStackTrace();
            return false;
        }
        return false;

    }

}
