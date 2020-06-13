package com.iiht.emart.service;

import java.util.List;

import com.iiht.emart.view.ProduceAllDataView;
import com.iiht.emart.view.ProduceDataView;
import com.iiht.emart.view.ProduceEditDataView;
import com.iiht.emart.view.ProduceSpecDataView;
import com.iiht.emart.vo.ProduceEditDataVo;
import com.iiht.emart.vo.SearchDataKeyVo;

public interface SellerProductService {

	List<ProduceDataView> findByUserName(String userName);

	List<ProduceDataView> deleteById(String id, String userName);

	ProduceEditDataView findById(String id);

	void saveProduct(ProduceEditDataVo produceEditDataVo);

	List<ProduceAllDataView> findAll();

	List<ProduceAllDataView> findAllProductBySearchKey(String searchname);

	List<ProduceAllDataView> findAllProductByMoreKey(SearchDataKeyVo searchKey);

	ProduceSpecDataView findProductById(String id);

}
