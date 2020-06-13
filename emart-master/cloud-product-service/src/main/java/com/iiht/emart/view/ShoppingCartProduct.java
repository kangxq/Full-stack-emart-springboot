package com.iiht.emart.view;

public class ShoppingCartProduct {

	private Integer id;
	private Integer price;
	private String title;
	private String details;
	private Integer number;
	private boolean numberEdit;
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
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public boolean isNumberEdit() {
		return numberEdit;
	}
	public void setNumberEdit(boolean numberEdit) {
		this.numberEdit = numberEdit;
	}
}
