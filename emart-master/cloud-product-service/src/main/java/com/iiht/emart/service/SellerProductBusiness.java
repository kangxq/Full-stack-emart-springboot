package com.iiht.emart.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.iiht.emart.entity.CodeMstEntity;
import com.iiht.emart.entity.SellerProductEntity;
import com.iiht.emart.repository.CodeMstRepository;
import com.iiht.emart.repository.SellerProductRepository;
import com.iiht.emart.view.ItemList;
import com.iiht.emart.view.ProduceAllDataView;
import com.iiht.emart.view.ProduceDataView;
import com.iiht.emart.view.ProduceEditDataView;
import com.iiht.emart.view.ProduceSpecDataView;
import com.iiht.emart.vo.ProduceEditDataVo;
import com.iiht.emart.vo.SearchDataKeyVo;

@Service
public class SellerProductBusiness implements SellerProductService{
	@Autowired
	private SellerProductRepository sellerProductRepository;
	@Autowired
	private CodeMstRepository codeMstRepository;

	private final static String CATEGORY_CTG  = "CATEGORY_CTG";
	private final static String SUBCATEGORY_CTG  = "SUBCATEGORY_CTG";
	private final static String NW_TYPE  = "NW_TYPE";
	private final static String MEMORY_STORAGE  = "MEMORY_STORAGE";

	@Override
	public List<ProduceDataView> findByUserName(String userName) {
		List<SellerProductEntity> entityList = sellerProductRepository.findByCreateName(userName);

		List<ProduceDataView> dataList = new ArrayList<>();
		for (SellerProductEntity entity : entityList) {
			ProduceDataView view = new ProduceDataView();
			view.setId(entity.getId());
			view.setTitle(entity.getShopName());
			view.setDetails(entity.getItemname());
			view.setPrice(entity.getPrice());
			view.setNumber(entity.getNumOfStockItems());
			dataList.add(view);
		}

		return dataList;
	}

	@Override
	public List<ProduceDataView> deleteById(String id, String userName) {

		sellerProductRepository.delete(Integer.valueOf(id));

		return findByUserName(userName);
	}

	@Override
	public ProduceEditDataView findById(String id) {
		ProduceEditDataView view = new ProduceEditDataView();
		if (!StringUtils.isEmpty(id)) {
			SellerProductEntity entity = sellerProductRepository.findById(Integer.valueOf(id));
			view.setCategory(entity.getCategory());
			view.setSubcategory(entity.getSubcategory());
			view.setItemname(entity.getItemname());
			view.setPrice(entity.getPrice());
			view.setNumOfStockItems(entity.getNumOfStockItems());
			view.setNwtype(entity.getNwtype());
			view.setMemorystorage(entity.getMemorystorage());
			view.setScreenresolution(entity.getScreenresolution());
			view.setWeight(entity.getWeight());
			view.setWithlength(entity.getWithlength());
		} else {
			view.setCategory("");
			view.setSubcategory("");
			view.setItemname("");
			view.setNwtype("");
			view.setMemorystorage("");
			view.setScreenresolution("");
			view.setWeight("");
			view.setWithlength("");
		}
		List<CodeMstEntity> cmEntity1 = codeMstRepository.findByCode(CATEGORY_CTG);
		cmEntity1.sort(Comparator.comparing(CodeMstEntity::getCode));
		List<ItemList> listCategory = new ArrayList<>();
		ItemList itemList1 = new ItemList();
		itemList1.setValue("");
		itemList1.setName("");
		listCategory.add(itemList1);
		for (CodeMstEntity codeMstEntity : cmEntity1) {
			itemList1 = new ItemList();
			itemList1.setValue(codeMstEntity.getValue());
			itemList1.setName(codeMstEntity.getName());
			listCategory.add(itemList1);
		}
		view.setListCategory(listCategory);
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
		view.setListSubcategory(listSubcategory);
		List<CodeMstEntity> cmEntity3 = codeMstRepository.findByCode(NW_TYPE);
		cmEntity3.sort(Comparator.comparing(CodeMstEntity::getCode));
		List<ItemList> nwTypeList = new ArrayList<>();
		ItemList itemList3 = new ItemList();
		itemList3.setValue("");
		itemList3.setName("");
		nwTypeList.add(itemList3);
		for (CodeMstEntity codeMstEntity : cmEntity3) {
			itemList3 = new ItemList();
			itemList3.setValue(codeMstEntity.getValue());
			itemList3.setName(codeMstEntity.getName());
			nwTypeList.add(itemList3);
		}
		view.setNwTypeList(nwTypeList);
		List<CodeMstEntity> cmEntity4 = codeMstRepository.findByCode(MEMORY_STORAGE);
		cmEntity4.sort(Comparator.comparing(CodeMstEntity::getCode));
		List<ItemList> memoryList = new ArrayList<>();
		ItemList itemList4 = new ItemList();
		itemList4.setValue("");
		itemList4.setName("");
		memoryList.add(itemList4);
		for (CodeMstEntity codeMstEntity : cmEntity4) {
		    itemList4 = new ItemList();
			itemList4.setValue(codeMstEntity.getValue());
			itemList4.setName(codeMstEntity.getName());
			memoryList.add(itemList4);
		}
		view.setMemoryList(memoryList);
		return view;
	}

	@Override
	public void saveProduct(ProduceEditDataVo produceEditDataVo) {
		SellerProductEntity entity = new SellerProductEntity();
		if (produceEditDataVo.getId() != null ) {
			entity.setId(produceEditDataVo.getId());
		}
		entity.setCategory(produceEditDataVo.getCategory());
		entity.setSubcategory(produceEditDataVo.getSubcategory());
		CodeMstEntity cmEntity2 = codeMstRepository.findByCodeAndValue(SUBCATEGORY_CTG, produceEditDataVo.getSubcategory());
		if (cmEntity2 != null) {
			entity.setShopName(cmEntity2.getName());
		}
		entity.setItemname(produceEditDataVo.getItemname());
		entity.setPrice(produceEditDataVo.getPrice());
		entity.setNumOfStockItems(produceEditDataVo.getNumOfStockItems());
		entity.setNwtype(produceEditDataVo.getNwtype());
		entity.setMemorystorage(produceEditDataVo.getMemorystorage());
		entity.setScreenresolution(produceEditDataVo.getScreenresolution());
		entity.setWeight(produceEditDataVo.getWeight());
		entity.setWithlength(produceEditDataVo.getWithlength());
		entity.setIsActive("1");
		entity.setCreateTime(new Date());
		entity.setCreateName(produceEditDataVo.getUserName());
		entity.setUpdateTime(new Date());
		entity.setUpdateName(produceEditDataVo.getUserName());

		sellerProductRepository.save(entity);
	}

	@Override
	public List<ProduceAllDataView> findAll() {

		List<SellerProductEntity> entityList = sellerProductRepository.findAll();

		List<ProduceAllDataView> dataList = new ArrayList<>();
		for (SellerProductEntity entity : entityList) {
			ProduceAllDataView view = new ProduceAllDataView();
			view.setId(entity.getId());
			view.setTitle(entity.getShopName());
			view.setDetails(entity.getItemname());
			view.setPrice(entity.getPrice());
			view.setSellerUser(entity.getCreateName());
			dataList.add(view);
		}

		return dataList;
	}

	@Override
	public List<ProduceAllDataView> findAllProductBySearchKey(String searchname) {
		if (StringUtils.isEmpty(searchname)) {
			return findAll();
		} else {
			List<SellerProductEntity> entityList = sellerProductRepository.findByItemnameLike("%" + searchname + "%");
			List<ProduceAllDataView> dataList = new ArrayList<>();
			for (SellerProductEntity entity : entityList) {
				ProduceAllDataView view = new ProduceAllDataView();
				view.setId(entity.getId());
				view.setTitle(entity.getShopName());
				view.setDetails(entity.getItemname());
				view.setPrice(entity.getPrice());
				view.setSellerUser(entity.getCreateName());
				dataList.add(view);
			}
			return dataList;
		}

	}

	@Override
	public List<ProduceAllDataView> findAllProductByMoreKey(SearchDataKeyVo searchKey) {
		List<SellerProductEntity> entityList;
		if (StringUtils.isEmpty(searchKey.getSearchname())) {
			entityList = sellerProductRepository.findAll();
		} else {
			entityList = sellerProductRepository.findByItemnameLike("%" + searchKey.getSearchname() + "%");
		}
		List<ProduceAllDataView> dataList = new ArrayList<>();
		if (!StringUtils.isEmpty(searchKey.getStartprice()) &&
				!StringUtils.isEmpty(searchKey.getEndprice()) &&
				Integer.valueOf(searchKey.getStartprice()) > Integer.valueOf(searchKey.getEndprice())) {
			return dataList;
		}
		if (entityList != null && entityList.size() > 0) {
			for (SellerProductEntity entity : entityList) {
				if (!StringUtils.isEmpty(searchKey.getManufacturer()) &&
						!searchKey.getManufacturer().equals(entity.getSubcategory())) {
					continue;
				}

				if (!StringUtils.isEmpty(searchKey.getStartprice()) &&
						Integer.valueOf(searchKey.getStartprice()) > entity.getPrice()) {
					continue;
				}
				if (!StringUtils.isEmpty(searchKey.getEndprice()) &&
						Integer.valueOf(searchKey.getEndprice()) < entity.getPrice()) {
					continue;
				}
				ProduceAllDataView view = new ProduceAllDataView();
				view.setId(entity.getId());
				view.setTitle(entity.getShopName());
				view.setDetails(entity.getItemname());
				view.setPrice(entity.getPrice());
				view.setSellerUser(entity.getCreateName());
				dataList.add(view);
			}
		}
		return dataList;
	}

	@Override
	public ProduceSpecDataView findProductById(String id) {
		SellerProductEntity entity = sellerProductRepository.findById(Integer.valueOf(id));
		ProduceSpecDataView view = new ProduceSpecDataView();
		view.setId(entity.getId());
		view.setDetails(entity.getItemname());
		view.setPrice(entity.getPrice());
		view.setSellerUser(entity.getCreateName());
		view.setScreenResolution(entity.getScreenresolution());
		CodeMstEntity cmEntity3 = codeMstRepository.findByCodeAndValue(NW_TYPE, entity.getNwtype());
		if (cmEntity3 != null) {
			view.setNetworkType(cmEntity3.getName());
		}
		CodeMstEntity cmEntity4 = codeMstRepository.findByCodeAndValue(MEMORY_STORAGE, entity.getNwtype());
		if (cmEntity4 != null) {
			view.setMemoryStorage(cmEntity4.getName());
		}
		view.setWithlength(entity.getWithlength());
		view.setNumOfStockItems(entity.getNumOfStockItems());
		return view;
	}

}
