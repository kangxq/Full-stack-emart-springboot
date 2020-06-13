package com.iiht.emart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iiht.emart.service.DiscountsService;
import com.iiht.emart.view.DiscountsDataView;

@RestController
@RequestMapping("/emart")
@CrossOrigin(origins="http://localhost:4200")
public class DiscountsController {
	@Autowired
	private DiscountsService discountsService;

	@RequestMapping(value = { "/discounts" }, method = RequestMethod.POST)
	public List<DiscountsDataView> getDiscounts(@RequestParam(name="username") String username) throws Exception{
		List<DiscountsDataView> resultList = discountsService.getDiscounts(username);
		return resultList;
	}

	@RequestMapping(value = { "/shoppingcart/applyDiscountOk" }, method = RequestMethod.GET)
	public int applyDiscountOk(@RequestParam(name="username") String username,
			@RequestParam(name="discountcode") String discountcode) throws Exception {
		return discountsService.findDiscount(username, discountcode);
	}
}
