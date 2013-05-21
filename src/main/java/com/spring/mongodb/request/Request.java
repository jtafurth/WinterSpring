package com.spring.mongodb.request;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Request {
	
	@Id
	private String documentID;
	
	private String requestType;
	
	private String Header;
	
	private String Body;
	
	public Request(String documentID, String requestType, String Header, String Body){
		this.documentID = documentID;
		this.requestType = requestType;
		this.Header = Header;
		this.Body = Body;
	}
	
	public void setHeader(String header){
		this.Header = header;
	}
	public void setRequestType(String requestType){
		this.requestType = requestType;
	}
	
	@Override
	public String toString(){
		return "RequestType " + requestType + " DocumentID: " + this.documentID + " Header: " + this.Header +  " Body: " + Body;
	}
	
}
