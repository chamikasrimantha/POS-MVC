/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package supermarket_mvc.controller;

import supermarket_mvc.model.CustomerModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.ResultSet;
import supermarket_mvc.db.DBConnection;
/**
 *
 * @author HP
 */
public class CustomerController {
    
    public String saveCustomer(CustomerModel customerModel) throws SQLException{
        Connection connection = DBConnection.getInstance().getConnection();
        
        String query = "INSERT INTO Customer VALUES(?,?,?,?,?,?,?,?,?)";
        
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, customerModel.getCustID());
        statement.setString(2, customerModel.getCustTitle());
        statement.setString(3, customerModel.getCustName());
        statement.setString(4, customerModel.getDOB());
        statement.setDouble(5, customerModel.getSalary());
        statement.setString(6, customerModel.getCustAddress());
        statement.setString(7, customerModel.getCity());
        statement.setString(8, customerModel.getProvince());
        statement.setString(9, customerModel.getPostalCode());
        
        if(statement.executeUpdate() > 0){
            return "Success";
        } else{
            return "Fail";
        }
        
    }
    
    public ArrayList<CustomerModel> getAllCustomer() throws SQLException{
        Connection connection = DBConnection.getInstance().getConnection();
        String query = "SELECT * FROM Customer";
        PreparedStatement statement = connection.prepareStatement(query);
        
        ArrayList<CustomerModel> customerModels = new ArrayList<>();
        
        ResultSet rst = statement.executeQuery();
        while (rst.next()) {            
            CustomerModel cm = new CustomerModel(rst.getString(1),
                    rst.getString(2), 
                    rst.getString(3), 
                    rst.getString(4), 
                    rst.getDouble(5),
                    rst.getString(6), 
                    rst.getString(7), 
                    rst.getString(8), 
                    rst.getString(9));
            
            customerModels.add(cm);
        }
       
        return customerModels;
    }
     public CustomerModel searchCustomer(String custId) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String query = "SELECT * FROM Customer WHERE CustID = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, custId);
        
        ResultSet rst = statement.executeQuery();
        while (rst.next()) {            
            return new CustomerModel(rst.getString(1),
                    rst.getString(2), 
                    rst.getString(3), 
                    rst.getString(4), 
                    rst.getDouble(5),
                    rst.getString(6), 
                    rst.getString(7), 
                    rst.getString(8), 
                    rst.getString(9));
        }
        
        return null;
    }

    public String updateCustomer(CustomerModel customerModel) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        
        String query = "UPDATE Customer SET CustTitle=?, CustName =?, DOB=?, salary=?, CustAddress=?,City=?, Province=?,PostalCode=? WHERE CustID=?";
        
        PreparedStatement statement = connection.prepareStatement(query);
        
        statement.setString(1, customerModel.getCustTitle());
        statement.setString(2, customerModel.getCustName());
        statement.setString(3, customerModel.getDOB());
        statement.setDouble(4, customerModel.getSalary());
        statement.setString(5, customerModel.getCustAddress());
        statement.setString(6, customerModel.getCity());
        statement.setString(7, customerModel.getProvince());
        statement.setString(8, customerModel.getPostalCode());
        statement.setString(9, customerModel.getCustID());
        
        if(statement.executeUpdate() > 0){
            return "Success";
        } else{
            return "Fail";
        }
    }

    public String deleteCustomer(String custId) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String query = "DELETE FROM Customer WHERE CustID = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, custId);
        
        if(statement.executeUpdate() > 0){
            return "Success";
        } else{
            return "Fail";
        }
        
    }
}