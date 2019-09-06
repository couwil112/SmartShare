package com.ftd.smartshare.data.entity;

import java.sql.Timestamp;

public class Files {
	private String file_name;
	private Integer id;
	private byte[] file;
	private Timestamp time_created;
	private Timestamp expiry_time;
	private Integer max_downloads;
	private Integer total_downloads;
	private String password;
	
	public Files() {		
	}

	

	public String getFile_name() {
		return file_name;
	}



	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}



	public byte[] getFile() {
		return file;
	}



	public void setFile(byte[] file) {
		this.file = file;
	}



	public Timestamp getTime_created() {
		return time_created;
	}



	public void setTime_created(Timestamp time_created) {
		this.time_created = time_created;
	}



	public Timestamp getExpiry_time() {
		return expiry_time;
	}



	public void setExpiry_time(Timestamp expiry_time) {
		this.expiry_time = expiry_time;
	}



	public Integer getMax_downloads() {
		return max_downloads;
	}



	public void setMax_downloads(Integer max_downloads) {
		this.max_downloads = max_downloads;
	}



	public Integer getTotal_downloads() {
		return total_downloads;
	}



	public void setTotal_downloads(Integer total_downloads) {
		this.total_downloads = total_downloads;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public Integer getId() {
		return id;
	}



	@Override
	public String toString() {
		return file_name + " Summary: " +
			   "/nTime Created: " + time_created +
			   "/nExpires: " + expiry_time + 
			   "/nDownloads Remaining: " + (max_downloads - total_downloads);
	}
		
}
