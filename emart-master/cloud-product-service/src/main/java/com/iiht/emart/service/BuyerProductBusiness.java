package com.iiht.emart.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.iiht.emart.entity.BuyerProductEntity;
import com.iiht.emart.entity.CodeMstEntity;
import com.iiht.emart.entity.SellerProductEntity;
import com.iiht.emart.repository.BuyerProductRepository;
import com.iiht.emart.repository.CodeMstRepository;
import com.iiht.emart.repository.SellerProductRepository;
import com.iiht.emart.view.ProduceReoprtDataView;
import com.iiht.emart.view.ProduceReoprtDetailDataView;
import com.iiht.emart.view.ProduceSpecHistoryDataView;
import com.iiht.emart.view.PurchaseHistoryDataView;
import com.iiht.emart.view.ShoppingCartDataView;
import com.iiht.emart.view.ShoppingCartProduct;

@Service
public class BuyerProductBusiness implements BuyerProductService{
	@Autowired
	private BuyerProductRepository buyerProductRepository;
	@Autowired
	private SellerProductRepository sellerProductRepository;
	@Autowired
	private CodeMstRepository codeMstRepository;

	private final static String NW_TYPE  = "NW_TYPE";
	private final static String MEMORY_STORAGE  = "MEMORY_STORAGE";

	@Override
	public boolean checkOut(String id, Integer number, String username) {
		SellerProductEntity spentity = sellerProductRepository.findById(Integer.valueOf(id));
		if (spentity.getNumOfStockItems() >= number) {
			BuyerProductEntity entity = new BuyerProductEntity();
			entity.setProductId(spentity.getId());
			entity.setShopName(spentity.getShopName());
			entity.setItemname(spentity.getItemname());
			entity.setPrice(spentity.getPrice());
			entity.setNumber(number);
			entity.setSumAmt(spentity.getPrice()*number);
			entity.setDiscountAmt(0);
			entity.setSellerUsername(spentity.getCreateName());
			entity.setIsPayment("1");
			entity.setIsActive("1");
			entity.setCreateTime(new Date());
			entity.setCreateName(username);
			entity.setUpdateTime(new Date());
			entity.setUpdateName(username);
			buyerProductRepository.save(entity);
		} else {
			return false;
		}
		return true;
	}

	@Override
	public boolean addToCart(String id, Integer number, String username) {
		SellerProductEntity spentity = sellerProductRepository.findById(Integer.valueOf(id));
		if (spentity.getNumOfStockItems() >= number) {
			BuyerProductEntity entity = new BuyerProductEntity();
			entity.setProductId(spentity.getId());
			entity.setShopName(spentity.getShopName());
			entity.setItemname(spentity.getItemname());
			entity.setPrice(spentity.getPrice());
			entity.setNumber(number);
			entity.setSumAmt(spentity.getPrice()*number);
			entity.setDiscountAmt(0);
			entity.setSellerUsername(spentity.getCreateName());
			entity.setIsPayment("0");
			entity.setIsActive("1");
			entity.setCreateTime(new Date());
			entity.setCreateName(username);
			entity.setUpdateTime(new Date());
			entity.setUpdateName(username);
			buyerProductRepository.save(entity);

			spentity.setNumOfStockItems(spentity.getNumOfStockItems() - number);
			sellerProductRepository.save(spentity);

		} else {
			return false;
		}
		return true;
	}

	@Override
	public List<PurchaseHistoryDataView> getPurchasehistory(String username) {

		List<BuyerProductEntity> entityList = buyerProductRepository.findByCreateNameAndIsPayment(username, "1");

		List<PurchaseHistoryDataView> viewList = new ArrayList<>();
		if (entityList != null && entityList.size() > 0) {
			for (BuyerProductEntity entity : entityList) {
				PurchaseHistoryDataView view = new PurchaseHistoryDataView();
				view.setId(entity.getId());
				view.setTitle(entity.getShopName());
				view.setDetails(entity.getItemname());
				view.setPrice(entity.getPrice());
				view.setCount(entity.getNumber());
				view.setSumAmt(entity.getSumAmt());
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
				String dateString = formatter.format(entity.getUpdateTime());
				view.setBuydate(dateString);
				viewList.add(view);
			}
		}
		return viewList;
	}

	@Override
	public ProduceSpecHistoryDataView findProductById(String id) {

		BuyerProductEntity buyerProductEntity = buyerProductRepository.findById(Integer.valueOf(id));

		SellerProductEntity entity = sellerProductRepository.findById(buyerProductEntity.getProductId());
		ProduceSpecHistoryDataView view = new ProduceSpecHistoryDataView();
		view.setId(entity.getId());
		view.setDetails(entity.getItemname());
		view.setPrice(buyerProductEntity.getPrice());
		view.setCount(buyerProductEntity.getNumber());
		view.setSumAmt(buyerProductEntity.getSumAmt());
		view.setSellerUser(entity.getCreateName());
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
		String dateString = formatter.format(buyerProductEntity.getUpdateTime());
		view.setBuydate(dateString);
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

	@Override
	public List<ProduceReoprtDataView> findProductBySellerUsername(String username) {
		List<BuyerProductEntity> entityList = buyerProductRepository.findBySellerUsernameAndIsPayment(username, "1");

		return setDate(entityList);
	}

	@Override
	public List<ProduceReoprtDataView> findProductBySearchKey(String username, String startdate, String enddate) throws ParseException {
		List<BuyerProductEntity> entityList = buyerProductRepository.findBySellerUsernameAndIsPayment(username, "1");
		if (StringUtils.isEmpty(startdate) && StringUtils.isEmpty(enddate)) {
			return setDate(entityList);
		} else {
			List<BuyerProductEntity> editDataList = new ArrayList<>();
			for (BuyerProductEntity buyerProductEntity : entityList) {
				if (!StringUtils.isEmpty(startdate)) {
					SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
					Date sDate = formatter.parse(startdate);
					if (sDate.compareTo(buyerProductEntity.getUpdateTime()) > 0) {
						continue;
					}
				}
				if (!StringUtils.isEmpty(enddate)) {
					SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
					Date eDate = formatter.parse(enddate);
					if (eDate.compareTo(buyerProductEntity.getUpdateTime()) < 0) {
						continue;
					}
				}
				editDataList.add(buyerProductEntity);
			}
			return setDate(editDataList);
		}
	}

	private List<ProduceReoprtDataView> setDate(List<BuyerProductEntity> entityList) {
		List<ProduceReoprtDataView> resultList = new ArrayList<>();
		if (entityList != null && entityList.size()> 0) {
			entityList.sort(Comparator.comparing(BuyerProductEntity::getProductId));
			Integer productId = null;
			ProduceReoprtDataView view = new ProduceReoprtDataView();
			for (BuyerProductEntity buyerProductEntity : entityList) {
				if (productId == null) {
					view.setId(buyerProductEntity.getProductId());
					view.setTitle(buyerProductEntity.getShopName());
					view.setDetails(buyerProductEntity.getItemname());
					view.setNumber(buyerProductEntity.getNumber());
					view.setMonetofsum(buyerProductEntity.getSumAmt());
					productId = buyerProductEntity.getProductId();
				} else if(productId == buyerProductEntity.getProductId()) {
					view.setNumber(view.getNumber() + buyerProductEntity.getNumber());
					view.setMonetofsum(view.getMonetofsum() + buyerProductEntity.getSumAmt());
				} else {
					resultList.add(view);
					view = new ProduceReoprtDataView();
					view.setId(buyerProductEntity.getProductId());
					view.setTitle(buyerProductEntity.getShopName());
					view.setDetails(buyerProductEntity.getItemname());
					view.setNumber(buyerProductEntity.getNumber());
					view.setMonetofsum(buyerProductEntity.getSumAmt());
					productId = buyerProductEntity.getProductId();
				}
			}
			resultList.add(view);
		}
		return resultList;
	}

	@Override
	public List<ProduceReoprtDetailDataView> findProductDetails(String id, String username, String startdate,
			String enddate) throws ParseException {
		List<BuyerProductEntity> entityList = buyerProductRepository.findByProductIdAndSellerUsernameAndIsPayment(Integer.valueOf(id), username, "1");
		if (StringUtils.isEmpty(startdate) && StringUtils.isEmpty(enddate)) {
			return setDateDetails(entityList);
		} else {
			List<BuyerProductEntity> editDataList = new ArrayList<>();
			for (BuyerProductEntity buyerProductEntity : entityList) {
				if (!StringUtils.isEmpty(startdate)) {
					SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
					Date sDate = formatter.parse(startdate);
					if (sDate.compareTo(buyerProductEntity.getUpdateTime()) > 0) {
						continue;
					}
				}
				if (!StringUtils.isEmpty(enddate)) {
					SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
					Date eDate = formatter.parse(enddate);
					if (eDate.compareTo(buyerProductEntity.getUpdateTime()) < 0) {
						continue;
					}
				}
				editDataList.add(buyerProductEntity);
			}
			return setDateDetails(editDataList);
		}
	}

	private List<ProduceReoprtDetailDataView> setDateDetails(List<BuyerProductEntity> entityList) {
		List<ProduceReoprtDetailDataView> resultList = new ArrayList<>();
		if (entityList != null && entityList.size()> 0) {
			entityList.sort(Comparator.comparing(BuyerProductEntity::getProductId));
			Integer price = null;
			ProduceReoprtDetailDataView view = new ProduceReoprtDetailDataView();
			for (BuyerProductEntity buyerProductEntity : entityList) {
				if (price == null) {
					view.setTitle(buyerProductEntity.getShopName());
					view.setDetails(buyerProductEntity.getItemname());
					view.setNumber(buyerProductEntity.getNumber());
					view.setMonetofsum(buyerProductEntity.getSumAmt());
					view.setPrice(buyerProductEntity.getPrice());
					price = buyerProductEntity.getPrice();
				} else if(price == buyerProductEntity.getPrice()) {
					view.setNumber(view.getNumber() + buyerProductEntity.getNumber());
					view.setMonetofsum(view.getMonetofsum() + buyerProductEntity.getSumAmt());
				} else {
					resultList.add(view);
					view = new ProduceReoprtDetailDataView();
					view.setTitle(buyerProductEntity.getShopName());
					view.setDetails(buyerProductEntity.getItemname());
					view.setNumber(buyerProductEntity.getNumber());
					view.setMonetofsum(buyerProductEntity.getSumAmt());
					view.setPrice(buyerProductEntity.getPrice());
					price = buyerProductEntity.getPrice();
				}
			}
			resultList.add(view);
		}
		return resultList;
	}

	@Override
	public ShoppingCartDataView findProductByBuyerUsername(String username) {
		List<BuyerProductEntity> entityList = buyerProductRepository.findByCreateNameAndIsPayment(username, "0");

		ShoppingCartDataView view = new ShoppingCartDataView();
		List<ShoppingCartProduct> scpList = new ArrayList<>();
		if (entityList != null && entityList.size() > 0) {
			view.setViewFlag(true);
			Integer totalPrice = 0;
			for (BuyerProductEntity buyerProductEntity : entityList) {
				ShoppingCartProduct scp = new ShoppingCartProduct();
				scp.setId(buyerProductEntity.getId());
				scp.setTitle(buyerProductEntity.getShopName());
				scp.setDetails(buyerProductEntity.getItemname());
				SellerProductEntity spEntity = sellerProductRepository.findById(buyerProductEntity.getProductId());
				if (spEntity.getNumOfStockItems() >= buyerProductEntity.getNumber()) {
					scp.setNumber(buyerProductEntity.getNumber());
				} else {
					scp.setNumber(spEntity.getNumOfStockItems());
				}
				scp.setPrice(buyerProductEntity.getPrice());
				scp.setNumberEdit(false);
				totalPrice = totalPrice + scp.getNumber()*scp.getPrice();
				scpList.add(scp);
			}
			view.setTotalPrice(totalPrice);
			view.setTotalTax((int) (totalPrice*0.1));
		}
		view.setProductList(scpList);

		return view;
	}

	@Override
	public void saveNumber(String id, String number) throws Exception {
		BuyerProductEntity bpEntity = buyerProductRepository.findById(Integer.valueOf(id));
		SellerProductEntity spEntity = sellerProductRepository.findById(bpEntity.getProductId());
		if (spEntity.getNumOfStockItems() >= Integer.valueOf(number)) {
			bpEntity.setNumber(Integer.valueOf(number));
			buyerProductRepository.save(bpEntity);
		} else {
			throw new Exception("There are only " + spEntity.getNumOfStockItems() + " items.");
		}

	}

	@Override
	public void shoppingcCartDelete(String id) {
		buyerProductRepository.delete(Integer.valueOf(id));
	}

	@Override
	public void productCheckout(String username) {
		List<BuyerProductEntity> entityList = buyerProductRepository.findByCreateNameAndIsPayment(username, "0");
		if (entityList != null && entityList.size() > 0) {
			for (BuyerProductEntity buyerProductEntity : entityList) {
				if (buyerProductEntity.getNumber() != 0) {
					buyerProductEntity.setIsPayment("1");
					buyerProductEntity.setUpdateTime(new Date());
					buyerProductRepository.save(buyerProductEntity);
				}
			}
		}
	}
}
