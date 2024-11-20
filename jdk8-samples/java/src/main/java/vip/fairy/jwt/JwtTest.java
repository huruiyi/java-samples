package vip.fairy.jwt;

import com.alibaba.fastjson.JSON;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.Base64Codec;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.MacSigner;

class JwtTest {

  private static final String SECRET_KEY = "432543254365764432637695438328967568498513054390859236";

  @Test
  void test0() {
    //创建token
    Map<String, Object> claims = new HashMap<>();
    claims.put("username", "mike");
    claims.put("mobile", "15943569516");
    claims.put("expires_in", 60 * 1000); //设置过期时间，可用于过期校验
    MacSigner rsaSigner = new MacSigner(SECRET_KEY);
    Jwt encode = JwtHelper.encode(JSON.toJSONString(claims), rsaSigner);
    String token = encode.getEncoded();
    System.out.println(token);

    //解析token
    Jwt decode = JwtHelper.decode(token);
    System.out.println(decode.getClaims());
  }


  @Test
  void test1() {
    JwtBuilder jwtBuilder = Jwts.builder()
        .setId("666")
        .setSubject("Fox")
        .setIssuedAt(new Date())
        .setExpiration(new Date(System.currentTimeMillis() + 60 * 1000))
        // .addClaims(map)
        .claim("roles", "admin")
        .claim("logo", "xxx.jpg")
        .signWith(SignatureAlgorithm.HS256, SECRET_KEY);
    //获取token
    String token = jwtBuilder.compact();
    System.out.println(token);

    //三部分的base64解密
    System.out.println("=========");
    String[] split = token.split("\\.");
    System.out.println(Base64Codec.BASE64.decodeToString(split[0]));
    System.out.println(Base64Codec.BASE64.decodeToString(split[1]));
    //无法解密
    System.out.println(Base64Codec.BASE64.decodeToString(split[2]));
  }

  /**
   * 解析token
   */
  @Test
  void testParseToken() {
    //token
    String token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI2NjYiLCJzdWIiOiJGb3giLCJpYXQiOjE3MTgxMTQ0NjYsImV4cCI6MTcxODExNDUyNiwicm9sZXMiOiJhZG1pbiIsImxvZ28iOiJ4eHguanBnIn0.SbUBJyx9WlJTvciU58fQTsx61d52dmmDKpp0xRTc-Jg";
    //解析token获取载荷中的声明对象
    Claims claims = Jwts.parser()
        .setSigningKey(SECRET_KEY)
        .parseClaimsJws(token)
        .getBody();

    System.out.println("id:" + claims.getId());
    System.out.println("subject:" + claims.getSubject());
    System.out.println("issuedAt:" + claims.getIssuedAt());

    DateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    System.out.println("签发时间:" + sf.format(claims.getIssuedAt()));
    System.out.println("过期时间:" + sf.format(claims.getExpiration()));
    System.out.println("当前时间:" + sf.format(new Date()));

    System.out.println("roles:" + claims.get("roles"));
    System.out.println("logo:" + claims.get("logo"));
  }


}
