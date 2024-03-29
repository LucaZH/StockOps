package com.ops.stock_ops.client.daos;

import com.ops.stock_ops.Dao;
import com.ops.stock_ops.client.entities.User_permission;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class User_permissionDAO extends Dao<User_permission> {
    public User_permissionDAO(Connection connection) {
        super(connection);
    }

    @Override
    public boolean create(User_permission obj) {
        String sql = "insert into permission_utilisateur (id_utilisateur, id_permission) values (?, ?)";
        try {
            PreparedStatement prepare = this.connection.prepareStatement(sql);
            prepare.setInt(1, obj.getId_user());
            prepare.setInt(2, obj.getId_permission());
            prepare.executeUpdate();
            prepare.close();
            return true;
        } catch (SQLException exception) {
            exception.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(int id_user) {
        String sql = "delete from permission_utilisateur where id_utilisateur=?";
        try {
            PreparedStatement prepare = this.connection.prepareStatement(sql);
            prepare.setInt(1, id_user);
            prepare.executeUpdate();
            prepare.close();
            return true;
        } catch (SQLException exception) {
            exception.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(User_permission obj) {
        String sql = "update permission_utilisateur set id_utilisateur=?, id_permission=? where id_permission=?";
        try {
            PreparedStatement prepare = this.connection.prepareStatement(sql);
            prepare.setInt(1, obj.getId_user());
            prepare.setInt(2, obj.getId_permission());
            prepare.executeUpdate();
            prepare.close();
            return true;
        } catch (SQLException exception) {
            exception.printStackTrace();
            return false;
        }
    }

    @Override
    public User_permission get(int id_user) {
        String sql = "select * from permission_utilisateur where id_utilisateur=?";
        try {
            PreparedStatement prepare = this.connection.prepareStatement(sql);
            prepare.setInt(1, id_user);
            ResultSet result = prepare.executeQuery();
            if (result.next()) {
                User_permission userPermission = new User_permission(
                        id_user,
                        result.getInt("id_permission"));
                prepare.close();
                return userPermission;
            } else {
                return null;
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
            return null;
        }
    }

    @Override
    public List<User_permission> get_all() {
        String sql = "select * from permission_utilisateur";
        List<User_permission> list_user_permission = new ArrayList<User_permission>();
        try {
            PreparedStatement prepare = this.connection.prepareStatement(sql);
            ResultSet result = prepare.executeQuery();
            while (result.next()) {
                list_user_permission.add(new User_permission(
                        result.getInt("id_user"),
                        result.getInt("id_permission")
                ));
            }
            prepare.close();
            return list_user_permission;
        } catch (SQLException exception) {
            exception.printStackTrace();
            return null;
        }
    }
}
