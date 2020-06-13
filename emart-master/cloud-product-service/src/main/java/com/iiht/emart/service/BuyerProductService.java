package com.iiht.emart.service;

import java.text.ParseException;
import java.util.List;

import com.iiht.emart.view.ProduceReoprtDataView;
import com.iiht.emart.view.ProduceReoprtDetailDataView;
import com.iiht.emart.view.ProduceSpecHistoryDataView;
import com.iiht.emart.view.PurchaseHistoryDataView;
import com.iiht.emart.view.ShoppingCartDataView;

public interface BuyerProductService {

	boolean checkOut(String id, Integer number, String username);

	boolean addToCart(String id, Integer number, String username);

	List<PurchaseHistoryDataView> getPurchasehistory(String username);

	ProduceSpecHistoryDataView findProductById(String id);

	List<ProduceReoprtDataView> findProductBySellerUsername(String username);

	List<ProduceReoprtDataView> findProductBySearchKey(String username, String startdate, String enddate) throws ParseException;

	List<ProduceReoprtDetailDataView> findProductDetails(String id, String username, String startdate, String enddate) throws ParseException;

	ShoppingCartDataView findProductByBuyerUsername(String username);

	void saveNumber(String id, String number) throws Exception;

	void shoppingcCartDelete(String id);

	void productCheckout(String username);

}
