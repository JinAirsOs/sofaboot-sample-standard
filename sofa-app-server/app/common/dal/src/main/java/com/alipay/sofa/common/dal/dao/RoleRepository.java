package com.alipay.sofa.common.dal.dao;

import com.alipay.sofa.common.dal.tables.Role;
import com.alipay.sofa.common.dal.tables.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by rajeevkumarsingh on 02/08/17.
 */
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName roleName);
}
