package com.iiht.emart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iiht.emart.service.CodeMstService;
import com.iiht.emart.view.ItemList;

@RestController
@RequestMapping("/emart")
@CrossOrigin(origins="http://localhost:4200")
public class CodeMstController {
	@Autowired
	private CodeMstService codeMstService;

	@RequestMapping(value = { "/search/manufacturer" }, method = RequestMethod.POST)
	public List<ItemList> getManufacturer(){
		List<ItemList> itemList = codeMstService.getManufacturer();
		return itemList;
	}

}
