package vip.fairy;

import org.apache.commons.compress.utils.IOUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.FileInputStream;

public class Demo1 {


    public static void main(String[] args) throws Exception {
        downloadFileFromHdfs();
    }

    static void uploadDemo1() throws Exception {
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS", "hdfs://172.27.113.123:9000");
        conf.set("fs.hdfs.impl", "org.apache.hadoop.hdfs.DistributedFileSystem");

        FileSystem fs = FileSystem.get(conf);

        String localFilePath = "D:/Soft/Apache/apache-activemq-5.18.2-bin.zip";
        // hdfs dfs -chmod 777 /user/huruiyi
        //使用 hdfs dfs -chown hurui:supergroup / 命令。
        String hdfsTargetPath = "/user/huruiyi/20231216-apache-activemq-5.18.2-bin.zip";
        //String hdfsTargetPath = "/user/huruiyi/";

        Path src = new Path(localFilePath);
        Path des = new Path(hdfsTargetPath);
        fs.copyFromLocalFile(src, des);

        fs.close();
    }

    static void uploadDemo2() throws Exception {
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS", "hdfs://172.27.113.123:9000");
        conf.set("fs.hdfs.impl", "org.apache.hadoop.hdfs.DistributedFileSystem");


        String localFilePath = "D:/Soft/Apache/apache-activemq-5.18.2-bin.zip";
        String hdfsTargetPath = "/user/huruiyi/apache-activemq-5.18.2-bin.zip";

        FileSystem fileSystem = FileSystem.get(conf);
        // 创建输入流
        FileInputStream fis = new FileInputStream(localFilePath);
        // 获取输入流，父目录不存在会自动创建
        FSDataOutputStream fos = fileSystem.create(new Path(hdfsTargetPath));
        // 流对拷 org.apache.commons.io.IOUtils
        IOUtils.copy(fis, fos);
        // 释放资源
        IOUtils.closeQuietly(fos);
        IOUtils.closeQuietly(fis);
        fileSystem.close();

    }

    static void downloadFileFromHdfs() throws Exception {
        // configuration
        Configuration conf = new Configuration();

        conf.set("fs.defaultFS", "hdfs://172.27.113.123:9000");
        conf.set("fs.hdfs.impl", "org.apache.hadoop.hdfs.DistributedFileSystem");
        FileSystem fileSystem = FileSystem.get(conf);

        // 下载文件
        fileSystem.copyToLocalFile(new Path("/user/huruiyi/apache-activemq-5.18.2-bin.zip"), new Path("D:/"));
        // 释放资源
        fileSystem.close();
    }
}
