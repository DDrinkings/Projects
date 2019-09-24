package info.tcpay.diamonddemo.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InResp implements Serializable {

  // 版本号
  private String version;

  private String tradeNo; // 商户单号
  private String orderNo; // otc单号
  private String amount; // 下单金额
  private String type; // 买卖方向
  private String quantity; // usdt数量
  private String apiKey; //
  private String platform; //
  private String status; // 订单状态
  private String amountCNY; // 实际支付金额
  private String payNotifyUrl; // 商家的回调接口地址
  private String userReceiveType; // 玩家收款方式(出金时)
  private String userReceiveName; // 玩家收款真名(出金时)
  private String userReceiveAccount; // 玩家收款账号(出金时)
  private String otcMerchantId; // otc币商id
  private String otcMerchantName; // otc币商名
  private String otcMerchantNickName; //
  private String otcMerchantPublicKey; // otc币商公钥
  private String otcReceiveType; // otc收款方式
  private String otcReceiveName; // otc收款真名
  private String otcReceiveAccount; // otc收款账号
  private String otcReceiveBankAddr; // otc收款银行(如果收款方式为银行卡)
  private String otcReceiveQrUrl; // otc收款二维码
  private String otcPrice; // 挂单价格
  private String otcOfferOrderNo; // 挂单单号
}
