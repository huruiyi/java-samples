package com.example.unfiled;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.CountDownLatch;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.apache.zookeeper.server.auth.DigestAuthenticationProvider;
import org.junit.jupiter.api.Test;

public class Test_Zookeeper {

  /*
   * 1:创建回话
   * 2:创建节点
   * 3:读取数据
   * 4:更新数据
   * 5:检测节点是否存在
   * 6:权限控制
   * 7:watch
   * */
  private final static String CONNECTIONStr = "192.168.70.133:2181";
  private static CountDownLatch countDownLatch = new CountDownLatch(1);


  @Test
  static void Demo1() throws NoSuchAlgorithmException {
    String digest = DigestAuthenticationProvider.generateDigest("admin:123456");
    System.out.println(digest);
  }

  @Test
  static void Demo2() throws IOException, InterruptedException, KeeperException {

    ZooKeeper zooKeeper = new ZooKeeper(CONNECTIONStr, 5000, new Watcher() {
      @Override
      public void process(WatchedEvent event) {
        if (event.getState().equals(Event.KeeperState.SyncConnected)) {
          countDownLatch.countDown();
          System.out.println(event.getState());
        }
        if (event.getType().equals(Event.EventType.NodeDataChanged)) {
          System.out.println("节点发生变化");
        }
      }
    });

    countDownLatch.await();

    Stat stat = new Stat();
    String path = "/country";
    //Watcher事件只会触发一次,每次要触发事件时都要重新设置监听:zooKeeper.getData(path, true, stat);
    byte[] data = zooKeeper.getData(path, true, stat);
    if (data.length == 0) {
      String res = zooKeeper.create(path, "china".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
      System.out.println(res);
    } else {
      Stat newStat = zooKeeper.setData(path, "世界你好".getBytes(), stat.getVersion());
      System.out.println(newStat);
    }

    byte[] data2 = zooKeeper.getData(path, true, null);
    System.out.println(new String(data2));

    // version: -1 忽略版本的变化
    zooKeeper.setData(path, "china 2017".getBytes(), -1);

    zooKeeper.getData(path, true, stat);
    zooKeeper.setData(path, "china 2018".getBytes(), -1);

    byte[] data1 = zooKeeper.getData(path, new Watcher() {
      @Override
      public void process(WatchedEvent event) {
        if (event.getType().equals(Event.EventType.NodeDataChanged)) {
          System.out.println("节点发生变化............");
        }
      }
    }, stat);
    zooKeeper.setData(path, "china 2019".getBytes(), -1);
    zooKeeper.setData(path, "china 2020".getBytes(), -1);

    System.out.println(new String(data1));
    zooKeeper.delete("/country", 0);
  }
}
