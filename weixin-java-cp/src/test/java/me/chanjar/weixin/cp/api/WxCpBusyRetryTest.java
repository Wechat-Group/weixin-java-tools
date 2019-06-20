package me.chanjar.weixin.cp.api;

import me.chanjar.weixin.common.error.WxError;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.http.RequestExecutor;
import me.chanjar.weixin.cp.api.impl.WxCpServiceImpl;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Test
public class WxCpBusyRetryTest {

  @DataProvider(name = "getService")
  public Object[][] getService() {
    WxCpService service = new WxCpServiceImpl() {

      @Override
      public synchronized <T, E> T executeInternal(
        RequestExecutor<T, E> executor, String uri, E data)
        throws WxErrorException {
        this.log.info("Executed");
        throw new WxErrorException(WxError.builder().errorCode(-1).build());
      }
    };

    service.setMaxRetryTimes(3);
    service.setRetrySleepMillis(500);
    return new Object[][]{
      new Object[]{service}
    };
  }

  @Test(dataProvider = "getService", expectedExceptions = RuntimeException.class)
  public void testRetry(WxCpService service) throws WxErrorException {
    service.execute(null, null, null);
  }

  @Test(dataProvider = "getService")
  public void testRetryInThreadPool(final WxCpService service) throws InterruptedException, ExecutionException {
    // 当线程池中的线程复用的时候，还是能保证相同的重试次数
    ExecutorService executorService = Executors.newFixedThreadPool(1);
    Runnable runnable = new Runnable() {
      @Override
      public void run() {
        try {
          System.out.println("=====================");
          System.out.println(Thread.currentThread().getName() + ": testRetry");
          service.execute(null, null, null);
        } catch (WxErrorException e) {
          throw new RuntimeException(e);
        } catch (RuntimeException e) {
          // OK
        }
      }
    };
    Future<?> submit1 = executorService.submit(runnable);
    Future<?> submit2 = executorService.submit(runnable);

    submit1.get();
    submit2.get();
  }

}
