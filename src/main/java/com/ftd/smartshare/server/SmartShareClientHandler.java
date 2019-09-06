package com.ftd.smartshare.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.ftd.smartshare.dto.DownloadRequestDto;
import com.ftd.smartshare.dto.UploadRequestDto;
import com.ftd.smartshare.dto.ViewRequestDto;

public class SmartShareClientHandler implements Runnable {

    private Socket clientSocket;

    public SmartShareClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }
	@Override
	public void run() {
		try (PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
			 BufferedReader bufferedReader = new BufferedReader(
			 new InputStreamReader(clientSocket.getInputStream()));
			) {			
			JAXBContext context = JAXBContext.newInstance(
					UploadRequestDto.class, DownloadRequestDto.class, ViewRequestDto.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            Marshaller marshaller = context.createMarshaller();            
            
            
            String requestType = bufferedReader.readLine();
            switch (requestType) {
	            case "download" : downloadRequestHandler(unmarshaller, bufferedReader);
	            case "upload" : uploadRequestHandler(unmarshaller, bufferedReader);
	            case "view" : viewRequestHandler(unmarshaller, bufferedReader);
            }

		} catch (IOException | JAXBException e) {
            e.printStackTrace();
        }

	}

	private void downloadRequestHandler(Unmarshaller unmarshaller, BufferedReader bufferedReader) 
			throws JAXBException, IOException {
		DownloadRequestDto downloadRequest = 
            	(DownloadRequestDto) unmarshaller.unmarshal(new StringReader(bufferedReader.readLine()));
		System.out.println(downloadRequest);
		
	}
	
	private void uploadRequestHandler(Unmarshaller unmarshaller, BufferedReader bufferedReader)
			throws JAXBException, IOException {
		UploadRequestDto uploadRequest = 
            	(UploadRequestDto) unmarshaller.unmarshal(new StringReader(bufferedReader.readLine()));
		System.out.println(uploadRequest);
	}

	private void viewRequestHandler(Unmarshaller unmarshaller, BufferedReader bufferedReader)
			throws JAXBException, IOException {
		ViewRequestDto viewRequest = 
        		(ViewRequestDto) unmarshaller.unmarshal(new StringReader(bufferedReader.readLine()));
		System.out.println(viewRequest);
	}	
}
