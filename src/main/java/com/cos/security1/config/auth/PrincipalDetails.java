package com.cos.security1.config.auth;

// 시큐리티가 /login 주소 요청이 오면 낚아채서 로그인을 진행시킨다.
// 로그인 진행이 완료되면 시큐리티 session을 만들어 준다.(Security ContextHolder)
// 오브젝트 => Authentication 타입 객체만 session에 들어갈 수 있음
// Authentication 안에 User 정보가 있어야 된다
// User 오브젝트 타입 => UserDetails 타입 객체

// Security Session => Authentication => UserDetails(PrincipalDetails)

import com.cos.security1.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class PrincipalDetails implements UserDetails {

    private User user; // Composition

    public PrincipalDetails(User user) {
        this.user = user;
    }

    // 해당 User의 권한을 반환하는 곳
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collect = new ArrayList<>();
        collect.add((GrantedAuthority) () -> user.getRole());
        return collect;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {

        // 사이트에서 1년동안 회원이 로그인을 안하면 휴면 게정을 하기로 함
        // 필드로 Timestamp loginDate와 같이 만들고, 로그인시마다 이 값을 업데이트 시킴
        // user.getLoginDate()로 가져오고, (현재시간 - 로그인시간)이 1년을 초과하면 return false

        return true;
    }
}
