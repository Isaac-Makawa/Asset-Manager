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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import static mw.gov.agriculture.assestmanager.models.User.DB_PASSWORD;
import static mw.gov.agriculture.assestmanager.models.User.DB_URL;
import static mw.gov.agriculture.assestmanager.models.User.DB_USERNAME;
import static mw.gov.agriculture.assestmanager.models.User.JDBC_DRIVER;

/**
 *
 * @author USER
 */
public class SystemAdmin {
    
    int id;
    String fname,lname,role,password,state;
    Department department;
    Institution institution;
    public SystemAdmin(User user){
        super();
        this.setFname(user.getFname());
        this.setLname(user.getLname());
        this.setId(user.getId());
        this.setRole(user.getRole());
        this.setDepartment(user.getDepartment());
        this.setInstitution(user.getInstitution());
        this.setState(user.getState());
        this.setPassword(user.getPassword());
    }

    
    //custom methods
    public boolean addInstitution(Institution inst){
        Connection connection=null;
        PreparedStatement pstatement=null;
        boolean result=false;
        
        try{
            Class.forName(JDBC_DRIVER);
            connection=DriverManager.getConnection(DB_URL,DB_USERNAME,DB_PASSWORD);
            String sql="INSERT INTO institition (name,location,email,address,phone,description,created,updated_at) VALUES(?,?,?,?,?,?,now(),now())";
            pstatement=connection.prepareStatement(sql);
                        System.out.println(sql);
                        System.out.println(pstatement);
            pstatement.setString(1,inst.getName());            
            pstatement.setString(2,inst.getLocation());
            pstatement.setString(3,inst.getEmail());
            pstatement.setString(4,inst.getAddress());
            pstatement.setString(5,inst.getPhone());
            pstatement.setString(6,inst.getDescription());
            int rowAffected=pstatement.executeUpdate();
            pstatement.close();
            connection.close();
            result=rowAffected>0;
            
            
        }catch(SQLException e){
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, "SQL error",e);
        }catch(ClassNotFoundException e){
            Logger.getLogger(SystemAdmin.class.getName()).log(Level.SEVERE, null,e);
        }finally{
            if(pstatement !=null){
                try{
                    pstatement.close();
                }catch(SQLException e){
                    Logger.getLogger(User.class.getName()).log(Level.SEVERE, "SQL error",e);
                }
            }
            if(connection !=null){
                try{
                    connection.close();
                }catch(SQLException e){
                    Logger.getLogger(User.class.getName()).log(Level.SEVERE, "SQL error",e);
                }
            }
        }
        return result;
    }
    public ArrayList<Institution> getInstitutions(){
        Connection connection=null;
        ResultSet resultset;
        PreparedStatement pstatement=null;
        ArrayList<Institution> list=null;
        try{
            Class.forName(JDBC_DRIVER);
            connection=DriverManager.getConnection(DB_URL,DB_USERNAME,DB_PASSWORD);
            String sql="SELECT * FROM institition;";
            pstatement=connection.prepareStatement(sql);
            resultset=pstatement.executeQuery();
            
            list= new ArrayList<>();
            while(resultset.next()){
                Institution inst=new Institution();
                inst.setId(resultset.getInt(1));
                inst.setName(resultset.getString(2));
                inst.setEmail(resultset.getString(4));
                 inst.setPhone(resultset.getString(6));
                inst.setLocation(resultset.getString(3));
                inst.setAddress(resultset.getString(5));
                inst.setState(resultset.getString(10));                
                inst.setDescription(resultset.getString(9));
                 list.add(inst);
            }
            resultset.close();
            pstatement.close();
            connection.close();
            return list;
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
        return list;
    }
    public boolean updateInstitution(Institution inst){
        Connection connection=null;
        PreparedStatement pstatement=null;
        boolean result=false;
        try{
            Class.forName(JDBC_DRIVER);
            connection=DriverManager.getConnection(DB_URL,DB_USERNAME,DB_PASSWORD);
            String sql="UPDATE institition SET name=?,description=?, email=?,location=?, address=?, phone=?, updated_at=now(),state=? WHERE id=?";
            pstatement=connection.prepareStatement(sql);
            
            pstatement.setString(1, inst.getName());            
            pstatement.setString(2, inst.getDescription());
            pstatement.setString(3, inst.getEmail());
            pstatement.setString(4, inst.getLocation());
            pstatement.setString(5, inst.getAddress());
            pstatement.setString(6, inst.getPhone());
            pstatement.setString(7, inst.getState());
            pstatement.setInt(8, inst.getId());
            
            int rowAffected=pstatement.executeUpdate();
            
            pstatement.close();
            connection.close();
            result=rowAffected>0;

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
        return result;
    }
    public boolean deactivateInstitution(int id){
         Connection connection=null;
        PreparedStatement pstatement=null;
        boolean result=false;
        try{
            Class.forName(JDBC_DRIVER);
            connection=DriverManager.getConnection(DB_URL,DB_USERNAME,DB_PASSWORD);
            String sql="UPDATE institition SET state=? WHERE id=?";
            pstatement=connection.prepareStatement(sql);
            pstatement.setString(1,"inactive");
            pstatement.setInt(2,id);
            int affectedRows=pstatement.executeUpdate();
            pstatement.close();
            connection.close();
            result=affectedRows>0;
            
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
        return result;
    }
    public boolean addUser(User user){
        Connection connection=null;
        PreparedStatement pstatement=null;
        boolean result=false;
        try{
            Class.forName(JDBC_DRIVER);
            connection=DriverManager.getConnection(DB_URL,DB_USERNAME,DB_PASSWORD);
            String sql="INSERT into users(fname,sname,email,password,role,dept,inst_id) VALUES(?,?,?,?,?,?,?)";
            pstatement=connection.prepareStatement(sql);
            pstatement.setString(1,user.getFname());            
            pstatement.setString(2,user.getLname());
            pstatement.setString(3,user.getEmail());
            pstatement.setString(4,user.getPassword());
            pstatement.setString(5,user.getRole());

            pstatement.setInt(6,1);
            pstatement.setInt(7,user.getInstitution().getId());
            System.out.println(pstatement);
            int affectedRows=pstatement.executeUpdate();
            
            pstatement.close();
            connection.close();
            result=affectedRows>0;
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
        return result;
    }
    public ArrayList<User> getUsers(){
        Connection connection=null;
        PreparedStatement pstatement=null;
        ResultSet resultset;
        ArrayList<User> list=null;
        try{
            Class.forName(JDBC_DRIVER);
            connection=DriverManager.getConnection(DB_URL,DB_USERNAME,DB_PASSWORD);
            String sql="SELECT u.id,u.fname,u.sname,u.role,u.email,u.last_login,u.state,i.name FROM users u,institition i where u.inst_id=i.id;";
            pstatement=connection.prepareStatement(sql);
            resultset=pstatement.executeQuery();
            
            list= new ArrayList<>();
            while(resultset.next()){
                User user=new User();
                user.setId(resultset.getInt(1));                
                user.setFname(resultset.getString(2));
                user.setLname(resultset.getString(3));
                user.setEmail(resultset.getString(5));
                
                Institution inst=new Institution();
                inst.setName(resultset.getString(8));
                user.setInstitution(inst);
                user.setRole(resultset.getString(4));
                user.setState(resultset.getString(7));
                list.add(user);
            }
            resultset.close();
            pstatement.close();
            connection.close();
            return list;
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
        return list;
    }
    public boolean updateUser(User user){
         Connection connection=null;
        PreparedStatement pstatement=null;
        boolean result=false;
        try{
            Class.forName(JDBC_DRIVER);
            connection=DriverManager.getConnection(DB_URL,DB_USERNAME,DB_PASSWORD);
            String sql="UPDATE users SET fname=?,sname=?,email=?,password=?,role=? WHERE id=?";
            pstatement=connection.prepareStatement(sql);
            pstatement.setString(1, user.getFname());           
            pstatement.setString(2, user.getLname());
            pstatement.setString(3, user.getEmail());
            pstatement.setString(4, user.getState());
            pstatement.setString(5, user.getRole());
            pstatement.setInt(6, user.getId());
            
            int affectedRows=pstatement.executeUpdate();
            
            pstatement.close();
            connection.close();
            result=affectedRows>0;
            
        }catch(SQLException e){
            Logger.getLogger(User.class.getName()).log(Level.SEVERE,"SQL error",e);
        }catch(ClassNotFoundException ex){
            Logger.getLogger(SystemAdmin.class.getName()).log(Level.SEVERE,null,ex);
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
        return result;
    }
    public int getUsersCount(){
        Connection connection=null;
        PreparedStatement pstatement=null;
        ResultSet resultset;
        int count=-1;
        try{
            Class.forName(JDBC_DRIVER);
            connection=DriverManager.getConnection(DB_URL,DB_USERNAME,DB_PASSWORD);
            String sql="SELECT count(*) as users_count FROM users;";
            pstatement=connection.prepareStatement(sql);
            resultset=pstatement.executeQuery();
            
            if(resultset.next()){
                count=resultset.getInt(1);
            }
            resultset.close();
            pstatement.close();
            connection.close();
        }catch(SQLException e){
            Logger.getLogger(User.class.getName()).log(Level.SEVERE,"SQL error",e);
        }catch(ClassNotFoundException ex){
            Logger.getLogger(SystemAdmin.class.getName()).log(Level.SEVERE,null,ex);
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
        return count;
    }
    public int getInstitutionCount(){
        Connection connection=null;
        PreparedStatement pstatement=null;
        ResultSet resultset;
        int count=-1;
        try{
            Class.forName(JDBC_DRIVER);
            connection=DriverManager.getConnection(DB_URL,DB_USERNAME,DB_PASSWORD);
            String sql="SELECT count(*) as inst_count FROM institition;";
            pstatement=connection.prepareStatement(sql);
            resultset=pstatement.executeQuery();
            
            if(resultset.next()){
                count=resultset.getInt(1);
            }
            resultset.close();
            pstatement.close();
            connection.close();
        }catch(SQLException e){
            Logger.getLogger(User.class.getName()).log(Level.SEVERE,"SQL error",e);
        }catch(ClassNotFoundException ex){
            Logger.getLogger(SystemAdmin.class.getName()).log(Level.SEVERE,null,ex);
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
        return count;
    }
    public int reducedBalanceCount(){
        Connection connection=null;
        PreparedStatement pstatement=null;
        ResultSet resultset;
        int count=-1;
        try{
            Class.forName(JDBC_DRIVER);
            connection=DriverManager.getConnection(DB_URL,DB_USERNAME,DB_PASSWORD);
            String sql="SELECT count(*) as r_balance FROM asset WHERE depreciation_method=?;";
            pstatement=connection.prepareStatement(sql);
            pstatement.setString(1,"reduced balance");
            resultset=pstatement.executeQuery();
            
            if(resultset.next()){
                count=resultset.getInt(1);
            }
            resultset.close();
            pstatement.close();
            connection.close();
        }catch(SQLException e){
            Logger.getLogger(User.class.getName()).log(Level.SEVERE,"SQL error",e);
        }catch(ClassNotFoundException ex){
            Logger.getLogger(SystemAdmin.class.getName()).log(Level.SEVERE,null,ex);
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
        return count;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
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
