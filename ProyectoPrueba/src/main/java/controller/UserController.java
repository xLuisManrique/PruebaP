/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import View.EditUser;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Objects;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.User;
import View.EditUser;
import View.RegisterUser;
import java.awt.Image;
import java.sql.Blob;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 *
 * @author camil
 */
public class UserController {

    User user;
    EditUser geditUsers;
    RegisterUser gregisterUser;

    public UserController() {
    }

    public UserController(RegisterUser gregisterUser) {
        this.gregisterUser = gregisterUser;
    }

    public UserController(EditUser gviewUsers) {
        this.geditUsers = gviewUsers;
    }

    public void registerUser() {
        user = new User();
        user.setUsername(gregisterUser.getUsername());
        user.setFirst_name(gregisterUser.getFirst_name());
        user.setLast_name(gregisterUser.getLast_name());
        user.setPassword(gregisterUser.getPassword());
        user.registerUser(gregisterUser.getRuta());

    }

    public void editUser(String user_edit) {
        user = new User();

        user.setUsername(geditUsers.getUsername());
        user.setFirst_name(geditUsers.getFirst_name());
        user.setLast_name(geditUsers.getLast_name());
        user.setPassword(geditUsers.getPassword());
        user.editUser(user_edit);

    }

    public void deleteUser(String username) {
        user = new User();
        ArrayList<User> users = user.listUser();
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUsername().equals(username)) {
                user.delete(username);
            }
        }
    }

    public void listUsers() {
        ArrayList<User> users = user.listUser();

        for (int i = 0; i < users.size(); i++) {

        }

    }

    public void searchUser(String username, JTable tablaUsername) {

        user = new User();

        User user_search = user.search(username);

        DefaultTableModel tabla = new DefaultTableModel();

        tabla.addColumn("Username");
        tabla.addColumn("First Name");
        tabla.addColumn("Last Name");

        if (!Objects.isNull(user_search)) {
            User Alluser = new User();

            Object fila[] = new Object[3];

            fila[0] = user_search.getUsername();
            fila[1] = user_search.getFirst_name();
            fila[2] = user_search.getLast_name();
            tabla.addRow(fila);

            tablaUsername.setModel(tabla);

        } else {

            DefaultTableModel tabla2 = (DefaultTableModel) tablaUsername.getModel();
            tabla2.setRowCount(0);
        }

    }

    public boolean getListUsers(JTable tablaUsername) {
        User user = new User();

        ArrayList<User> Users = user.listUser();

        DefaultTableModel tabla = new DefaultTableModel();

        tabla.addColumn("Username");
        tabla.addColumn("First Name");
        tabla.addColumn("Last Name");

        if (Users.size() > 0) {
            User Alluser = new User();
            for (int i = 0; i < Users.size(); i++) {
                Object fila[] = new Object[3];
                Alluser = Users.get(i);
                fila[0] = Alluser.getUsername();
                fila[1] = Alluser.getFirst_name();
                fila[2] = Alluser.getLast_name();
                tabla.addRow(fila);
            }

            tablaUsername.setModel(tabla);

        }
        if (Users.size() < 1) {
            DefaultTableModel tabla2 = (DefaultTableModel) tablaUsername.getModel();
            tabla2.setRowCount(0);
        }

        return true;
    }

    public void searchEditUser(String Username) {

        user = new User().search(Username);
        geditUsers.setUsername(user.getUsername());
        geditUsers.setFirst_name(user.getFirst_name());
        geditUsers.setLast_name(user.getLast_name());
        geditUsers.setPassword(user.getPassword());

    }

}
