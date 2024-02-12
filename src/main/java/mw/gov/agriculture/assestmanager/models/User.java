/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mw.gov.agriculture.assestmanager.models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author USER
 */
public class User {
    int id;
    String fname;
    String lname;
    String email;
    String password;
    String role;
    Department department;
    Institution institution;
    String state;

    //connection string
    static final String JDBC_DRIVER="com.mysql.cj.jdbc.Driver";
    static final String DB_URL="jdbc:mysql://localhost/ppc";
    static final String DB_USERNAME="root";
    static final String DB_PASSWORD="";
    
    public User(){}
    
    public boolean login(){
        Connection connection =null;
        PreparedStatement pstatement=null;
        ResultSet resultset;
        boolean status=false;
        
        try{
            Class.forName(JDBC_DRIVER);
            connection=DriverManager.getConnection(DB_URL,DB_USERNAME,DB_PASSWORD);
            String sql="SELECT u.id, u.fname,u.sname,u.email,u.password,u.role,d.name AS dept_name, d.id AS dept_id FROM users u JOIN department d ON u.dept=d.id WHERE u.email=? AND u.state=?";
            pstatement=connection.prepareStatement(sql);
            pstatement.setString(1, this.getEmail());
            pstatement.setString(2,"active");
            resultset=pstatement.executeQuery();
            System.out.println(pstatement);
            if(resultset.next()){
                this.setId(resultset.getInt(1));
                this.setEmail(resultset.getString(4));
                this.setFname(resultset.getString(2));
                this.setLname(resultset.getString(3));
                this.setRole(resultset.getString(6));
                this.setDepartment(new Department(resultset.getInt(8),resultset.getString(7)));
                status=BCrypt.checkpw(this.getPassword(),resultset.getString(5));
            }
            resultset.close();
            pstatement.close();
            connection.close();
            System.out.println(resultset);
        }catch(SQLException e){
            Logger.getLogger(User.class.getName()).log(Level.SEVERE,"SQL error",e);
        }catch(ClassNotFoundException ex){
            Logger.getLogger(User.class.getName()).log(Level.SEVERE,null,ex);
        }finally{
            if(pstatement!=null){
                try{
                    pstatement.close();
                }catch(SQLException e){
                    Logger.getLogger(User.class.getName()).log(Level.SEVERE,null,e);
                }
            }
            if(connection!=null){
                try{
                    connection.close();
                }catch(SQLException e){
                    Logger.getLogger(User.class.getName()).log(Level.SEVERE,null,e);
                }
            }
        }
        
        return status;
    }
    

    //getter and setter
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
    
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Institution getInstitution() {
        return institution;
    }

    public void setInstitution(Institution institution) {
        this.institution = institution;
    }
    
    
}
