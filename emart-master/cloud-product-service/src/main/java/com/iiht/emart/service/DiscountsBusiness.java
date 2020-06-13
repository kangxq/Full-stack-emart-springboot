package com.iiht.emart.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iiht.emart.entity.DiscountsEntity;
import com.iiht.emart.repository.DiscountsRepository;
import com.iiht.emart.view.DiscountsDataView;

@Service
public class DiscountsBusiness implements DiscountsService{
	@Autowired
	private DiscountsRepository discountsRepository;

	@Override
	public List<DiscountsDataView> getDiscounts(String buyerUsername) {
		List<DiscountsEntity> list = discountsRepository.findByBuyerUsername(buyerUsername);
		List<DiscountsDataView> entityList = new ArrayList<>();
		Date date = new Date();
		for (DiscountsEntity entity : list) {
			Date startDate = entity.getStartDate();
			Date endDate = entity.getEndDate();
			if (date.compareTo(startDate) >= 0 && date.compareTo(endDate) <= 0) {
				DiscountsDataView view = new DiscountsDataView();
				view.setDiscountCode(entity.getDiscountCode());
				view.setDescription(entity.getDescription());
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
				String startDateString = formatter.format(startDate);
				view.setStartDate(startDateString);
				String endDateString = formatter.format(endDate);
				view.setEndDate(endDateString);
				entityList.add(view);
			}
		}
		return entityList;
	}

	@Override
	public Integer findDiscount(String username, String discountcode) throws Exception {
		DiscountsEntity entity = discountsRepository.findByBuyerUsernameAndDiscountCode(username, discountcode);
		if (entity != null) {
			return Integer.valueOf(entity.getPercentage());
		} else {
			throw new Exception("The discount does not exist!");
		}
	}

}
