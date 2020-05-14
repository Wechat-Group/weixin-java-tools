package com.github.binarywang.wxpay.v3;

import java.io.IOException;
import org.apache.http.client.methods.HttpUriRequest;

public interface Credentials {

  String getSchema();

  String getToken(HttpUriRequest request) throws IOException;
}
