package com.alipay.sofa.scheduler;

import com.alipay.sofa.facade.StudentRpcService;
import com.alipay.sofa.rpc.config.ConsumerConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class SampleScheduler {
    //5秒
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    private static final long FIVE_SECONDS = 5000;
    private static final Logger LOGGER = LoggerFactory.getLogger(SampleScheduler.class);

    @Scheduled(fixedRate = FIVE_SECONDS)
    public void scheduledTask() {

        LOGGER.info("scheduled Job The time is now {}", dateFormat.format(new Date()));
        ConsumerConfig<StudentRpcService> consumerConfig = new ConsumerConfig<StudentRpcService>()
                .setInterfaceId(StudentRpcService.class.getName()) // 指定接口
                .setProtocol("bolt") // 指定协议
                .setDirectUrl("bolt://127.0.0.1:12200") // 指定直连地址
                .setConnectTimeout(10 * 1000);

        StudentRpcService studentRpcService = consumerConfig.refer();

        LOGGER.info(studentRpcService.sayName());
    }
}
