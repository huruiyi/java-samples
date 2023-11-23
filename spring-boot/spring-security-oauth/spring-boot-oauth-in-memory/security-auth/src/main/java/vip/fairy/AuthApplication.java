package vip.fairy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AuthApplication {

    /**
     * 1:
     * http://localhost:53020/uaa/login
     * 2:
     * http://localhost:53020/uaa/oauth/authorize?client_id=client_1&response_type=code&scope=all&redirect_uri=https://www.baidu.com
     */
    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
    }
}
