# 使用说明
1. 在自己的Spring Boot项目里，引入maven依赖
```xml
    <dependency>
        <groupId>com.github.binarywang</groupId>
        <artifactId>wx-java-pay-spring-boot-starter</artifactId>
        <version>${version}</version>
    </dependency>
 ```
2. 添加配置(application.yml)
###### 1）V2版本
```yml
wx:
  pay:
    appId: 
    mchId: 
    mchKey: 
    subAppId:
    subMchId:
    keyPath:
```
###### 2）V3版本
```yml
wx:
  pay:
    appId: xxxxxxxxxxx
    mchId: 15xxxxxxxxx #商户id
    apiV3Key: Dc1DBwSc094jACxxxxxxxxxxxxxxx #V3密钥
    certSerialNo: 62C6CEAA360BCxxxxxxxxxxxxxxx
    privateKeyPath: classpath:cert/apiclient_key.pem #apiclient_key.pem证书文件的绝对路径或者以classpath:开头的类路径
    privateCertPath: classpath:cert/apiclient_cert.pem #apiclient_cert.pem证书文件的绝对路径或者以classpath:开头的类路径
```







