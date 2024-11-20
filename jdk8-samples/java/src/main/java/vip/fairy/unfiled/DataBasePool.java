package vip.fairy.unfiled;

import java.sql.Connection;
import org.junit.jupiter.api.Test;
import vip.fairy.dataSourcePool.MyDbPool;

public class DataBasePool {


  @Test
  public void CustomDataPool() {
    Connection connection = MyDbPool.getConnection();
    System.out.println(connection);
    MyDbPool.AddBack(connection);
    System.out.println("数据池的容量：" + MyDbPool.GetCapacity());
  }
}
