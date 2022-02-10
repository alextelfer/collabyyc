/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.sait.itsd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Alex Telfer, Brady Belsher
 */
public class DBOperations {
     public String getInventory() {
        String result = "";
        ConnectionPool pool = ConnectionPool.getInstance();
        try {
            Connection conn = pool.getConnection();
            String sql = "select * from collabyyc.items;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result = result + rs.getInt(1) + "," + rs.getInt(2) + "," + rs.getString(3) +
                    "," + rs.getDouble(4) + "," + rs.getInt(5) + "," + rs.getString(6) + ";";
            }
            rs.close();
            ps.close();
            pool.freeConnection(conn);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }
}
