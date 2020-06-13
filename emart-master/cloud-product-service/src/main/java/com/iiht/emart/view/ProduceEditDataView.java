package com.iiht.emart.view;

import java.util.List;

public class ProduceEditDataView {

	private String category;
	private List<ItemList> listCategory;
	private String subcategory;
	private List<ItemList> listSubcategory;
	private String itemname;
	private Integer price;
	private Integer numOfStockItems;
	private String nwtype;
	private List<ItemList> nwTypeList;
	private String memorystorage;
	private List<ItemList> memoryList;
	private String screenresolution;
	private String weight;
	private String withlength;

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public List<ItemList> getListCategory() {
		return listCategory;
	}

	public void setListCategory(List<ItemList> listCategory) {
		this.listCategory = listCategory;
	}

	public String getSubcategory() {
		return subcategory;
	}

	public void setSubcategory(String subcategory) {
		this.subcategory = subcategory;
	}

	public List<ItemList> getListSubcategory() {
		return listSubcategory;
	}

	public void setListSubcategory(List<ItemList> listSubcategory) {
		this.listSubcategory = listSubcategory;
	}

	public String getItemname() {
		return itemname;
	}

	public void setItemname(String itemname) {
		this.itemname = itemname;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getNumOfStockItems() {
		return numOfStockItems;
	}

	public void setNumOfStockItems(Integer numOfStockItems) {
		this.numOfStockItems = numOfStockItems;
	}

	public String getNwtype() {
		return nwtype;
	}

	public void setNwtype(String nwtype) {
		this.nwtype = nwtype;
	}

	public List<ItemList> getNwTypeList() {
		return nwTypeList;
	}

	public void setNwTypeList(List<ItemList> nwTypeList) {
		this.nwTypeList = nwTypeList;
	}

	public String getMemorystorage() {
		return memorystorage;
	}

	public void setMemorystorage(String memorystorage) {
		this.memorystorage = memorystorage;
	}

	public List<ItemList> getMemoryList() {
		return memoryList;
	}

	public void setMemoryList(List<ItemList> memoryList) {
		this.memoryList = memoryList;
	}

	public String getScreenresolution() {
		return screenresolution;
	}

	public void setScreenresolution(String screenresolution) {
		this.screenresolution = screenresolution;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getWithlength() {
		return withlength;
	}

	public void setWithlength(String withlength) {
		this.withlength = withlength;
	}
}
