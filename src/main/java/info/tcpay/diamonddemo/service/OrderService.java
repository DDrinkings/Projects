package info.tcpay.diamonddemo.service;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class OrderService {

  @Autowired OkHttpClient httpClient;

  @Value("${api.base.url}")
  String baseUrl;

  @Value("${api.base.platform}")
  String basePlatform;

  public String in(
      String amount,
      String apiKey,
      String secret,
      String merchantPublicKey,
      String payType,
      String modeType,
      String comment,
      String redComment)
      throws IOException {
    InReq req = new InReq();
    req.setVersion("0.0.1");
    req.setTradeNo("ZD_1001" + System.currentTimeMillis()); // 商户单号
    req.setApiKey(apiKey);
    req.setType("buy"); // 买卖方向
    req.setAmount(amount); // 金额
    req.setUserId(req.getTradeNo()); // 玩家id
    req.setOperSysType("Android"); // 用户终端操作系统类型
    req.setTimeStamp(System.currentTimeMillis() + ""); // 13位时间戳
    req.setPlatform(basePlatform); //
    req.setPayNotifyUrl(null); // 回调通知地址
    req.setBcMerchantPublicKey(merchantPublicKey); // BC商户的公钥
    // 不签名的参数
    req.setPayType(payType);
    req.setModeType(modeType);
    req.setComment(comment);
    req.setRedComment(redComment);
    req.setSign(sign(req, apiKey, secret)); // 签名

    RequestBody requestBody =
        MultipartBody.create(
            MediaType.parse(org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE),
            JSON.toJSONString(req));
    String r =
        httpClient
            .newCall(new Request.Builder().post(requestBody).url(baseUrl + "diamond/in").build())
            .execute()
            .body()
            .string();
    log.info("r=>{}", r);
    return r;
  }

  private String sign(InReq req, String apiKey, String secret) {
    return sign(
        req.getTradeNo(),
        apiKey,
        secret,
        req.getType(),
        req.getAmount(),
        req.getUserId(),
        req.getOperSysType(),
        req.getTimeStamp(),
        req.getPlatform(),
        req.getPayNotifyUrl(),
        req.getBcMerchantPublicKey());
  }

  private String sign(
      String tradeNo,
      String apiKey,
      String secret,
      String type,
      String amount,
      String userId,
      String operSysType,
      String timeStamp,
      String platform,
      String payNotifyUrl,
      String bcMerchantPublicKey) {
    Map<String, String> map = new HashMap();
    map.put("tradeNo", tradeNo);
    map.put("apiKey", apiKey);
    map.put("type", type);
    map.put("userId", userId);
    map.put("operSysType", operSysType);
    map.put("timeStamp", timeStamp);
    map.put("bcMerchantPublicKey", bcMerchantPublicKey);
    map.put("platform", platform);
    map.put("amount", amount);
    map.put("payNotifyUrl", payNotifyUrl);
    try {
      // todo, sign
    } catch (Exception e) {
      e.printStackTrace();
    }
    return map.get("sign");
  }
}
