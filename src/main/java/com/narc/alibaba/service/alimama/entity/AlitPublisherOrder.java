package com.narc.alibaba.service.alimama.entity;

import java.math.BigDecimal;
import java.util.Date;
import javax.validation.constraints.*;
import lombok.Data;

/**
 * ALIT_PUBLISHER_ORDER
 * 
 * 
 * @author Narc
 * @date 2021-01-25
 */
@Data
public class AlitPublisherOrder {
    /**
     * TRADE_ID
     * 买家通过购物车购买的每个商品对应的订单编号，此订单编号并未在淘宝买家后台透出
     */
    @Size(max=64)
    @NotNull
    private String tradeId;

    /**
     * TRADE_PARENT_ID
     * 买家在淘宝后台显示的订单编号
     */
    @Size(max=64)
    private String tradeParentId;

    /**
     * ITEM_ID
     * 商品id
     */
    @Size(max=64)
    private String itemId;

    /**
     * ITEM_NUM
     * 商品数量
     */
    private Integer itemNum;

    /**
     * ITEM_TITLE
     * 商品标题
     */
    @Size(max=255)
    private String itemTitle;

    /**
     * TK_CREATE_TIME
     * 订单创建的时间，该时间同步淘宝，可能会略晚于买家在淘宝的订单创建时间
     */
    private Date tkCreateTime;

    /**
     * TB_PAID_TIME
     * 订单在淘宝拍下付款的时间
     */
    private Date tbPaidTime;

    /**
     * TOTAL_COMMISSION_RATE
     * 佣金比率
     */
    private BigDecimal totalCommissionRate;

    /**
     * ALIPAY_TOTAL_PRICE
     * 买家拍下付款的金额（不包含运费金额）
     */
    private BigDecimal alipayTotalPrice;

    /**
     * SUBSIDY_RATE
     * 平台给与的补贴比率，如天猫、淘宝、聚划算等
     */
    @Size(max=16)
    private String subsidyRate;

    /**
     * TK_TOTAL_RATE
     * 提成=收入比率*分成比率。指实际获得收益的比率
     */
    @Size(max=32)
    private String tkTotalRate;

    /**
     * PUB_SHARE_PRE_FEE
     * 付款预估收入=付款金额*提成。指买家付款金额为基数，预估您可能获得的收入。因买家退款等原因，可能与结算预估收入不一致
     */
    private BigDecimal pubSharePreFee;

    /**
     * ALIMAMA_RATE
     * 推广者赚取佣金后支付给阿里妈妈的技术服务费用的比率
     */
    private BigDecimal alimamaRate;

    /**
     * TK_STATUS
     * 3：订单结算，12：订单付款， 13：订单失效，14：订单成功
     */
    @Size(max=8)
    private String tkStatus;

    /**
     * ITEM_PRICE
     * 商品单价
     */
    private BigDecimal itemPrice;

    /**
     * SENDER_ID
     * 消息发送人ID
     */
    @Size(max=64)
    private String senderId;

    /**
     * SENDER_NAME
     * 消息发送人姓名
     */
    @Size(max=255)
    private String senderName;

    /**
     * DISCOUNT_RATE
     * 返现比例
     */
    private BigDecimal discountRate;

    /**
     * DISCOUNT_FEE
     * 返现费用
     */
    private BigDecimal discountFee;

    /**
     * IS_DONE
     * 是否已完成结算
     */
    @Size(max=1)
    private String isDone;
}