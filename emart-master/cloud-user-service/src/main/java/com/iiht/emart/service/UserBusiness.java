package com.iiht.emart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iiht.emart.entity.UserEntity;
import com.iiht.emart.repository.UserRepository;

@Service
public class UserBusiness implements UserService{
	@Autowired
	private UserRepository userRepository;

	public UserEntity findByUserName(String userName) {
		return userRepository.findByUserName(userName);
	}

	public UserEntity registUser(UserEntity user){
		return userRepository.saveAndFlush(user);
	}
}
