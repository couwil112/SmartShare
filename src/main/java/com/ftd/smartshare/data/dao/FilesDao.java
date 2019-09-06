package com.ftd.smartshare.data.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.ftd.smartshare.data.JDBCConnectionFactory;
import com.ftd.smartshare.data.entity.Files;

public class FilesDao {
	
    public boolean createFile(Files file) {
        try (Connection connection = JDBCConnectionFactory.getInstance().getConnection();
        		PreparedStatement stmt = connection.prepareStatement("INSERT INTO files" +
                    "(file_name, file, time_created, expiry_time, max_downloads, "
                    + "total_downloads, password)" + 
                    "VALUES (?, ?, ?, ?, ?, ?, ?)")) {
            stmt.setString(1, file.getFile_name());
            stmt.setBytes(2, file.getFile());
            stmt.setTimestamp(3, file.getTime_created());
            stmt.setTimestamp(4, file.getExpiry_time());
            stmt.setInt(5, file.getMax_downloads());
            stmt.setInt(6, file.getTotal_downloads());
            stmt.setString(7, file.getPassword());
            stmt.executeUpdate();            
            
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public byte[] getFile(String file_name, String password) {
    	byte[] byteArrResult = null;
        try (PreparedStatement stmt = 
        		JDBCConnectionFactory.getInstance().getConnection().prepareStatement(
        				"SELECT * FROM files WHERE file_name = '" + file_name + "'");
        	) {
            ResultSet resultSet = stmt.executeQuery();
            Files result = new Files(); 
            if (resultSet.next()) {
            	result.setPassword(resultSet.getString("password"));
            	result.setMax_downloads(resultSet.getInt("max_downloads"));
            	result.setTotal_downloads(resultSet.getInt("total_downloads"));
            	result.setExpiry_time(resultSet.getTimestamp("expiry_time"));
            	result.setFile(resultSet.getBytes("file"));
            
            	boolean hasPassword = password.equals(resultSet.getString("password"));            	

            	if (hasPassword) {
            		boolean max_downloadsReached = 
                			(result.getTotal_downloads() >= result.getMax_downloads());
                	boolean expired = 
                			(result.getExpiry_time().getTime() <= System.currentTimeMillis());
                	if (max_downloadsReached || expired) {    
                		delete(file_name);
                		System.out.println("File deleted");
                		return byteArrResult;
                	}
                	byteArrResult = result.getFile();
	                updateTotal_downloads(file_name, result.getTotal_downloads());
	                System.out.println("Success!");
                	
            	}
            } else {            	
            		System.out.println("ACCESS DENIED");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }        
        return byteArrResult;
    }
    
	private void updateTotal_downloads(String file_name, int total_downloads) 
			throws SQLException {
    	PreparedStatement stmt = 
    		JDBCConnectionFactory.getInstance().getConnection().prepareStatement(
    			"UPDATE files SET total_downloads = " + (total_downloads + 1) +
        		" WHERE file_name = '" + file_name + "'");
    	stmt.executeUpdate();               
    }
    
    private void delete(String file_name) throws SQLException {
    	PreparedStatement stmt = 
        	JDBCConnectionFactory.getInstance().getConnection().prepareStatement(
        		"DELETE FROM files WHERE file_name = '" + file_name + "'");
        stmt.executeUpdate();
	}
    
    public static void main(String[] args) {
        Files newFile = new Files();
        FilesDao fileDao = new FilesDao();
        
//        newFile.setFile_name("land.doc");
//        newFile.setFile(new byte[] {001, 0010, 0101});
//        newFile.setTime_created(new Timestamp(System.currentTimeMillis()));
//        newFile.setExpiry_time(new Timestamp(System.currentTimeMillis() + 600*1000));
//        newFile.setMax_downloads(4);
//        newFile.setTotal_downloads(0);
//        newFile.setPassword("54321");
//        fileDao.createFile(newFile);
        
        byte[] file = fileDao.getFile("notes.doc", "123465");
        System.out.println(file);
    }
}
