package com.jmiranda.identity.infrastructure.security.service;

import com.jmiranda.identity.domain.auth.model.Login;
import com.jmiranda.identity.domain.auth.repository.LoginRepository;
import com.jmiranda.identity.domain.permission.model.Permission;
import com.jmiranda.identity.domain.role.model.Role;
import com.jmiranda.identity.domain.role.repository.RolePermissionRepository;
import com.jmiranda.identity.domain.user.model.HumanUser;
import com.jmiranda.identity.domain.user.repository.UserRepository;
import com.jmiranda.identity.domain.user.repository.UserRoleRepository;
import com.jmiranda.identity.infrastructure.permission.web.persistance.jpa.JpaRolePermissionRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final LoginRepository loginRepository;
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final RolePermissionRepository roleAuthorityRepository;

    public CustomUserDetailsService(
            LoginRepository loginRepository,
            UserRepository userRepository,
            UserRoleRepository userRoleRepository,
            RolePermissionRepository rolePermissionRepository
    ) {
        this.loginRepository = loginRepository;
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.roleAuthorityRepository = rolePermissionRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {


        Login login = loginRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));


        HumanUser user = userRepository.findById(login.getUserId())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));


        Optional<Set<Role>> roles = userRoleRepository.findRolesByUserId(user.getId());


        Set<String> authorities = roleAuthorityRepository.findAuthoritiesByRoleIds(
                roles.orElse(Set.of()).stream()
                        .map(Role::getId)
                        .collect(Collectors.toSet())
        );


        Set<GrantedAuthority> grantedAuthorities = authorities.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toSet());

        // User importado de Spring Security, no confundir con el dominio User
        return new User(
                login.getUsername().value(),
                login.getPasswordHash().value(),
                grantedAuthorities
        );
    }
}
