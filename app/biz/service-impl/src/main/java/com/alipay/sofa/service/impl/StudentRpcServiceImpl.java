package com.alipay.sofa.service.impl;

import com.alipay.sofa.facade.StudentRpcService;

public class StudentRpcServiceImpl implements StudentRpcService {

    public String sayName(){
        return "hello student rpc service";
    }
}
