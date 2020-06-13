package com.iiht.emart.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iiht.emart.entity.CodeMstEntity;

@Repository
public interface CodeMstRepository extends JpaRepository<CodeMstEntity, Integer>{

	List<CodeMstEntity> findByCode(String code);

	CodeMstEntity findByCodeAndValue(String code, String value);

}
