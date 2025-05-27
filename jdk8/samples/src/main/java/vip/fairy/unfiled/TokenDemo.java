package vip.fairy.unfiled;

import com.alibaba.fastjson.JSON;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.Test;

public class TokenDemo {

  static final String SECRET = "ee534sdfsdb53bcee534sdfsdb53bcb543vcbc35637vb534vxcv54xvxc567vxv543zbf46trsycx456ddgdgfg645dfgdb543vcbc35637vb534vxcv54xvxc567vxv543zbf46trsycx456ddgdgfg645dfgd";         // JWT密码

  @Test
  public void test() {
    Persion user = new Persion();
    user.setId(124L);
    user.setName("admin");
    String token = Jwts.builder()
        .setSubject(JSON.toJSONString(user))
        .setExpiration(new Date(System.currentTimeMillis() + 60 * 60 * 24 * 1000 * 365 * 100))
        .signWith(SignatureAlgorithm.HS512, SECRET) //采用什么算法是可以自己选择的，不一定非要采用HS512
        .compact();
    System.out.println(token);

    Claims body = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
    System.out.println(body);
  }

  @Test
  void splitTest() {
    String aa = "A;B;C;D";
    String bb = "A|B|C|D|E";
    splitQualifierValues(aa);
    splitQualifierValues(bb);
  }

  List<String> splitQualifierValues(String qualifierValue) {
    if (qualifierValue.contains(";")) {
      int length = qualifierValue.split(";").length;
      System.out.println(";" + length);
    } else if (qualifierValue.contains("|")) {
      int length = qualifierValue.split("\\|").length;
      System.out.println("|" + length);
    }
    return null;
  }

  public class Persion {

    public Long id;
    public String name;

    public Long getId() {
      return id;
    }

    public void setId(Long id) {
      this.id = id;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }
  }
}
