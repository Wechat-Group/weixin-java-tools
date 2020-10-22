package com.binarywang.spring.starter.wxjava.mp.properties;

import lombok.Data;

import java.io.Serializable;

@Data
public class HostConfig implements Serializable {

  private static final long serialVersionUID = -4172767630740346001L;

  private String apiHost;

  private String openHost;

  private String mpHost;

}
