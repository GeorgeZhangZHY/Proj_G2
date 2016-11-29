package group2.grade15.njuse.vo;

import group2.grade15.njuse.bl.hotelmanagerbl.HotelManager;
import group2.grade15.njuse.po.HotelManagerPO;

import java.io.Serializable;

public class HotelManagerVO implements Serializable{
	private int id;
	private String password;
	private String name;
	private String contact;
	private int hotelID;
	
	public HotelManagerVO(HotelManagerPO po){
		id = po.getId();
		password = po.getPassword();
		name = po.getName();
		contact = po.getContact();
		hotelID = po.getHotelID();
	}
	
	public int getId() {
		return id;
	}

	public String getPassword() {
		return password;
	}

	public String getName() {
		return name;
	}

	public String getContact() {
		return contact;
	}

	public int getHotelID() {
		return hotelID;
	}
}
