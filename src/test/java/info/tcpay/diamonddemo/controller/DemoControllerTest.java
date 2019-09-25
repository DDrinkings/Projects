package info.tcpay.diamonddemo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import info.tcpay.diamonddemo.DiamondDemoApplicationTests;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

@Slf4j
public class DemoControllerTest extends DiamondDemoApplicationTests {

  @Autowired DemoController demoController;

  /**
   * 入金测试用例
   *
   * @throws IOException
   * @throws NoSuchAlgorithmException
   */
  @Test
  public void in() throws IOException, NoSuchAlgorithmException {

    String in = demoController.in("0.01", "alipay", null, "comment", "redComment");

    JSONObject jsonObject = JSON.parseObject(in);
    Assert.assertEquals(200, jsonObject.get("code"));
    Assert.assertEquals("OK", jsonObject.get("msg"));
  }
}
