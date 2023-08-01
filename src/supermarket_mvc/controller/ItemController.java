/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package supermarket_mvc.controller;

import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import supermarket_mvc.db.DBConnection;
import supermarket_mvc.model.ItemModel;
/**
 *
 * @author HP
 */
public class ItemController {
    public String saveItem(ItemModel item) throws SQLException{
        Connection connection = DBConnection.getInstance().getConnection();
        String query = "INSERT INTO Item VALUES(?,?,?,?,?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, item.getItemCode());
        statement.setString(2, item.getDescription());
        statement.setString(3, item.getPackSize());
        statement.setDouble(4, item.getUnitPrice());
        statement.setInt(5, item.getQoh());
        
        if(statement.executeUpdate() > 0){
            return "Success";
        } else{
            return "Fail";
        }
    }
    
    public ArrayList<ItemModel> getAllItem() throws SQLException{
        Connection connection = DBConnection.getInstance().getConnection();
        String query = "SELECT * FROM Item";
        PreparedStatement statement = connection.prepareStatement(query);
        
        ResultSet rst = statement.executeQuery();
        
        ArrayList<ItemModel> itemModels = new ArrayList<>();
        while (rst.next()) {            
            ItemModel item = new ItemModel(
                    rst.getString(1), 
                    rst.getString(2), 
                    rst.getString(3), 
                    rst.getDouble(4),
                    rst.getInt(5));
            itemModels.add(item);
        }
        return itemModels;
    }
    
    public ItemModel searchItem(String itemCode) throws SQLException{
        Connection connection = DBConnection.getInstance().getConnection();
        String query = "SELECT * FROM Item WHERE ItemCode =?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, itemCode);
        ResultSet rst = statement.executeQuery();
        while (rst.next()) {            
            ItemModel item = new ItemModel(
                    rst.getString(1), 
                    rst.getString(2), 
                    rst.getString(3), 
                    rst.getDouble(4),
                    rst.getInt(5));
            return item;
        }
        return null;
    }
    
    public String updateItem(ItemModel item) throws SQLException{
        Connection connection = DBConnection.getInstance().getConnection();
        String query = "UPDATE Item SET Description=?, PackSize=?, UnitPrice=?, QtyOnHand=? WHERE ItemCode=?";
        PreparedStatement statement = connection.prepareStatement(query);
        
        statement.setString(1, item.getDescription());
        statement.setString(2, item.getPackSize());
        statement.setDouble(3, item.getUnitPrice());
        statement.setInt(4, item.getQoh());
        statement.setString(5, item.getItemCode());
        if(statement.executeUpdate() > 0){
            return "Success";
        } else{
            return "Fail";
        }
    }
    
    public String deleteItem(String itemCode) throws SQLException{
        Connection connection = DBConnection.getInstance().getConnection();
        String query = "DELETE FROM Item WHERE ItemCode =?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, itemCode);
        if(statement.executeUpdate() > 0){
            return "Success";
        } else{
            return "Fail";
        }
    }
}
