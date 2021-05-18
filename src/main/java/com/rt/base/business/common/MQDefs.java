package com.rt.base.business.common;

public class MQDefs {
    /**
     * 队列参数
     */
    public static class X {

    }

    /**
     * 交换器
     */
    public static class Exchange {

        public final static String EX_EXAMPLE = "base.ex.example";
        public final static String EX_FAIL = "base.ex.fail";
    }

    /**
     * 死信交换器
     */
    public static class DeadExchange {

        public final static String DLX_EXAMPLE = "base.dlx.example";
    }

    /**
     * 队列
     */
    public static class Queue {

        public final static String Q_EXAMPLE = "base.queue.example";
        public final static String Q_FAIL = "base.queue.fail";
    }

    /**
     * 路由KEY
     */
    public static class RoutingKey {

        public final static String RK_EXAMPLE = "base.k.example";
    }

    /**
     * 绑定KEY
     */
    public static class BindKey {

        public final static String BK_EXAMPLE = "base.k.example";
        public final static String BK_FAIL = "base.k.fail";
    }
}
