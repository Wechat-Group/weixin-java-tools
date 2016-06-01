weixin-java-tools

[![Build Status](https://travis-ci.org/chanjarster/weixin-java-tools.svg?branch=develop)](https://travis-ci.org/chanjarster/weixin-java-tools)
![Maven Central](https://img.shields.io/maven-central/v/me.chanjar/weixin-java-parent.svg)
[![Gitter](https://badges.gitter.im/Join%20Chat.svg)](https://gitter.im/chanjarster/weixin-java-tools?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge)

===========

* 群/聊天室：请点击上方的GITTER按钮
* 本项目的发布周期：本人子2015年6月开始进入到了一个非常忙碌的状态，且公司目前也没有开发微信相关的项目，因此本项目的维护基本靠“等”。所以靠人不如靠自己，提交pull request吧。

===========

微信公众号、企业号Java SDK。

详细文档请看 [wiki](https://github.com/chanjarster/weixin-java-tools/wiki)。

## Quick Start

如果要开发公众号（订阅号、服务号）应用，在你的maven项目中添加：

```xml
<dependency>
  <groupId>me.chanjar</groupId>
  <artifactId>weixin-java-mp</artifactId>
  <version>1.3.3</version>
</dependency>
```

如果要开发企业号应用，在你的maven项目中添加：

```xml
<dependency>
  <groupId>me.chanjar</groupId>
  <artifactId>weixin-java-cp</artifactId>
  <version>1.3.3</version>
</dependency>
```

## SNAPSHOT版

本项目的BUG修复和新特性一般会先发布在*-SNAPSHOT版里供大家预览，如果要使用*-SNAPSHOT版，则需要在你的pom.xml中添加这段：

```xml
<repositories>
  <repository>
      <snapshots />
      <id>sonatype snapshots</id>
      <url>https://oss.sonatype.org/content/repositories/snapshots/</url>
  </repository>
</repositories>
```

## 升级指南

* [1.0.3升级指南](https://github.com/chanjarster/weixin-java-tools/wiki/1_0_3升级指南)
* [1.1.0升级指南](https://github.com/chanjarster/weixin-java-tools/wiki/1_1_0升级指南)
* [1.1.1升级指南](https://github.com/chanjarster/weixin-java-tools/wiki/1_1_1升级指南)

## 关于Pull Request

非常欢迎和感谢对本项目发起Pull Request的同学，不过本项目基于[git flow](https://www.atlassian.com/git/tutorials/comparing-workflows/gitflow-workflow)开发流程，因此在发起Pull Request的时候请选择develop分支。

且本项目代码风格是用2个空格代表一个tab，因此在发起PR时注意一下，否则很容易发生在IDE格式化代码后与原代码产生大量diff，这样我在阅读PR的时候就很困难。
