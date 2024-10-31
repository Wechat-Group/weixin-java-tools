package cn.binarywang.wx.miniapp.bean.intractiy;

import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WxMaStoreBalance {
  private static final Logger logger = LoggerFactory.getLogger(WxMaStoreBalance.class);

  private String wxStoreId;
  private String appid;
  private int allBalance;

  private List<Detail> balanceDetail;

  public String getWxStoreId() {
    return wxStoreId;
  }

  public void setWxStoreId(String wxStoreId) {
    this.wxStoreId = wxStoreId;
  }

  public String getAppid() {
    return appid;
  }

  public void setAppid(String appid) {
    this.appid = appid;
  }

  public int getAllBalance() {
    return allBalance;
  }

  public void setAllBalance(int allBalance) {
    this.allBalance = allBalance;
  }

  public List<Detail> getBalanceDetail() {
    return balanceDetail;
  }

  public void setBalanceDetail(List<Detail> balanceDetail) {
    this.balanceDetail = balanceDetail;
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this);
  }

  public static class Detail {
    private String payorderId;
    private int chargeAmt;
    private int unusedAmt;
    private long beginTime;
    private long endTime;

    public String getPayorderId() {
      return payorderId;
    }

    public void setPayorderId(String payorderId) {
      this.payorderId = payorderId;
    }

    public int getChargeAmt() {
      return chargeAmt;
    }

    public void setChargeAmt(int chargeAmt) {
      this.chargeAmt = chargeAmt;
    }

    public int getUnusedAmt() {
      return unusedAmt;
    }

    public void setUnusedAmt(int unusedAmt) {
      this.unusedAmt = unusedAmt;
    }

    public Date getBeginDate() {
      return this.beginTime == 0 ? null : new Date(this.beginTime * 1000);
    }

    public Date getEndDate() {
      return this.endTime == 0 ? null : new Date(this.endTime * 1000);
    }

    public long getBeginTime() {
      return beginTime;
    }

    public void setBeginTime(long beginTime) {
      this.beginTime = beginTime;
    }

    public long getEndTime() {
      return endTime;
    }

    public void setEndTime(long endTime) {
      this.endTime = endTime;
    }

    @Override
    public String toString() {
      return ToStringBuilder.reflectionToString(this);
    }
  }
}
