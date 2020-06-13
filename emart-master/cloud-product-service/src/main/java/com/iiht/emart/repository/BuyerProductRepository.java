package com.iiht.emart.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iiht.emart.entity.BuyerProductEntity;

@Repository
public interface BuyerProductRepository extends JpaRepository<BuyerProductEntity, Integer>{

	List<BuyerProductEntity> findByCreateNameAndIsPayment(String createName, String isPayment);

	BuyerProductEntity findById(Integer id);

	List<BuyerProductEntity> findBySellerUsernameAndIsPayment(String sellerUsername, String isPayment);

	List<BuyerProductEntity> findByProductIdAndSellerUsernameAndIsPayment(Integer productId, String sellerUsername,
			String isPayment);

}
