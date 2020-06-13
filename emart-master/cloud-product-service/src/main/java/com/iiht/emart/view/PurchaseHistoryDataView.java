package com.iiht.emart.view;

public class PurchaseHistoryDataView {
	private Integer id;
	private Integer price;
	private Integer count;
	private Integer sumAmt;
	private String title;
	private String details;
	private String buydate;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Integer getSumAmt() {
		return sumAmt;
	}
	public void setSumAmt(Integer sumAmt) {
		this.sumAmt = sumAmt;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public String getBuydate() {
		return buydate;
	}
	public void setBuydate(String buydate) {
		this.buydate = buydate;
	}

}
