package com.iiht.emart.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="seller_product")
public class SellerProductEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID")
	private Integer id;
	private String category;
	private String subcategory;
	private String shopName;
	private String itemname;
	private Integer price;
	private Integer numOfStockItems;
	private String nwtype;
	private String memorystorage;
	private String screenresolution;
	private String weight;
	private String withlength;
	private String isActive;
	private Date createTime;
	private String createName;
	private Date updateTime;
	private String updateName;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name="CATEGORY")
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	@Column(name="SUBCATEGORY")
	public String getSubcategory() {
		return subcategory;
	}
	public void setSubcategory(String subcategory) {
		this.subcategory = subcategory;
	}
	@Column(name="SHOP_NAME")
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	@Column(name="ITEMNAME")
	public String getItemname() {
		return itemname;
	}
	public void setItemname(String itemname) {
		this.itemname = itemname;
	}
	@Column(name="PRICE")
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	@Column(name="NUM_OF_STOCK_ITEMS")
	public Integer getNumOfStockItems() {
		return numOfStockItems;
	}
	public void setNumOfStockItems(Integer numOfStockItems) {
		this.numOfStockItems = numOfStockItems;
	}
	@Column(name="NWTYPE")
	public String getNwtype() {
		return nwtype;
	}
	public void setNwtype(String nwtype) {
		this.nwtype = nwtype;
	}
	@Column(name="MEMORYSTORAGE")
	public String getMemorystorage() {
		return memorystorage;
	}
	public void setMemorystorage(String memorystorage) {
		this.memorystorage = memorystorage;
	}
	@Column(name="SCREENRESOLUTION")
	public String getScreenresolution() {
		return screenresolution;
	}
	public void setScreenresolution(String screenresolution) {
		this.screenresolution = screenresolution;
	}
	@Column(name="WEIGHT")
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	@Column(name="WITHLENGTH")
	public String getWithlength() {
		return withlength;
	}
	public void setWithlength(String withlength) {
		this.withlength = withlength;
	}
	@Column(name="IS_ACTIVE")
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	@Column(name="CREATE_TIME")
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@Column(name="CREATE_NAME")
	public String getCreateName() {
		return createName;
	}
	public void setCreateName(String createName) {
		this.createName = createName;
	}
	@Column(name="UPDATE_TIME")
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	@Column(name="UPDATE_NAME")
	public String getUpdateName() {
		return updateName;
	}
	public void setUpdateName(String updateName) {
		this.updateName = updateName;
	}
}
