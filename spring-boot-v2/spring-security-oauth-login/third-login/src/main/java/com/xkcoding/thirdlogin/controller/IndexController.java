package com.xkcoding.thirdlogin.controller;

import com.xkcoding.thirdlogin.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.Objects;

/**
 * <p>
 * 首页控制器
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2019/10/15 13:55
 */
@Controller
public class IndexController {
    @GetMapping
    public String index(HttpSession session) {
        Object objectUser = session.getAttribute("user");
        if (Objects.nonNull(objectUser)) {
            User user = (User) objectUser;
            System.out.println(user.getUsername());
        }
        return "index";
    }
}
