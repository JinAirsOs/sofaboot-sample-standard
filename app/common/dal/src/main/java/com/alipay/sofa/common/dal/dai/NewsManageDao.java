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
package com.alipay.sofa.common.dal.dai;

import com.alipay.sofa.common.dal.dao.NewsDO;

import java.sql.SQLException;
import java.util.List;

/**
 * @author qilong.zql
 * @since 2.5.0
 */
public interface NewsManageDao {

    /**
     * insert a new
     * @param newDO
     * @return
     * @throws SQLException
     */
    int insert(NewsDO newDO) throws SQLException;

    /**
     * query new according to author
     * @param author
     * @return
     * @throws SQLException
     */
    List<NewsDO> query(String author) throws SQLException;

    /**
     * delete new according to author
     * @param author
     * @throws SQLException
     */
    void delete(String author) throws SQLException;
}