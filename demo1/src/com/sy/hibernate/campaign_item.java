package com.sy.hibernate;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class campaign_item {
private int id;
private String Campaign;
private String Item;
private int Campaign_Item;
@Id
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getCampaign() {
	return Campaign;
}
public void setCampaign(String campaign) {
	Campaign = campaign;
}
public String getItem() {
	return Item;
}
public void setItem(String item) {
	Item = item;
}
public int getCampaign_Item() {
	return Campaign_Item;
}
public void setCampaign_Item(int campaign_Item) {
	Campaign_Item = campaign_Item;
}
}
