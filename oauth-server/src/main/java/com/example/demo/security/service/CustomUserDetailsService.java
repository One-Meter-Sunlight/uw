package com.example.demo.security.service;

import com.example.demo.client.UserClient;
import com.example.demo.constants.ResponseCodeConstants;
import com.example.demo.model.CustomResponseBody;
import com.example.demo.model.dto.security.CustomUser;
import com.example.demo.model.dto.user.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    private final UserClient userClient;

    @Autowired
    public CustomUserDetailsService(UserClient userClient) {
        this.userClient = userClient;
    }

    @Override
    public UserDetails loadUserByUsername(String _username) throws UsernameNotFoundException {

        CustomResponseBody<UserDto> response = userClient.loadUserByLoginName(_username);
        if (!response.getResultCode().equals(ResponseCodeConstants.OK)) {
            throw new UsernameNotFoundException(_username);
        }

        UserDto userDto = response.getContents();

        List<GrantedAuthority> authorities = new ArrayList<>();
        userDto.getAuthorities().forEach(
                authority -> authorities.add(new SimpleGrantedAuthority(authority.getAuthority()))
        );

        CustomUser customUser = new CustomUser(
                userDto.getId(),
                userDto.getLoginName(),
                userDto.getPassword(),
                true,
                true,
                true,
                true,
                authorities,
                userDto.getGrade());

        return customUser;
    }

}
