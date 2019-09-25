package info.tcpay.diamonddemo.util;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.TreeMap;

public class SignUtil {

  public static final String md5(Object o) throws NoSuchAlgorithmException {
    return DatatypeConverter.printHexBinary(
        MessageDigest.getInstance("MD5").digest(String.valueOf(o).getBytes()));
  }

  private static final String getPreSignStr(Map data, String secret, String secretName) {
    Map<String, String> treeMap = new TreeMap(data);
    StringBuilder sb = new StringBuilder();
    treeMap.forEach(
        (k, v) -> {
          if (!"sign".equals(k) && null != v && !"".equals(v)) {
            sb.append("&" + k + "=" + v);
          }
        });
    sb.append("&" + secretName + "=" + secret);
    return sb.toString().substring(1);
  }

  public static final String sign(Map data, String secret, String secretName)
      throws NoSuchAlgorithmException {
    String preSignStr = getPreSignStr(data, secret, secretName);
    return md5(preSignStr);
  }
}
