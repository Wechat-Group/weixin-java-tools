## 全能微信Java开发工具包（SDK）
#### 支持包括微信支付、开放平台、公众号、企业微信/企业号、小程序等微信功能的后端开发。
---------------------------------
[![码云Gitee](https://gitee.com/binary/weixin-java-tools/badge/star.svg?theme=blue)](https://gitee.com/binary/weixin-java-tools) 
[![Github](https://img.shields.io/badge/Github-10k+-green.svg)](https://github.com/Wechat-Group/weixin-java-tools) 
[![Maven Central](https://img.shields.io/maven-central/v/com.github.binarywang/weixin-java-parent.svg)](http://mvnrepository.com/artifact/com.github.binarywang/weixin-java-parent)
[![Build Status](https://travis-ci.org/Wechat-Group/weixin-java-tools.svg?branch=develop)](https://travis-ci.org/Wechat-Group/weixin-java-tools)
[![使用IntelliJ IDEA开发维护](https://img.shields.io/badge/IntelliJ%20IDEA-提供支持-blue.svg)](https://www.jetbrains.com/idea/)

---------------------------------
### 重要信息

1. **2018-09-24 发布 [【3.2.0正式版】](https://github.com/Wechat-Group/weixin-java-tools/releases)**！
1. 新手重要提示：本项目仅是一个SDK开发工具包，未提供Web实现，建议使用maven或gradle引用本项目即可使用本SDK提供的各种功能，详情可参考 **[【Demo项目】](demo.md)** 或本项目中的部分单元测试代码；另外微信开发新手请务必阅读[【开发文档 Wiki 首页】](https://github.com/Wechat-Group/weixin-java-tools/wiki)的常见问题部分，可以少走很多弯路，节省不少时间。
1. [出现`Illegal key size`问题的解决办法](https://github.com/Wechat-Group/weixin-java-tools/wiki/%E5%8A%A0%E8%A7%A3%E5%AF%86%E7%9A%84%E5%BC%82%E5%B8%B8%E5%A4%84%E7%90%86%E5%8A%9E%E6%B3%95) （太多人遇到此问题而不知所措，因此特意置顶，希望能引起新手的注意，其他常见问题请查阅 [【开发文档Wiki】](https://github.com/wechat-group/weixin-java-tools/wiki)首页）
1. **更多精彩内容，请扫描以下二维码关注新开通的微信公众号【WX开发助手】，或者加入企业微信，或者[访问此页面扫码](http://www.binarywang.com/article/cp_and_mp) ，也可以在微信中搜索 `weixin-java-tools`或 `WX开发助手` 关注公众号，公众号会及时通知SDK相关更新信息，并不定期分享微信开发相关技术知识。**

![微信公众号及企业微信](qrcodes/cp_mp_qrcodes.png) 

--------------------------------
### 其他说明
1. 本项目Fork自chanjarster/weixin-java-tools，但由于原项目已停止维护，故单独维护和发布，且发布到maven上的groupId也会不同，详细信息见下文。
1. [开源中国本项目的首页](https://www.oschina.net/p/weixin-java-tools-new)，欢迎大家积极留言评分 🙂
1. SDK详细开发文档请查阅 [【开发文档Wiki】](https://github.com/wechat-group/weixin-java-tools/wiki)，部分文档可能未能及时更新，如有发现，可以及时上报或者自行修改。
1. **阅读源码的同学请注意，本SDK为简化代码编译时加入了lombok支持，如果不了解lombok的话，请先学习下相关知识，比如可以阅读[此文章](https://mp.weixin.qq.com/s/cUc-bUcprycADfNepnSwZQ)；**
1. 如有新功能需求，发现BUG，或者由于微信官方接口调整导致的代码问题，可以直接在[【Issues】](https://github.com/Wechat-Group/weixin-java-tools/issues)页提出issue，便于讨论追踪问题；
1. 如果想贡献代码，请阅读[【代码贡献指南】](contribution.md)；
1. **如果本SDK对您有所帮助，欢迎对我们的努力进行肯定，可以扫描[【支付宝付款码】](qrcodes/alipay_qrcode.jpg)或者[【微信支付二维码】](qrcodes/wepay_qrcode.jpg)进行打赏，或者直接前往[【托管于码云的项目首页】](http://gitee.com/binary/weixin-java-tools)，在评论区上方可以找到“捐助”按钮。非常感谢各位打赏和捐助的同学！**
1. 各个模块的Javadoc可以在线查看：[weixin-java-miniapp](http://binary.ac.cn/weixin-java-miniapp-javadoc/)、[weixin-java-pay](http://binary.ac.cn/weixin-java-pay-javadoc/)、[weixin-java-mp](http://binary.ac.cn/weixin-java-mp-javadoc/)、[weixin-java-common](http://binary.ac.cn/weixin-java-common-javadoc/)、[weixin-java-cp](http://binary.ac.cn/weixin-java-cp-javadoc/)、[weixin-java-open](http://binary.ac.cn/weixin-java-open-javadoc/)
1. 本SDK要求的最低JDK版本是1.7，还在使用JDK6的用户请参考[【此项目】]( https://github.com/binarywang/weixin-java-tools-for-jdk6) ，而其他更早的JDK版本则需要自己改造实现。
1. 本SDK项目在以下代码托管网站同步更新:
* 码云：https://gitee.com/binary/weixin-java-tools
* GitHub：https://github.com/wechat-group/weixin-java-tools
  
----------------------------------
### 使用案例
1. 开源项目：https://github.com/workcheng/weiya
1. 开源项目：https://github.com/jmdhappy/xxpay-master 
1. 微信点餐系统开源项目：http://www.sqmax.top/springboot-project/
1. 小程序：[喵星人贴吧助手(扫码关注)](http://p98ahz3tg.bkt.clouddn.com/miniappqrcode.jpg)
1. 平台：[小猪餐餐](http://www.xzcancan.com/)
1. 平台：[餐饮系统](http://canyin.daydao.com)
1. 公众号：中国电信上海网厅（sh_189）
1. 公众号：E答平台
1. 公众号：[全民约跑健身便利店](http://www.oneminsport.com/)
1. 公众号：[洽洽食品](https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=gQFM8TwAAAAAAAAAAS5odHRwOi8vd2VpeGluLnFxLmNvbS9xLzAycDRPOXBZbVZib2UxMDAwME0wN2gAAgRIu4RbAwQAAAAA)、[洽洽合伙人](https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=gQFP8jwAAAAAAAAAAS5odHRwOi8vd2VpeGluLnFxLmNvbS9xLzAyOUpJaU5VcXBlWTAxMDAwME0wN1oAAgSau4RbAwQAAAAA)
1. 公众号和小程序：民医台（可自行搜索）
1. 洽洽企业号
1. 高善人力资源
1. 其他更多案例请[【访问这里】](https://github.com/Wechat-Group/weixin-java-tools/issues/729)，持续更新中。

---------------------------------
### 技术交流方式
1. QQ群：（**注意：目前为付费群，刚入群会有5分钟禁言，稍等片刻即可正常发言**） [![加入QQ群](https://img.shields.io/badge/QQ群-343954419-blue.svg)](http://shang.qq.com/wpa/qunwpa?idkey=731dc3e7ea31ebe25376cc1a791445468612c63fd0e9e05399b088ec81fd9e15) 或 [![加入QQ群](https://img.shields.io/badge/QQ群-343954419-blue.svg)](http://jq.qq.com/?_wv=1027&k=40lRskK)，推荐点击按钮入群，当然如果无法成功操作，请自行搜索群号`343954419`进行添加；由于群容量有限，为了维持运营千人QQ群的所需支付的QQ年费会员费用，故开启付费入群模式，申请者只需支付少量金额即可加入，这样也可以保证只有真实交流需求的人进入，避免闲杂做广告人员的乱入；当然如果确实因为各种原因无法付费入群的，请联系群主说明原因即可入群；
1. 钉钉企业群：[请点击链接申请加入](https://h5.dingtalk.com/inviteColleague/index.html#/invite/9ed100cc4a/E1DF918E32E398D191E7FE61FE0552A6) 或者 [用手机钉钉APP扫码](qrcodes/ding_qrcode.jpg) 申请加入。
1. 微信群： 因微信群已达到100人限制，故如有想加入微信群的，可以 [扫码加此微信](qrcodes/wechat_qrcode.jpg) 以便邀请加入（请务必注明“申请加入微信开发群”，否则不予理睬，谢谢配合~）；
1. 新手提问前，请先阅读[【提问的智慧】](http://www.binarywang.com/article/smart-questions)，并确保已查阅过 [【开发文档Wiki】](https://github.com/wechat-group/weixin-java-tools/wiki)；
1. 寻求帮助时需贴代码或大长串异常信息的，请利用http://paste.ubuntu.com

---------------------------------
### Maven引用
注意：最新版本（包括测试版）为 [![Maven Central](https://img.shields.io/maven-central/v/com.github.binarywang/weixin-java-parent.svg)](http://mvnrepository.com/artifact/com.github.binarywang/weixin-java-parent)，以下为最新正式版。

```xml
<dependency>
  <groupId>com.github.binarywang</groupId>
  <artifactId>（不同模块参考下文）</artifactId>
  <version>3.2.0</version>
</dependency>
```
* 各模块的`artifactId`：
  - 微信小程序：`weixin-java-miniapp`   
  - 微信支付：`weixin-java-pay`
  - 微信开放平台：`weixin-java-open`   
  - 公众号：`weixin-java-mp`    
  - 企业号/企业微信：`weixin-java-cp`

---------------------------------
### 版本说明
1. 本项目定为大约每两个月发布一次正式版，版本号格式为X.X.0（如2.1.0，2.2.0等），遇到重大问题需修复会及时提交新版本，欢迎大家随时提交Pull Request；
1. BUG修复和新特性一般会先发布成小版本作为临时测试版本（如3.1.8.B，即尾号不为0，并添加B，以区别于正式版）；
1. 目前最新版本号为 [![Maven Central](https://img.shields.io/maven-central/v/com.github.binarywang/weixin-java-parent.svg)](http://mvnrepository.com/artifact/com.github.binarywang/weixin-java-parent) ，也可以通过访问链接 [【微信支付】](http://search.maven.org/#search%7Cgav%7C1%7Cg%3A%22com.github.binarywang%22%20AND%20a%3A%22weixin-java-pay%22) 、[【微信小程序】](http://search.maven.org/#search%7Cgav%7C1%7Cg%3A%22com.github.binarywang%22%20AND%20a%3A%22weixin-java-miniapp%22) 、[【公众号】](http://search.maven.org/#search%7Cgav%7C1%7Cg%3A%22com.github.binarywang%22%20AND%20a%3A%22weixin-java-mp%22) 、[【企业微信】](http://search.maven.org/#search%7Cgav%7C1%7Cg%3A%22com.github.binarywang%22%20AND%20a%3A%22weixin-java-cp%22)、[【开放平台】](http://search.maven.org/#search%7Cgav%7C1%7Cg%3A%22com.github.binarywang%22%20AND%20a%3A%22weixin-java-open%22)
分别查看所有最新的版本。 

----------------------------------
### 贡献者列表
特别感谢以下参与贡献的所有同学！
1. [chanjarster (Daniel Qian)](http://github.com/chanjarster)
1. [binarywang (Binary Wang)](http://github.com/binarywang)
1. [mgcnrx11](http://github.com/mgcnrx11)
1. [007gzs](http://github.com/007gzs)
1. [aimilin6688 (Jonk)](http://github.com/aimilin6688)
1. [kakotor](http://github.com/kakotor)
1. [kareanyi (MillerLin)](http://github.com/kareanyi)
1. [rememberber (周波)](http://github.com/rememberber)
1. [tianmu](http://github.com/tianmu)
1. [charmingoh (Charming)](http://github.com/charmingoh)
1. [ukid](http://github.com/ukid)
1. [forfuns (爱因斯唐)](http://github.com/forfuns)
1. [zxkane (Meng Xin Zhu)](http://github.com/zxkane)
1. [crskyp (我是木予)](http://github.com/crskyp)
1. [yuanqixun (yuanqixun)](http://github.com/yuanqixun)
1. [gaigeshen (gaigeshen)](http://github.com/gaigeshen)
1. [dylanleung (dylanleung)](http://github.com/dylanleung)
1. [huansinho](http://github.com/huansinho)
1. [codepiano (codepiano)](http://github.com/codepiano)
1. [stvliu (Steven Liu)](http://github.com/stvliu)
1. [ajffdnt](http://github.com/ajffdnt)
1. [fxdfxq (fxdfxq)](http://github.com/fxdfxq)
1. [unlimitedsola (Sola)](http://github.com/unlimitedsola)
1. [DDLeEHi](http://github.com/DDLeEHi)
1. [Hyseen](http://github.com/Hyseen)
1. [nickwongwong (Nick Wong)](http://github.com/nickwongwong)
1. [jink2005 (Jink2005)](http://github.com/jink2005)
1. [withinthefog (withinthefog)](http://github.com/withinthefog)
1. [iwareserictsai (Eric.Tsai)](http://github.com/iwareserictsai)
1. [lwxian](http://github.com/lwxian)
1. [xusheng1987 (flying)](http://github.com/xusheng1987)
1. [ZhaoxiongTan (xiong)](http://github.com/ZhaoxiongTan)
1. [SimonDolph (Simon Dolph)](http://github.com/SimonDolph)
1. [axeon](http://github.com/axeon)
1. [TonyLuo (Tony)](http://github.com/TonyLuo)
1. [dwandw (dwandw)](http://github.com/dwandw)
1. [alanchenup (alanchen)](http://github.com/alanchenup)
1. [zexpp5 (Lance7in)](http://github.com/zexpp5)
1. [xiaohulu (huluwa)](http://github.com/xiaohulu)
1. [aalx (devina)](http://github.com/aalx)
1. [rtsbtx (强哥)](http://github.com/rtsbtx)
1. [dracupid (Jingchen Zhao)](http://github.com/dracupid)
1. [lijunkun1988](http://github.com/lijunkun1988)
1. [lly835](http://github.com/lly835)
1. [mog0202 (蘑菇0202)](http://github.com/mog0202)
1. [bobbyguo (bobby_guo)](http://github.com/bobbyguo)
1. [huotaihe (白马度和)](http://github.com/huotaihe)
1. [dxwts (xuewu)](http://github.com/dxwts)
1. [aliangsoft (阿亮软件)](http://github.com/aliangsoft)
1. [Mkluas (Mklaus)](http://github.com/Mkluas)
1. [CodeIdeal (康阳)](http://github.com/CodeIdeal)
1. [leeis (IOMan)](http://github.com/leeis)
1. [lichenliang666 (李晨亮)](http://github.com/lichenliang666)
1. [627535195](http://github.com/627535195)
1. [ztmark (Mark)](http://github.com/ztmark)
1. [gtyang](http://github.com/gtyang)
1. [scott-z (scott)](http://github.com/scott-z)
1. [borisbao (Boris)](http://github.com/borisbao)
1. [qsjia (QSJia)](http://github.com/qsjia)
1. [webcreazy (webcreazy)](http://github.com/webcreazy)
1. [cwivan (鱼丸Cwivan)](http://github.com/cwivan)
1. [howardliu-cn (看山)](https://github.com/howardliu-cn)
