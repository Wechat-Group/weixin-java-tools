package com.github.binarywang.wxpay.v3.auth;

public interface Verifier {
  boolean verify(String serialNumber, byte[] message, String signature);
}
