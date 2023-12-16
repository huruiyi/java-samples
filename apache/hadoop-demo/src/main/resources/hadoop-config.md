- hadoop-env.sh 配置

```sh
export JAVA_HOME=/home/huruiyi/soft/jdk1.8.0_391
```

- hdfs-site.xml 配置

```xml
<configuration>
<property>
    <name>dfs.replication</name>
    <value>1</value>
</property>
</configuration>
```

- core-site.xml 配置

```xml
<configuration>
<property>
    <name>fs.defaultFS</name>
    <value>hdfs://localhost:9000</value>
</property>
<property>
    <name>hadoop.tmp.dir</name>
    <value>/home/huruiyi/soft/hadoop-3.3.6/data/temp</value>
</property>
</configuration>
```

- 1：Format the filesystem:

 ```shell
hdfs namenode -format
```

- 2：Start NameNode daemon and DataNode daemon:

 ```shell
sbin/start-dfs.sh

config:
HDFS_NAMENODE_USER=huruiyi
HDFS_DATANODE_USER=huruiyi
HDFS_SECONDARYNAMENODE_USER=huruiyi
```

- 3：Browse the web interface for the NameNode; by default it is available at:

 ```shell
http://localhost:9870/	
```

- 4：Make the HDFS directories required to execute MapReduce jobs:

 ```shell
hdfs dfs -mkdir -p /user/huruiyi
```

- 其他设置：

```yaml
export PDSH_RCMD_TYPE=ssh
export JAVA_HOME=/home/huruiyi/soft/jdk1.8.0_391
export PATH=$JAVA_HOME/bin:$PATH
export PDSH_RCMD_TYPE=ssh

source ~/.bashrc

pdsh -q -w localhost
sudo yum install pdsh


停止
start-dfs.sh

upload file
hadoop fs -put ./hadoop-3.3.6.tar.gz


dowload file
http://localhost:9870/webhdfs/v1/user/huruiyi/hadoop-3.3.6.tar.gz?op=OPEN

```







