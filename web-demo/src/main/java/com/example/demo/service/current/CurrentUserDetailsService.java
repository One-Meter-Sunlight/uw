package com.example.demo.service.current;

import com.example.demo.domain.CurrentUser;
import com.example.demo.domain.Role;
import com.example.demo.entity.User;
import com.example.demo.service.user.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Slf4j
@Service
public class CurrentUserDetailsService implements UserDetailsService {

    private final IUserService userService;

    @Autowired
    public CurrentUserDetailsService(IUserService userService) {
        this.userService = userService;
    }

    @Override
    public CurrentUser loadUserByUsername(String username) throws UsernameNotFoundException {
        log.debug("Authenticating user with username={}", username);
        User user = userService.getUserByUserName(username);
        if (user == null) {
            new UsernameNotFoundException(String.format("User with username=%s was not found", username));
        }
        return new CurrentUser(user);
    }

//    /**
//     *取得用户的权限
//     * @param user
//     * @return
//     */
//    private Set<GrantedAuthority> obtionGrantedAuthorities(User user) {
//        List<Role> roles=roleDAO.findRolesByLoginName(user.getUsername());
//        Set<GrantedAuthority> authSet = new HashSet<GrantedAuthority>();
//        for (Role res : roles) {
//            // 用户可以访问的资源名称（或者说用户所拥有的权限） 注意：必须"ROLE_"开头
//            authSet.add(new SimpleGrantedAuthority(res.getRoleName()));
//        }
//        return authSet;
//    }
}
