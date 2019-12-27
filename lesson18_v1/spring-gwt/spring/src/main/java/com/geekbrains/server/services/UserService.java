package com.geekbrains.server.services;

import com.geekbrains.gwt.common.UserDto;
import com.geekbrains.server.entities.Role;
import com.geekbrains.server.entities.User;
import com.geekbrains.server.repositories.RoleRepository;
import com.geekbrains.server.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder bcryptEncoder;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<User> getAllUsers() {
        return (List<User>)userRepository.findAll();
    }

    public User getUserById(Long userId) {
        return userRepository.findById(userId).get();
    }

    @Autowired
    public void setBcryptEncoder(PasswordEncoder bcryptEncoder) {
        this.bcryptEncoder = bcryptEncoder;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userlogin) throws UsernameNotFoundException {
        User user = userRepository.findOneByUserLogin(userlogin);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with userlogin: " + userlogin);
        }
        Collection<? extends GrantedAuthority> authorities = user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getRoleName())).collect(Collectors.toList());
        return new org.springframework.security.core.userdetails.User(user.getUserLogin(), user.getUserPassword(), authorities);
    }

    public User save(UserDto userDto) {
        User newUser = new User();
        newUser.setUserLogin(userDto.getUserLogin());
        newUser.setUserPassword(bcryptEncoder.encode(userDto.getUserPassword()));
        return userRepository.save(newUser);
    }
}
