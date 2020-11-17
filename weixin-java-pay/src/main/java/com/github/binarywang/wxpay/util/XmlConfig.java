package com.github.binarywang.wxpay.util;

public class XmlConfig {

  /**
   * 是否使用快速模式
   *
   * 如果设置为true，将会影响下面的方法，不再使用反射的方法来进行xml转换。
   * 1: BaseWxPayRequest的toXML方法
   * 2: BaseWxPayResult的fromXML方法
   * @see com.github.binarywang.wxpay.bean.request.BaseWxPayRequest#toXML
   * @see com.github.binarywang.wxpay.bean.result.BaseWxPayResult#fromXML
   *
   * 启用快速模式后，将能：
   * 1：性能提升约 10 ~ 15倍
   * 2：可以通过 graalvm 生成native image，大大减少系统开销(CPU,RAM)，加快应用启动速度(亚秒级)，加快系统部署速度（脱离JRE）.
   *
   * 参考测试案例: com.github.binarywang.wxpay.bean.result.WxPayRedpackQueryResultTest#benchmark
   * 参考网址: https://www.graalvm.org/
   */
  public static boolean fastMode = false;

}
