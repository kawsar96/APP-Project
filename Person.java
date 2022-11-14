import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.sql.*;

import org.json.simple.*;
import org.json.simple.parser.*;
import java.util.*;
import java.io.*;
public class Person {
    private String name;
    private int transferAmount;
    private int bankBalance;
    public Person() {

    }
    public Person(String name) {
        String sql = "SELECT balance "
                + "FROM person WHERE name = ?";
        int balance= 0;
        try (Connection conn = Connect.connect();
             PreparedStatement pstmt  = conn.prepareStatement(sql)){
             pstmt.setString(1,name);
             ResultSet rs  = pstmt.executeQuery();
             balance=rs.getInt("balance");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        this.name=name;
        this.bankBalance = balance;
    }
    public Person(String name, int transferAmount){
        String sql = "SELECT balance "
                + "FROM person WHERE name = ?";
        int balance= 0;
        try (Connection conn = Connect.connect();
             PreparedStatement pstmt  = conn.prepareStatement(sql)){
             pstmt.setString(1,name);
             ResultSet rs  = pstmt.executeQuery();
             balance=rs.getInt("balance");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        this.name=name;
        this.bankBalance=balance;
        this.transferAmount = transferAmount;
    }
    public String getName() {
        return this.name;
    }
    public int getTransferAmount() {
        return this.transferAmount;
    }
    public void setBankBalance(int newBalance) {
        this.bankBalance = newBalance;
        String sql = "UPDATE person SET balance = ?  "
                + "WHERE name = ?";

        try (Connection conn = Connect.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             pstmt.setInt(1, newBalance);
             pstmt.setString(2, name);
             pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public int getBankBalance() {
        return this.bankBalance;
    }
    public void details() {
        System.out.println("Name: " + this.name + " Balance: " + this.bankBalance);
    }
}