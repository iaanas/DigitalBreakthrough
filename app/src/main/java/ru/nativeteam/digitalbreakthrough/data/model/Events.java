package ru.nativeteam.digitalbreakthrough.data.model;

public class Events {
	private String userIn;
	private String userOut;
	private String startDate;
	private String endDate;
	private String nameOfEvent;
	private String statusOfEvent;
	private int id;
	private int nomer;
	private String description;
	private String date;
	private String statusOfWork;
	private String hardwareId;
	private String place;
	
	public Events( String userIn , String userOut , String startDate , String endDate , String nameOfEvent , String statusOfEvent , int id , int nomer , String description , String date , String statusOfWork , String hardwareId , String place ) {
		this.userIn = userIn;
		this.userOut = userOut;
		this.startDate = startDate;
		this.endDate = endDate;
		this.nameOfEvent = nameOfEvent;
		this.statusOfEvent = statusOfEvent;
		this.id = id;
		this.nomer = nomer;
		this.description = description;
		this.date = date;
		this.statusOfWork = statusOfWork;
		this.hardwareId = hardwareId;
		this.place = place;
	}
	
	public String getUserIn( ) {
		return userIn;
	}
	
	public void setUserIn( String userIn ) {
		this.userIn = userIn;
	}
	
	public String getUserOut( ) {
		return userOut;
	}
	
	public void setUserOut( String userOut ) {
		this.userOut = userOut;
	}
	
	public String getStartDate( ) {
		return startDate;
	}
	
	public void setStartDate( String startDate ) {
		this.startDate = startDate;
	}
	
	public String getEndDate( ) {
		return endDate;
	}
	
	public void setEndDate( String endDate ) {
		this.endDate = endDate;
	}
	
	public String getNameOfEvent( ) {
		return nameOfEvent;
	}
	
	public void setNameOfEvent( String nameOfEvent ) {
		this.nameOfEvent = nameOfEvent;
	}
	
	public String getStatusOfEvent( ) {
		return statusOfEvent;
	}
	
	public void setStatusOfEvent( String statusOfEvent ) {
		this.statusOfEvent = statusOfEvent;
	}
	
	public int getId( ) {
		return id;
	}
	
	public void setId( int id ) {
		this.id = id;
	}
	
	public int getNomer( ) {
		return nomer;
	}
	
	public void setNomer( int nomer ) {
		this.nomer = nomer;
	}
	
	public String getDescription( ) {
		return description;
	}
	
	public void setDescription( String description ) {
		this.description = description;
	}
	
	public String getDate( ) {
		return date;
	}
	
	public void setDate( String date ) {
		this.date = date;
	}
	
	public String getStatusOfWork( ) {
		return statusOfWork;
	}
	
	public void setStatusOfWork( String statusOfWork ) {
		this.statusOfWork = statusOfWork;
	}
	
	public String getHardwareId( ) {
		return hardwareId;
	}
	
	public void setHardwareId( String hardwareId ) {
		this.hardwareId = hardwareId;
	}
	
	public String getPlace( ) {
		return place;
	}
	
	public void setPlace( String place ) {
		this.place = place;
	}
}
