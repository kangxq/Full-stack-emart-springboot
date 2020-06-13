package com.iiht.emart.service;

import com.iiht.emart.entity.UserEntity;

public interface UserService {


	public UserEntity findByUserName(String userName);

	public UserEntity registUser(UserEntity user);
}
