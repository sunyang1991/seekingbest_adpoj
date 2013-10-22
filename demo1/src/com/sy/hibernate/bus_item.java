package com.sy.hibernate;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class bus_item {
private String Business;
private int id;
@Id
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
private String Item;
private int Bus_Item;
public String getBusiness() {
	return Business;
}
public void setBusiness(String business) {
	Business = business;
}
public String getItem() {
	return Item;
}
public void setItem(String item) {
	Item = item;
}
public int getBus_Item() {
	return Bus_Item;
}
public void setBus_Item(int bus_Item) {
	Bus_Item = bus_Item;
}
}
