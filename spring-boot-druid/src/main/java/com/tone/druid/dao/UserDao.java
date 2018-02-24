package com.tone.druid.dao;

import org.springframework.data.repository.CrudRepository;

import com.tone.druid.model.User;

/**
 * @author zhaoxiang.liu
 * @date 2018/2/23
 */
public interface UserDao extends CrudRepository<User, Long> {
}
