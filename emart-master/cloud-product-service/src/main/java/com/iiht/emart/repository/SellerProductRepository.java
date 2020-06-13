package com.iiht.emart.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iiht.emart.entity.SellerProductEntity;

@Repository
public interface SellerProductRepository extends JpaRepository<SellerProductEntity, Integer>{

	List<SellerProductEntity> findByCreateName(String createName);

	SellerProductEntity findById(Integer id);

	List<SellerProductEntity> findByItemnameLike(String itemname);
}
