package com.login.dao;

import java.sql.*;

public class RegisterDao {

    String url="jdbc:mysql://localhost:3306/jsp_lab";
    String UserName="root";
    String password="gala";

    String register_q="Insert into user_table (`Username`, `password`, `email`)  values(?,?,?)";
    public boolean register(String username,String pass,String email)
    {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn= DriverManager.getConnection(url, UserName, password);

            PreparedStatement st=conn.prepareStatement(register_q);
            st.setString(1,username);
            st.setString(2, pass);
            st.setString(3, email);
            int rs=st.executeUpdate();
            if(rs!=0)
            {
                //System.out.println("OKKKKKKKKKKKK");
                return true;
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();

        }
        return false;
    }
}
