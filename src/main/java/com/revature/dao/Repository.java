package com.revature.dao;

import com.revature.model.Hero;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.revature.dao.DBUtils.*;
public class Repository {
    public List<Hero> getAllHeros() {
        List<Hero> herosFromDB = new ArrayList<>();
        try
        {
            Connection connection = getConnection();
            System.out.println("getting all heros ...");
            String sql = "Select * from hero";
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet result = stmt.executeQuery();
            while(result.next()){
                herosFromDB.add(new Hero(
                        result.getString("heroName"),
                        result.getString("superPower"),
                        (UUID) result.getObject("heroid")
                ));
            }
            System.out.println("success!");
            closeConnection(connection);
            return herosFromDB;
        } catch (SQLException ex) {
            System.out.println("Something went wrong");
            System.out.println(ex.getMessage());
        }
        return null;
    }
    public Hero getHero(UUID id) {
        try {
            Connection connection = getConnection();
            System.out.println("Getting hero");
            String sql = "Select * from hero where hero.heroid = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setObject(1,id);
            ResultSet result = stmt.executeQuery();
            result.next();
            return new Hero(
                    result.getString("heroName"),
                    result.getString("superPower"),
                    (UUID) result.getObject("heroid")
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public int makeNewHero(Hero hero) {
        try {
            Connection connection = getConnection();
            String sql = "insert into hero values (?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setObject(1, UUID.randomUUID());
            stmt.setString(2, hero.getName());
            stmt.setString(3, hero.getSuperPower());

            int rowsAffected = stmt.executeUpdate();
            closeConnection(connection);
            return rowsAffected;
        } catch (SQLException ex) {
            System.out.println("Something went wrong");
            System.out.println(ex.getMessage());
        }
        return -1;
    }
}
