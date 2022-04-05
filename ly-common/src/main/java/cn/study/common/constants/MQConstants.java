package cn.study.common.constants;

/**
 * @Author Meteor
 * @Date 2022/3/30 17:45
 * @Description rabbitMQ定义
 */
public abstract class MQConstants {

    public static final class ExchangeConstants{
        /**
         * 消息服务交换机名称
         */
        public static final String SMS_EXCHANGE_NAME = "ly.sms.exchange";

        /**
         * 商品下架的routing-key
         */
        public static final String VERIFY_CODE_KEY = "sms.verify.code";
    }

}
