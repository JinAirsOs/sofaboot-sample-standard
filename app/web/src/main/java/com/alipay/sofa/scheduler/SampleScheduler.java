package com.alipay.sofa.scheduler;

import com.alipay.sofa.facade.StudentRpcService;
import com.alipay.sofa.rpc.config.ConsumerConfig;
import com.alipay.sofa.runtime.api.annotation.SofaReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class SampleScheduler {
    //20秒
    private static final long TWENTY_SECONDS = 20000;
    private static final Logger LOGGER = LoggerFactory.getLogger(SampleScheduler.class);

    @Resource
    private StudentRpcService studentRpcService;

    @Scheduled(fixedRate = TWENTY_SECONDS)
    public void scheduledTask() {
        //注意 studentRpcService已经被zookeeper注册成为bean
        LOGGER.info(studentRpcService.sayName());
    }
}
