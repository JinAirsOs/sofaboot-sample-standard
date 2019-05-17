package com.alipay.sofa.common.dal.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.alipay.sofa.common.dal.tables.User;

import java.util.ArrayList;
import java.util.List;

public interface UserDAO extends JpaRepository<User,Integer> {
}
