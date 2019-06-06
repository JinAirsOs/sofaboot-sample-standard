package com.alipay.sofa.scheduler;

import com.alipay.sofa.config.AppConfig;
import com.alipay.sofa.facade.StudentRpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


@Component
public class SampleScheduler {
    //20秒
    private static final long TWENTY_SECONDS = 20000;
    private static final Logger LOGGER = LoggerFactory.getLogger(SampleScheduler.class);
    //sofareference的方式不用xml 引用，但是不直观。
    //@SofaReference(binding = @SofaReferenceBinding(bindingType = "bolt"))
    //bean的方式引用虽然多了xml设置，但是便于理解，自己取舍。
    @Resource
    private StudentRpcService studentRpcService;

    @Resource
    private AppConfig appConfig;
    @Scheduled(fixedRate = TWENTY_SECONDS)
    public void scheduledTask() {
        //注意 studentRpcService已经被zookeeper注册成为bean,xml如果不注册成为bean,
        // 可以用上面的方式引用
        LOGGER.info(studentRpcService.ping());
    }
}
