package com.github.binarywang.wxpay.v3;


import org.apache.http.client.methods.HttpGet;

import java.net.URI;

public class WxPayV3DownloadHttpGet extends HttpGet {


  public WxPayV3DownloadHttpGet() {
  }

  public WxPayV3DownloadHttpGet(URI uri) {
    super(uri);
  }

  public WxPayV3DownloadHttpGet(String uri) {
    super(uri);
  }
}
