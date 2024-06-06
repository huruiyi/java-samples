# 本人修改：SpringBootES6-->[原始项目:springboot-elasticsearch](https://gitee.com/11230595/springboot-elasticsearch.git)

#### 一、项目介绍

Springboot2.1.1+elasticsearch6.5.3搭建的企业级搜索平台，支持PB级数据（需要elasticsearch分布式部署），目前已经支持中文分词，检索关键词高亮操作，如果帮到您，麻烦点下Star，谢谢。

另外 Springboot2.1+Solr7.5
搭建的搜索引擎，已经支持文档搜索、数据库搜索、中文分词等。 [https://gitee.com/11230595/springboot-solr](https://gitee.com/11230595/springboot-solr)

#### 二、软件架构

1. Springboot2.1.1
2. elasticsearch6.5.3
3. spring-boot-starter-data-elasticsearch
4. analysis-ik 6.5.3

#### 三、配置教程

| Spring Data | Elasticsearch | Spring Boot | Project       | ES版本  | IK版本                                                                                                                     |
|-------------|---------------|-------------|---------------|-------|--------------------------------------------------------------------------------------------------------------------------|
| 3.2.x       | 6.8.4         | 2.2.x       | SpringBootES7 | 7.4.2 | [7.4.2](https://github.com/medcl/elasticsearch-analysis-ik/releases/download/v7.4.2/elasticsearch-analysis-ik-7.4.2.zip) |
| 3.1.x       | 6.2.2         | 2.1.x       | SpringBootES6 | 6.5.3 | [6.5.3](https://github.com/medcl/elasticsearch-analysis-ik/releases/download/v6.5.3/elasticsearch-analysis-ik-6.5.3.zip) |

1. elasticsearch6.5.3  <br/>
    - 下载 <br/>
      https://www.elastic.co/cn/downloads/elasticsearch <br/>
    - 配置<br/>
      解压后，打开 ```config/elasticsearch.yml```，对其中两项配置进行修改 <br/>
        - ```cluster.name```集群名称，随便填写，或者使用默认的“my-application”，注意，后面Java链接elasticsearch时，需要该配置。
        - ```network.host```如果此不配置此项，其他机器无法链接当前elasticsearch。配置为：（0.0.0.0代表任何IP都可访问）
        - 启动 <br/>
          Mac/Linux：运行 ```bin/elasticsearch```<br/>
          Windows：运行 ```bin\elasticsearch.bat```
2. analysis-ik 6.5.3 <br/>
    - 安装执行命令： <br/>
      ```bin/elasticsearch-plugin install https://github.com/medcl/elasticsearch-analysis-ik/releases/download/v6.5.3/elasticsearch-analysis-ik-6.5.3.zip```
3. analysis-ik 7.4.2 <br/>
    - 安装执行命令： <br/>
      ```bin/elasticsearch-plugin install https://github.com/medcl/elasticsearch-analysis-ik/releases/download/v7.4.2/elasticsearch-analysis-ik-7.4.2.zip```

#### 四、essearch配置说明

1. 修改```application.properties->spring.data.elasticsearch.cluster-nodes```  elasticsearch地址
2. 修改```application.properties->spring.data.elasticsearch.cluster-name``` 集群名称，和上面配置的相对应

#### 五、搜索接口返回数据截图

- 分页搜索接口 <br>
  ![image](https://images.gitee.com/uploads/images/2018/1218/110942_55dcc26e_499215.png) <br>
- 普通搜索接口 <br>
  ![image](https://images.gitee.com/uploads/images/2018/1214/223726_f913dbf0_499215.png)

#### 六、补充

1. 数据库数据同步，可自行安装插件。
2. 如需mq、接口方式同步数据，请查看项目中的save接口模块。

#### 七、QQ群：83402555

#### 八、关注公众号（公众号中有安装es的步骤和很多实用文章）

![image](https://images.gitee.com/uploads/images/2018/1210/122022_148f50d8_499215.jpeg)

#### 八，个人补充

	The well known TransportClient is deprecated as of Elasticsearch 7 and will be removed in Elasticsearch 8. (see the Elasticsearch documentation). Spring Data Elasticsearch will support the TransportClient as long as it is available in the used Elasticsearch version.

​

客户端初始化

```java
	@Bean
	RestHighLevelClient restHighLevelClient() {

		ClientConfiguration clientConfiguration = ClientConfiguration.builder().connectedToLocalhost().build();

		return RestClients.create(clientConfiguration).rest();
	}
```

