package info.tcpay.diamonddemo.service;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public class OutReq implements Serializable {

  // 版本号
  @NotEmpty private String version;

  @NotEmpty private String tradeNo; // 接入方单号
  @NotEmpty private String apiKey; //
  @NotEmpty private String type; // 买卖方向, 出金为固定值 sell
  @NotEmpty private String amount; // 金额
  @NotEmpty private String userId; // 接入方用户id
  @Deprecated @NotEmpty private String operSysType; // 用户终端操作系统类型
  @NotEmpty private String timeStamp; // 13位时间戳
  @NotEmpty private String platform; //
  @NotEmpty private String payNotifyUrl; // 回调通知地址
  @NotEmpty private String receiveType; // 收款方式
  @NotEmpty private String name; // 收款名
  @NotEmpty private String accountNo; // 收款账号
  @NotEmpty private String bcMerchantPublicKey; // BC商户的公钥
  @NotEmpty private String sign; // 签名

  /* 以下是可选参数, 不需要签名 */
  private String redDragonComment;
}
