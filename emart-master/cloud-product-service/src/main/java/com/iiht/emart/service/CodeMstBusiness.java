package com.iiht.emart.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iiht.emart.entity.CodeMstEntity;
import com.iiht.emart.repository.CodeMstRepository;
import com.iiht.emart.view.ItemList;

@Service
public class CodeMstBusiness implements CodeMstService{
	@Autowired
	private CodeMstRepository codeMstRepository;
	private final static String SUBCATEGORY_CTG  = "SUBCATEGORY_CTG";
	@Override
	public List<ItemList> getManufacturer() {
		List<CodeMstEntity> cmEntity2 = codeMstRepository.findByCode(SUBCATEGORY_CTG);
		cmEntity2.sort(Comparator.comparing(CodeMstEntity::getCode));
		List<ItemList> listSubcategory = new ArrayList<>();
		ItemList itemList2 = new ItemList();
		itemList2.setValue("");
		itemList2.setName("");
		listSubcategory.add(itemList2);
		for (CodeMstEntity codeMstEntity : cmEntity2) {
			itemList2 = new ItemList();
			itemList2.setValue(codeMstEntity.getValue());
			itemList2.setName(codeMstEntity.getName());
			listSubcategory.add(itemList2);
		}
		return listSubcategory;
	}

}
