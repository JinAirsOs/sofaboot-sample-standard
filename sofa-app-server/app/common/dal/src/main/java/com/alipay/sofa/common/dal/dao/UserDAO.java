package com.alipay.sofa.common.dal.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.alipay.sofa.common.dal.tables.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface UserDAO extends JpaRepository<User,Integer> {

    Optional<List<User>> findByName(String name);

    Optional<User> findById(Long id);
}
