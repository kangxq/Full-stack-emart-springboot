package com.iiht.emart.controller;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iiht.emart.service.BuyerProductService;
import com.iiht.emart.view.ProduceReoprtDataView;
import com.iiht.emart.view.ProduceReoprtDetailDataView;
import com.iiht.emart.view.ProduceSpecHistoryDataView;
import com.iiht.emart.view.PurchaseHistoryDataView;
import com.iiht.emart.view.ShoppingCartDataView;

@RestController
@RequestMapping("/emart")
@CrossOrigin(origins="http://localhost:4200")
public class BuyerProductController {
	@Autowired
	private BuyerProductService buyerProductService;

	@RequestMapping(value = { "/specifications/checkout" }, method = RequestMethod.POST)
	public void checkOut(@RequestParam(name="id") String id,
			@RequestParam(name="number") Integer number,
			@RequestParam(name="username") String username) throws Exception{
		boolean checkOut = buyerProductService.checkOut(id, number, username);
		if (!checkOut) {
			throw new Exception("There is not so much in stock.");
		}
	}

	@RequestMapping(value = { "/specifications/addtocart" }, method = RequestMethod.POST)
	public void addToCart(@RequestParam(name="id") String id,
			@RequestParam(name="number") Integer number,
			@RequestParam(name="username") String username) throws Exception{
		boolean addToCart = buyerProductService.addToCart(id, number, username);
		if (!addToCart) {
			throw new Exception("There is not so much in stock.");
		}
	}

	@RequestMapping(value = { "/purchasehistory" }, method = RequestMethod.POST)
	public List<PurchaseHistoryDataView> getPurchasehistory(@RequestParam(name="username") String username) throws Exception{
		List<PurchaseHistoryDataView> resultList = buyerProductService.getPurchasehistory(username);
		return resultList;
	}

	@RequestMapping(value = { "/specificationshistory/searchbyid" }, method = RequestMethod.POST)
	public ProduceSpecHistoryDataView getProductById(@RequestParam(name="id") String id) {
		ProduceSpecHistoryDataView produceSpecHistoryDataView = buyerProductService.findProductById(id);
		return produceSpecHistoryDataView;
	}

	@RequestMapping(value = { "/report/init" }, method = RequestMethod.POST)
	public List<ProduceReoprtDataView> getProductReport(@RequestParam(name="username") String username) {
		return buyerProductService.findProductBySellerUsername(username);
	}
	@RequestMapping(value = { "/report/search" }, method = RequestMethod.POST)
	public List<ProduceReoprtDataView> getProductReportByDate(
			@RequestParam(name="username") String username,
			@RequestParam(name="startdate") String startdate,
			@RequestParam(name="enddate") String enddate) throws ParseException {
		return buyerProductService.findProductBySearchKey(username, startdate, enddate);
	}
	@RequestMapping(value = { "/reportdetails/search" }, method = RequestMethod.POST)
	public List<ProduceReoprtDetailDataView> getProductReportDetails(
			@RequestParam(name="id") String id,
			@RequestParam(name="username") String username,
			@RequestParam(name="startdate") String startdate,
			@RequestParam(name="enddate") String enddate) throws ParseException {
		return buyerProductService.findProductDetails(id, username, startdate, enddate);
	}

	@RequestMapping(value = { "/shoppingcart/search" }, method = RequestMethod.GET)
	public ShoppingCartDataView getShoppingcCartSearch(@RequestParam(name="username") String username) {
		return buyerProductService.findProductByBuyerUsername(username);
	}

	@RequestMapping(value = { "/shoppingcart/save" }, method = RequestMethod.GET)
	public void getShoppingcCartSave(@RequestParam(name="id") String id,
			@RequestParam(name="number") String number) throws Exception {
		buyerProductService.saveNumber(id, number);
	}

	@RequestMapping(value = { "/shoppingcart/delete" }, method = RequestMethod.GET)
	public void getShoppingcCartDelete(@RequestParam(name="id") String id) {
		buyerProductService.shoppingcCartDelete(id);
	}

	@RequestMapping(value = { "/shoppingcart/checkout" }, method = RequestMethod.GET)
	public void getShoppingcCartCheckout(@RequestParam(name="username") String username) {
		buyerProductService.productCheckout(username);
	}
}
