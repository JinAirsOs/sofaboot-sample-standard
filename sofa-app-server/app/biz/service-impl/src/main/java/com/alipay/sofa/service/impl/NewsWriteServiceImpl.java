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
package com.alipay.sofa.service.impl;

import com.alipay.sofa.common.dal.dao.StudentDAO;
import com.alipay.sofa.common.dal.tables.Student;
import com.alipay.sofa.facade.NewsWriteService;
import com.alipay.sofa.runtime.api.annotation.SofaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

/**
 * @author kerry.pzd
 * @since 2.5.0
 */
//用@Service注册bean
//用@SofaService注册JVM服务。
@Service
@SofaService
public class NewsWriteServiceImpl implements NewsWriteService {

    @Autowired
    private StudentDAO studentDAO;

    @Override
    public int addStudent(String name, int age) {
        try {
            Student student = new Student();
            student.setName(name);
            student.setAge(age);
            student = studentDAO.save(student);
            return student.getId();
        } catch (Throwable ex) {
            return 0;
        }
    }
}