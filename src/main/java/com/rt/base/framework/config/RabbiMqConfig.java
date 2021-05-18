package com.rt.base.framework.config;

import com.rt.base.business.common.MQDefs;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

/**
 * @author wangq
 */
@Configuration
public class RabbiMqConfig {
//    /* ************************队列：tli.queue.notice -start- ******************** */
//    /**
//     * 通知
//     * @return 交换机
//     */
//    @Bean
//    Exchange exampleEx() {
//        return ExchangeBuilder
//                .directExchange(MQDefs.Exchange.EX_EXAMPLE)
//                .durable(true)
//                .build();
//    }
//    @Bean
//    public Queue exampleQ() {
//        return new Queue(MQDefs.Queue.Q_EXAMPLE, true, false, false, new HashMap<String, Object>());
//    }
//    @Bean
//    public Binding bindingExampleQ(
//            @Qualifier("exampleQ") Queue queue
//            , @Qualifier("exampleEx") Exchange ex) {
//        return BindingBuilder
//                .bind(queue)
//                .to(ex)
//                .with(MQDefs.BindKey.BK_EXAMPLE)
//                .noargs();
//    }
//    /* ************************队列：tli.queue.notice -end- ******************** */
}
