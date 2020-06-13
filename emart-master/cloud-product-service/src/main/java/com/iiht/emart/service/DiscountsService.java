package com.iiht.emart.service;

import java.util.List;

import com.iiht.emart.view.DiscountsDataView;

public interface DiscountsService {

	List<DiscountsDataView> getDiscounts(String username);

	Integer findDiscount(String username, String discountcode) throws Exception;

}
