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


import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import java.lang.management.OperatingSystemMXBean;
import java.sql.Connection;
import java.sql.Statement;
import java.util.*;
import java.text.SimpleDateFormat;
import org.springframework.scheduling.annotation.Scheduled;

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
    public String sampleController() {
        return "pong";
    }

    @RequestMapping("/newReadService")
    public List<Student> readService() {
        return newReadService.readAll();
    }

    @RequestMapping("/queryAll")
    public List<Student> queryAll() {
        List<Student> list = new ArrayList<Student>();
        list = studentDAO.findAll();
        return list;
    }

    @RequestMapping("/getOne/{id}")
    public Student getStudent(@PathVariable("id") String id) {
        Student student = studentDAO.findById(Integer.valueOf(id)).get();
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

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public Map<String, Object> login(HttpServletRequest request){
        Map<String, Object> map = new HashMap<>();
        String name = request.getParameter("name");
        if(name == null){
            map.put("login", false);
            map.put("requestId",request.getSession().getId());
            return map;
        }
        Student student = new Student();
        student.setName(name);
        Optional<Student> op = studentDAO.findOne(Example.of(student));
        if (op.isPresent()) {
            request.getSession().setAttribute("userId", op.get().getId());
            map.put("requestId",request.getSession().getId());
            map.put("login", true);
        } else {
            map.put("login", false);
            map.put("requestId",request.getSession().getId());
        }
        return map;
    }

    @RequestMapping("getUserInfo")
    public Map<String, Object> getUserId(HttpServletRequest request){
        Map<String, Object> map = new HashMap<>();
        Object userId = request.getSession().getAttribute("userId");
        if (userId == null){
            map.put("error","not login");
            return map;
        }
        Optional<Student> optionalStudent = studentDAO.findById((int)userId);
        if(optionalStudent.isPresent()){
            map.put("userInfo", optionalStudent.get());
        } else {
            map.put("error","not login");
        }
        return map;
    }
}