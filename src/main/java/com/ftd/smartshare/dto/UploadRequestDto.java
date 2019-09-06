package com.ftd.smartshare.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class UploadRequestDto {

	@XmlElement
	private String file_name;
	
	@XmlElement
	private byte[] file;
	
	@XmlElement
	private long expiration_offset;
	
	@XmlElement
	private int max_downloads;
	
	@XmlElement
	private String password;
	
	public UploadRequestDto() {		
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

	public long getExpiration_offset() {
		return expiration_offset;
	}

	public void setExpiration_offset(long expiration_offset) {
		this.expiration_offset = expiration_offset;
	}

	public int getMax_downloads() {
		return max_downloads;
	}

	public void setMax_downloads(int max_downloads) {
		this.max_downloads = max_downloads;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "UploadRequestDto [file_name=" + file_name + 
			   ", expiration_offset=" + expiration_offset + 
			   ", max_downloads=" + max_downloads + "]";
	}
}
