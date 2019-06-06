package com.alipay.sofa.service.impl;

import com.alipay.sofa.rpc.core.exception.SofaRpcException;
import com.alipay.sofa.rpc.core.request.SofaRequest;
import com.alipay.sofa.rpc.core.response.SofaResponse;
import com.alipay.sofa.rpc.filter.Filter;
import com.alipay.sofa.rpc.filter.FilterInvoker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserAuthFilter extends Filter {

    private static final Logger logger = LoggerFactory.getLogger(UserAuthFilter.class);
    @Override
    public SofaResponse invoke(FilterInvoker invoker, SofaRequest request) throws SofaRpcException {
        logger.info("user auth filter before server process , method: {}, data: {}",request.getMethodName(),request.getMethodArgs());
        try {
            return invoker.invoke(request);
        } finally {
            logger.info( "user auth filter after server process , method: {}",request.getMethodName());
        }
    }
}