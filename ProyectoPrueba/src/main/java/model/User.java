/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import javax.swing.JOptionPane;
import model.Conexion;

/**
 *
 * @author Laura
 */
public class User {

    private String username;
    private String first_name;
    private String last_name;
    private String password;
    private Blob avatar;

    public User() {
    }

    public User(String username, String first_name, String last_name, String password, Blob avatar) {
        this.username = username;
        this.first_name = first_name;
        this.last_name = last_name;
        this.password = password;
        this.avatar = avatar;
    }

    public String getUsername() {
        return username;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getPassword() {
        return password;
    }

    public Blob getAvatar() {
        return avatar;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAvatar(Blob avatar) {
        this.avatar = avatar;
    }

    public boolean registerUser(String r) {
        boolean exito = false;

        Conexion con = new Conexion();
        FileInputStream fi;
        File file = new File(r);

        try {

            fi = new FileInputStream(file);
           
            String sql = "insert into user(username,first_name,last_name,password,avatar) values (?,?,?,?,?)";
            PreparedStatement p = con.conectarMySQL().prepareStatement(sql);

            p.setString(1, username);
            p.setString(2, first_name);
            p.setString(3, last_name);
            p.setString(4, password);
            p.setBinaryStream(5, fi);
            exito = p.execute();
         JOptionPane.showMessageDialog(null, "El usuario ha sido creado con exito");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error en la inserción");
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Por favor, llene todos los campos");
        }
        return exito;
    }
    
    
        public boolean editUser(String nom){
        boolean exito = false;
        try {
            Conexion con = new Conexion();
            String sql = "UPDATE user SET username = ?,first_name = ?, last_name = ?, password = ?\n" +
            "WHERE username = '"+nom+"'";      
            PreparedStatement p;
            p = con.conectarMySQL().prepareStatement(sql);
            p.setString(1, username);
            p.setString(2, first_name);
            p.setString(3, last_name);
            p.setString(4, password);
            exito = p.execute();   
            JOptionPane.showMessageDialog(null, "El usuario ha sido editado con exito");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error en la modificacion");
        } 
        return exito;
    }

    public ArrayList<User> listUser() {
        ArrayList<User> users = new ArrayList<User>();
            Conexion con = new Conexion();
        try {
            
            String sql = "select * from user";
            PreparedStatement stm = con.conectarMySQL().prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setUsername(rs.getString("username"));
                user.setFirst_name(rs.getString("first_name"));
                user.setLast_name(rs.getString("last_name"));
                user.setPassword(rs.getString("password"));
                users.add(user);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error en la inserción");
        }
        return users;
    }

    public User search(String Username) {
         User user_search = new User();

        try {
            Conexion con = new Conexion();
            String sql = "select * from user where username='" + Username + "'";
            PreparedStatement stm = con.conectarMySQL().prepareStatement(sql);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                User user = new User();
                user.setUsername(rs.getString("username"));
                user.setFirst_name(rs.getString("first_name"));
                user.setLast_name(rs.getString("last_name"));
                user.setPassword(rs.getString("password"));
                user_search = user;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error en la busqueda");
        }
        return user_search;
    }

    public boolean delete(String Username) {
        boolean exito = false;
        try {
            Conexion con = new Conexion();

            String sql = "delete from user where username = '" + Username + "'";
            PreparedStatement stm = con.conectarMySQL().prepareStatement(sql);
            stm.executeUpdate();
            exito = stm.execute();
            JOptionPane.showMessageDialog(null, "El usuario ha sido eliminado con exito");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al eliminar");
        }
        return exito;
    }

}
