package com.iiht.emart.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iiht.emart.entity.UserEntity;
import com.iiht.emart.service.UserService;
import com.iiht.emart.view.UserView;

@RestController
@RequestMapping("/emart")
@CrossOrigin(origins="http://localhost:4200")
public class UserController {
	@Autowired
	private UserService userService;

	@RequestMapping(value = { "/login" }, method = RequestMethod.POST)
	public ResponseEntity<UserEntity> login(@RequestParam(name="userName") String userName){
		UserEntity user = userService.findByUserName(userName);
		return ResponseEntity.ok(user);
	}

	@RequestMapping(value = { "/register" }, method = RequestMethod.POST)
	public ResponseEntity<UserEntity> regist(@RequestBody UserView user) throws Exception{

		UserEntity userData = userService.findByUserName(user.getUserName());

		if (userData != null && !StringUtils.isEmpty(userData.getUserName())) {
			throw new Exception("user has exist!");
		} else {
			UserEntity entity = new UserEntity();
			entity.setUserName(user.getUserName());
			entity.setPassword(user.getPassword());
			entity.setUserType(user.getBasicSelect());
			entity.setCompanyName(user.getCompanyname());
			entity.setGstin(user.getGstin());
			entity.setBriefAboutCompany(user.getBriefaboutcompany());
			entity.setPostalAddress(user.getPostaladdress());
			entity.setWebsite(user.getWebsite());
			if (StringUtils.endsWithIgnoreCase(user.getBasicSelect(), "0")) {
				entity.setEmail(user.getEmalid());
			} else {
				entity.setEmail(user.getEmalidseller());
			}
			entity.setMobileNum(user.getMobileNumber());
			entity.setContactNumber(user.getContactnumber());
			entity.setIsActive("1");
			entity.setCreateTime(new Date());
			entity.setCreateName(user.getUserName());
			entity.setUpdateTime(new Date());
			entity.setUpdateName(user.getUserName());
			UserEntity userEntity = userService.registUser(entity);
			return ResponseEntity.status(HttpStatus.OK).body(userEntity);
		}

	}
}
