package com.iiht.emart.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="buyer_product")
public class BuyerProductEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID")
	private Integer id;
	private Integer productId;
	private String shopName;
	private String itemname;
	private Integer price;
	private Integer number;
	private Integer sumAmt;
	private Integer discountAmt;
	private String sellerUsername;
	private String isPayment;
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
	@Column(name="PRODUCT_ID")
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
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
	@Column(name="NUMBER")
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	@Column(name="SUM_AMT")
	public Integer getSumAmt() {
		return sumAmt;
	}
	public void setSumAmt(Integer sumAmt) {
		this.sumAmt = sumAmt;
	}
	@Column(name="DISCOUNT_AMT")
	public Integer getDiscountAmt() {
		return discountAmt;
	}
	public void setDiscountAmt(Integer discountAmt) {
		this.discountAmt = discountAmt;
	}
	@Column(name="SELLER_USERNAME")
	public String getSellerUsername() {
		return sellerUsername;
	}
	public void setSellerUsername(String sellerUsername) {
		this.sellerUsername = sellerUsername;
	}
	@Column(name="IS_PAYMENT")
	public String getIsPayment() {
		return isPayment;
	}
	public void setIsPayment(String isPayment) {
		this.isPayment = isPayment;
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
