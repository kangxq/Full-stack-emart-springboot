package com.iiht.emart.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iiht.emart.entity.DiscountsEntity;

@Repository
public interface DiscountsRepository extends JpaRepository<DiscountsEntity, Integer>{

	List<DiscountsEntity> findByBuyerUsername(String buyerUsername);

	DiscountsEntity findByBuyerUsernameAndDiscountCode(String buyerUsername, String discountCode);

}
