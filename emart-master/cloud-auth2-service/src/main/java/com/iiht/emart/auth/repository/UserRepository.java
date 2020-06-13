package com.iiht.emart.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iiht.emart.auth.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer>{

	public UserEntity findById(Integer id);

	public UserEntity findUserByUserName(String userName);

//	@Modifying
//	@Transactional
//	@Query(value = "update s_user u set u.conformed='1' where u.id=?",nativeQuery = true)
//	public int activeUser(@Param("id")Integer id);
}
