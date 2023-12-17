package vip.fairy;

import org.apache.commons.compress.utils.IOUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;

public class Demo1 {
    private static final Logger logger = LoggerFactory.getLogger(Demo1.class);

    public static void main(String[] args) throws Exception {
        uploadDemo1();
        uploadDemo2();
        downloadFileFromHdfs();
    }

    static FileSystem getFileSystem() throws IOException {
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS", "hdfs://172.27.113.123:9000");
        conf.set("fs.hdfs.impl", "org.apache.hadoop.hdfs.DistributedFileSystem");
        return FileSystem.get(conf);
    }

    static void uploadDemo1() throws IOException {
//Permission denied: user=hurui, access=WRITE, inode="/user/huruiyi":huruiyi:supergroup:drwxr-xr-x
        FileSystem fs = getFileSystem();
        String localFilePath = "D:/Soft/Apache/hornetq/hornetq-2.4.0.Final-bin.zip";
        // hdfs dfs -chmod 777 /user/huruiyi
        //使用 hdfs dfs -chown hurui:supergroup / 命令。
        //自定义上传后存储的文件名称
        //String hdfsTargetPath = "/user/huruiyi/20231216-apache-activemq-5.18.2-bin.zip";
        //自动命名上传后存储的文件名称
        String hdfsTargetPath = "/user/huruiyi/";

        Path src = new Path(localFilePath);
        Path des = new Path(hdfsTargetPath);
        fs.copyFromLocalFile(src, des);

        fs.close();

        logger.info("上传成功");
    }

    static void uploadDemo2() throws IOException {
        FileSystem fileSystem = getFileSystem();

        String localFilePath = "D:/Soft/Apache/activemq/apache-activemq-5.18.2-bin.zip";
        String hdfsTargetPath = "/user/huruiyi/apache-activemq-5.18.2-bin.zip";

        // 创建输入流
        try (FileInputStream fis = new FileInputStream(localFilePath)) {
            FSDataOutputStream fos = fileSystem.create(new Path(hdfsTargetPath));
            IOUtils.copy(fis, fos);
            IOUtils.closeQuietly(fos);
            IOUtils.closeQuietly(fis);
        }

        fileSystem.close();

        logger.info("上传成功");
    }

    /**
     * https://mirrors.tuna.tsinghua.edu.cn/apache/hadoop/common/hadoop-3.3.6/
     * windows下，下载hadoop相关依赖：https://github.com/cdarlint/winutils.git，选取指定版本，并设置 HADOOP_HOME，和 HADOOP_BIN_PATH(下载需要：winutils.exe)
     */
    static void downloadFileFromHdfs() throws Exception {
        FileSystem fileSystem = getFileSystem();
        // 下载文件
        fileSystem.copyToLocalFile(new Path("/user/huruiyi/apache-activemq-5.18.2-bin.zip"), new Path("D:/"));
        // 释放资源
        fileSystem.close();

        logger.info("下载成功");
    }
}
