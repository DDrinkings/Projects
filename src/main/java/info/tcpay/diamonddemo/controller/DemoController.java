package info.tcpay.diamonddemo.controller;

import info.tcpay.diamonddemo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("api")
public class DemoController {

  @Autowired OrderService orderService;

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
}
