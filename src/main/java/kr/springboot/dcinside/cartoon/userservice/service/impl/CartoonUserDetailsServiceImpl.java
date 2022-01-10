package kr.springboot.dcinside.cartoon.userservice.service.impl;

import kr.springboot.dcinside.cartoon.userservice.domain.CartoonUserDetails;
import kr.springboot.dcinside.cartoon.userservice.repo.UserRepository;
import kr.springboot.dcinside.cartoon.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class CartoonUserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .map(CartoonUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("유저를 찾을수 없음."));
    }

}
