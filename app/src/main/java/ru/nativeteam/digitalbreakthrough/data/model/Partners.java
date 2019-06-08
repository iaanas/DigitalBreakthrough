package ru.nativeteam.digitalbreakthrough.data.model;

public class Partners {
	
	private String userOut;
	private String inn;
	private String ogrn;
	private String tel;
	private String address;
	
	public Partners( String userOut , String inn , String ogrn , String tel , String address ) {
		this.userOut = userOut;
		this.inn = inn;
		this.ogrn = ogrn;
		this.tel = tel;
		this.address = address;
	}
	
	public String getUserOut( ) {
		return userOut;
	}
	
	public void setUserOut( String userOut ) {
		this.userOut = userOut;
	}
	
	public String getInn( ) {
		return inn;
	}
	
	public void setInn( String inn ) {
		this.inn = inn;
	}
	
	public String getOgrn( ) {
		return ogrn;
	}
	
	public void setOgrn( String ogrn ) {
		this.ogrn = ogrn;
	}
	
	public String getTel( ) {
		return tel;
	}
	
	public void setTel( String tel ) {
		this.tel = tel;
	}
	
	public String getAddress( ) {
		return address;
	}
	
	public void setAddress( String address ) {
		this.address = address;
	}
}
