package com.ddmtchr.infosec.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private static final Map<String, String> users = new HashMap<>();

    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (users.isEmpty()) {
            fillUsersMap();
        }
        if (!users.containsKey(username)) {
            throw new UsernameNotFoundException(username);
        }

        return new User(username, users.get(username), List.of());
    }


    private void fillUsersMap() {
        users.put("admin", passwordEncoder.encode("123"));
        users.put("user", passwordEncoder.encode("456"));
    }
}
