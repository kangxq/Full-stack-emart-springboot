package com.iiht.emart.view;

import java.util.List;

public class ShoppingCartDataView {
	private Integer totalTax;
	private Integer totalPrice;
	private boolean viewFlag = false;
	private List<ShoppingCartProduct> productList;
	public Integer getTotalTax() {
		return totalTax;
	}
	public void setTotalTax(Integer totalTax) {
		this.totalTax = totalTax;
	}
	public Integer getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Integer totalPrice) {
		this.totalPrice = totalPrice;
	}
	public boolean isViewFlag() {
		return viewFlag;
	}
	public void setViewFlag(boolean viewFlag) {
		this.viewFlag = viewFlag;
	}
	public List<ShoppingCartProduct> getProductList() {
		return productList;
	}
	public void setProductList(List<ShoppingCartProduct> productList) {
		this.productList = productList;
	}

}
