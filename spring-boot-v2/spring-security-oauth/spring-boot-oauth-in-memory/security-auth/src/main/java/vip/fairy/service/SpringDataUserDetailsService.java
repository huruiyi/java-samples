package vip.fairy.service;

import com.alibaba.fastjson2.JSON;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import vip.fairy.dao.UserDao;
import vip.fairy.model.UserDto;

import java.util.List;

@Service
public class SpringDataUserDetailsService implements UserDetailsService {

  private final UserDao userDao;

  public SpringDataUserDetailsService(UserDao userDao) {
    this.userDao = userDao;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    UserDto userDto = userDao.getUserByUsername(username);
    if (userDto == null) {
      return null;
    }
    List<String> permissions = userDao.findPermissionsByUserId(userDto.getId());
    String[] permissionArray = new String[permissions.size()];
    permissions.toArray(permissionArray);
    String principal = JSON.toJSONString(userDto);
    return User.withUsername(principal).password(userDto.getPassword()).authorities(permissionArray).build();
  }
}
