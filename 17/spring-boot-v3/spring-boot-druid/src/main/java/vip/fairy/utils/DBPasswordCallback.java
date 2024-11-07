package vip.fairy.utils;

import com.alibaba.druid.filter.config.ConfigTools;
import com.alibaba.druid.util.DruidPasswordCallback;
import java.util.Properties;
import org.springframework.stereotype.Service;

/**
*需要继承DruidPasswordCallback  并重写setProperties 方法
**/
//@Service
public class DBPasswordCallback extends DruidPasswordCallback {

    //我使用的是RSA加密算法，所以讲公钥放在这的
    private final String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCsolr7//J3YqPkYGvkzL/+zoBbIDbKvYd1zm/mgHHKCHdP1oKA9L9BzfoRW6u3bf7lmUidS43eO0n7x34ThGY22NT5orwkFQ4+MuuSZve5XH+OxXVnLdWYw7WVqI0mCS52cNfQWBWjUNYAPGA+Zy0V8FHhlutBzNrb/b0YE2fCsQIDAQAB";
    @Override
    public void setProperties(Properties properties) {
        super.setProperties(properties);
        //获取配置文件中加密后的密码，和xml中的connectionProperties属性配置相关
        String password = (String) properties.get("password");
        try {
            //解密过程，ConfigTools为druid自带，提供一些好用的函数
            String dbpassword= ConfigTools.decrypt(publicKey,password);
            //设置密码
            setPassword(dbpassword.toCharArray());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
