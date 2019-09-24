package info.tcpay.diamonddemo.service;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public class InReq implements Serializable {

  // 版本号
  @NotEmpty private String version;

  @NotEmpty private String tradeNo; // 商户单号
  @NotEmpty private String apiKey; //
  @NotEmpty private String type; // 买卖方向
  @NotEmpty private String amount; // 金额
  @NotEmpty private String userId; // 玩家id
  @NotEmpty @Deprecated private String operSysType; // 用户终端操作系统类型
  @NotEmpty private String timeStamp; // 13位时间戳
  @NotEmpty private String platform; //
  @NotEmpty private String payNotifyUrl; // 回调通知地址
  @NotEmpty private String bcMerchantPublicKey; // BC商户的公钥
  @NotEmpty private String sign; // 签名

  /* 以下是可选参数, 不需要签名 */
  private String payType; // 选择支付方式, 可选值["alipay", "wepay", "bank", ]
  private String modeType;
  private String comment;
  private String redComment;
  private Boolean pricePrint;

  public String getVersion() {
    return version;
  }

  public InReq setVersion(String version) {
    this.version = version;
    return this;
  }

  public String getTradeNo() {
    return tradeNo;
  }

  public InReq setTradeNo(String tradeNo) {
    this.tradeNo = tradeNo;
    return this;
  }

  public String getApiKey() {
    return apiKey;
  }

  public InReq setApiKey(String apiKey) {
    this.apiKey = apiKey;
    return this;
  }

  public String getType() {
    return type;
  }

  public InReq setType(String type) {
    this.type = type;
    return this;
  }

  public String getAmount() {
    return amount;
  }

  public InReq setAmount(String amount) {
    this.amount = amount;
    return this;
  }

  public String getUserId() {
    return userId;
  }

  public InReq setUserId(String userId) {
    this.userId = userId;
    return this;
  }

  public String getOperSysType() {
    return operSysType;
  }

  public InReq setOperSysType(String operSysType) {
    this.operSysType = operSysType;
    return this;
  }

  public String getTimeStamp() {
    return timeStamp;
  }

  public InReq setTimeStamp(String timeStamp) {
    this.timeStamp = timeStamp;
    return this;
  }

  public String getPlatform() {
    return platform;
  }

  public InReq setPlatform(String platform) {
    this.platform = platform;
    return this;
  }

  public String getPayNotifyUrl() {
    return payNotifyUrl;
  }

  public InReq setPayNotifyUrl(String payNotifyUrl) {
    this.payNotifyUrl = payNotifyUrl;
    return this;
  }

  public String getBcMerchantPublicKey() {
    return bcMerchantPublicKey;
  }

  public InReq setBcMerchantPublicKey(String bcMerchantPublicKey) {
    this.bcMerchantPublicKey = bcMerchantPublicKey;
    return this;
  }

  public String getSign() {
    return sign;
  }

  public InReq setSign(String sign) {
    this.sign = sign;
    return this;
  }

  public String getPayType() {
    return payType;
  }

  public InReq setPayType(String payType) {
    this.payType = payType;
    return this;
  }

  public String getModeType() {
    return modeType;
  }

  public InReq setModeType(String modeType) {
    this.modeType = modeType;
    return this;
  }

  public String getComment() {
    return comment;
  }

  public InReq setComment(String comment) {
    this.comment = comment;
    return this;
  }

  public String getRedComment() {
    return redComment;
  }

  public InReq setRedComment(String redComment) {
    this.redComment = redComment;
    return this;
  }

  public Boolean getPricePrint() {
    return pricePrint;
  }

  public InReq setPricePrint(Boolean pricePrint) {
    this.pricePrint = pricePrint;
    return this;
  }
}
