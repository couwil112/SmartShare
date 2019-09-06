package com.ftd.smartshare.client;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.Socket;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.ftd.smartshare.client.commands.SmartShare;
import com.ftd.smartshare.dto.DownloadRequestDto;
import com.ftd.smartshare.dto.UploadRequestDto;
import com.ftd.smartshare.dto.ViewRequestDto;

import picocli.CommandLine;

class Main {

    public static void main(String[] args) {
    	try (
                Socket socket = new Socket("localhost", 3000);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))
    	    ) {
            System.out.println("Connected to the server");
            JAXBContext context = JAXBContext.newInstance(UploadRequestDto.class);
            Marshaller marshaller = context.createMarshaller();
            Unmarshaller unmarshaller = context.createUnmarshaller();
            UploadRequestDto uploadRequest = (UploadRequestDto) unmarshaller.unmarshal(
            		new File("xmlrequests\\UploadRequest.xml"));

            //  Marshal request to stringWriter
            StringWriter stringWriter = new StringWriter();
            marshaller.marshal(uploadRequest, stringWriter);
            String requestType = "upload";
            
            // Write 'stringified' XML to socket
            out.println(requestType);
            out.println(stringWriter.toString());
    	}  catch (JAXBException | IOException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
//        CommandLine.run(new SmartShare()); // Pass cli arguments here
//        CommandLine.run(new SmartShare(), "-h");
//        CommandLine.run(new SmartShare(), "--version");
//        CommandLine.run(new SmartShare(), "upload");
//        CommandLine.run(new SmartShare(), "upload", "pom.xml");
//        CommandLine.run(new SmartShare(), "upload", ".gitignore");
//        CommandLine.run(new SmartShare(), "upload", "pom.xml", "password");
//        CommandLine.run(new SmartShare(), "download", "pom.xml", "password");
//        CommandLine.run(new SmartShare(), "download", "pom.xml", "wrongpassword");
//        CommandLine.run(new SmartShare(), "download", "test.txt", "password");
//        CommandLine.run(new SmartShare(), "download", "pom.xml", "password");
//        CommandLine.run(new SmartShare(), "view", "pom.xml", "password");
//        CommandLine.run(new SmartShare(), "upload", "pom.xml", "password", "-m", "1");
//        CommandLine.run(new SmartShare(), "download", "pom.xml", "password");
//        CommandLine.run(new SmartShare(), "download", "pom.xml", "password");


        
    }

}

