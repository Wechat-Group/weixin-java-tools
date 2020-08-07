package com.github.binarywang.wxpay.v3;

import java.io.IOException;

import org.apache.http.client.methods.HttpRequestWrapper;

public interface Credentials {

  String getSchema();

  String getToken(HttpRequestWrapper request) throws IOException;
}
