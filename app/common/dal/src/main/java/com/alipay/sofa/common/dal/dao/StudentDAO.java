package com.alipay.sofa.common.dal.dao;




import org.springframework.data.jpa.repository.JpaRepository;
import com.alipay.sofa.common.dal.tables.Student;

public interface StudentDAO extends JpaRepository<Student,Integer> {

}