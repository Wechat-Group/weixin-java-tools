package com.binarywang.spring.demo.wxjava.cp;

import com.binaywang.spring.starter.wxjava.cp.annotation.EnableWxCpTp;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author caiqy
 */
@EnableWxCpTp
@SpringBootApplication
public class CpApplication {

  public static void main(String[] args) {
    SpringApplication.run(CpApplication.class, args);
  }

}
