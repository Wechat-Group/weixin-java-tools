package com.binarywang.spring.starter.wxjava.mp.properties;

import java.io.Serializable;

import lombok.Data;

@Data
public class HostConfig implements Serializable {
	  
	private static final long serialVersionUID = -4172767630740346001L;

	private String apihost;
	
	private String openhost;
	
	private String mphost;
	  
}
