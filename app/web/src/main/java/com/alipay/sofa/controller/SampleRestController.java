/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.alipay.sofa.controller;

import com.alipay.sofa.common.dal.dao.StudentDAO;
import com.alipay.sofa.common.dal.tables.Student;

import com.alipay.sofa.facade.NewsReadService;
import com.alipay.sofa.facade.NewsWriteService;
import com.alipay.sofa.runtime.api.annotation.SofaReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.web.bind.annotation.*;


import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author qilong.zql
 * @since 2.5.8
 */
@RestController
public class SampleRestController {

    private static int cnt = 0;

    @SofaReference
    private NewsReadService  newReadService;

    @SofaReference
    private NewsWriteService newWriteService;

    @Autowired
    private StudentDAO studentDAO;

    @RequestMapping("/json")
    public List<Student> sampleController() {
        return newReadService.readAll();
    }

    @RequestMapping("/queryAll")
    public List<Student> queryAll() {
        List<Student> list = new ArrayList<Student>();
        list = studentDAO.findAll();
        return list;
    }

    @RequestMapping("/getOne")
    public Student getStudent() {
        Student student = studentDAO.findById(1).get();
        return student;
    }

    @RequestMapping("add")
    public Student add(){
        Student user = new Student();
        user.setName("junjun"+(cnt++));
        user.setAge(6);
        studentDAO.save(user);
        return user;
    }

    @RequestMapping("update")
    public Student update(){
        Student student = studentDAO.findById(1).get();
        student.setName("duoduo");
        Student res = studentDAO.save(student);
        return res;
    }

}