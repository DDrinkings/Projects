package info.tcpay.diamonddemo.controller;

import info.tcpay.diamonddemo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("api")
public class DemoController {

  @Autowired OrderService orderService;

  @Value("${demo.baseUrl}")
  String baseUrl;
  /**
   * 入金接口
   *
   * @param amount
   * @param payType
   * @param modeType
   * @param comment
   * @param redComment
   * @return
   * @throws IOException
   * @throws NoSuchAlgorithmException
   */
  @RequestMapping("in")
  public String in(
      String amount, String payType, String modeType, String comment, String redComment)
      throws IOException, NoSuchAlgorithmException {
    return orderService.in(amount, payType, modeType, comment, redComment);
  }

  /**
   * 入金单查询接口, 依赖于下单接口, !!!相同apiKey+tradeNo不会重复下单
   *
   * @param apiKey
   * @param tradeNo
   * @param amount
   * @param payType
   * @param modeType
   * @param comment
   * @param redComment
   * @return
   * @throws IOException
   * @throws NoSuchAlgorithmException
   */
  @GetMapping("in/query")
  public String query(
      String apiKey,
      String tradeNo,
      String amount,
      String payType,
      String modeType,
      String comment,
      String redComment)
      throws IOException, NoSuchAlgorithmException {
    return orderService.query(apiKey, tradeNo, amount, payType, modeType, comment, redComment);
  }

  @GetMapping("in/redirect")
  public void redirect(HttpServletResponse response, String apiKey, String tradeNo)
      throws IOException {
    response.sendRedirect(
        String.format(
            "%sdiamond/html/buy_seventeen3.html?tradeNo=%s&apiKey=%s", baseUrl, tradeNo, apiKey));
  }
}
