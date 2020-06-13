package com.iiht.emart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iiht.emart.service.SellerProductService;
import com.iiht.emart.view.ProduceAllDataView;
import com.iiht.emart.view.ProduceDataView;
import com.iiht.emart.view.ProduceEditDataView;
import com.iiht.emart.view.ProduceSpecDataView;
import com.iiht.emart.vo.ProduceEditDataVo;
import com.iiht.emart.vo.SearchDataKeyVo;

@RestController
@RequestMapping("/emart")
@CrossOrigin(origins="http://localhost:4200")
public class SellerProductController {
	@Autowired
	private SellerProductService sellerProductService;

	@RequestMapping(value = { "/sellerproduct/search" }, method = RequestMethod.POST)
	public List<ProduceDataView> spSearch(@RequestParam(name="userName") String userName){
		List<ProduceDataView> sellerProductEntity = sellerProductService.findByUserName(userName);
		return sellerProductEntity;
	}

	@RequestMapping(value = { "/sellerproduct/delete" }, method = RequestMethod.POST)
	public List<ProduceDataView> spDelete(@RequestParam(name="id") String id,
			@RequestParam(name="userName") String userName){
		List<ProduceDataView> sellerProductEntity = sellerProductService.deleteById(id, userName);
		return sellerProductEntity;
	}

	@RequestMapping(value = { "/selleraddedit/getproduct" }, method = RequestMethod.POST)
	public ProduceEditDataView getProduct(@RequestParam(name="id", required=false) String id){
		ProduceEditDataView produceEditDataView = sellerProductService.findById(id);
		return produceEditDataView;
	}

	@RequestMapping(value = { "/selleraddedit/saveproduct" }, method = RequestMethod.POST)
	public void saveProduct(@RequestBody ProduceEditDataVo produceEditDataVo){
		sellerProductService.saveProduct(produceEditDataVo);
	}

	@RequestMapping(value = { "/search/buyitem" }, method = RequestMethod.POST)
	public List<ProduceAllDataView> getAllProduct(){
		List<ProduceAllDataView> produceAllDataView = sellerProductService.findAll();
		return produceAllDataView;
	}

	@RequestMapping(value = { "/search/searchbyname" }, method = RequestMethod.POST)
	public List<ProduceAllDataView> getAllProductBySearchKey(@RequestParam(name="searchname", required=false) String searchname){
		List<ProduceAllDataView> produceAllDataView = sellerProductService.findAllProductBySearchKey(searchname);
		return produceAllDataView;
	}
	@RequestMapping(value = { "/search/searchbymorekey" }, method = RequestMethod.POST)
	public List<ProduceAllDataView> getAllProductByMoreKey(@RequestBody SearchDataKeyVo searchKey){
		List<ProduceAllDataView> produceAllDataView =
				sellerProductService.findAllProductByMoreKey(searchKey);
		return produceAllDataView;
	}

	@RequestMapping(value = { "/specifications/searchbyid" }, method = RequestMethod.POST)
	public ProduceSpecDataView getProductById(@RequestParam(name="id") String id){
		ProduceSpecDataView produceSpecDataView = sellerProductService.findProductById(id);
		return produceSpecDataView;
	}
}
