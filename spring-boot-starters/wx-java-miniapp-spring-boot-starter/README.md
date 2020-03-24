# 使用说明
1. 在自己的Spring Boot项目里，引入maven依赖
```xml
    <dependency>
        <groupId>com.github.binarywang</groupId>
        <artifactId>wx-java-miniapp-spring-boot-starter</artifactId>
        <version>${version}</version>
    </dependency>
 ```
2. 添加配置(application.yml)
```yml
wx:
  miniapp:
    appid: 111
    secret: 111
    token: 111
    aesKey: 111
    msgDataFormat: JSON
```







