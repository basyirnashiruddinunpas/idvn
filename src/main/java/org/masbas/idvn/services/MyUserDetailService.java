package org.masbas.idvn.services;

import java.util.ArrayList;
import java.util.List;

import org.masbas.idvn.models.CurrentUser;
import org.masbas.idvn.models.User;
import org.masbas.idvn.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class MyUserDetailService implements UserDetailsService {
	@Autowired
    private UserRepository userRepository;
    // 
    public UserDetails loadUserByUsername(String email)
      throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        
        if (user == null) {
            throw new UsernameNotFoundException(
              "No user found with username: "+ email);
        }

        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;
        
        CurrentUser currUser = new CurrentUser
                (user.getEmail(), 
                        user.getPassword(), enabled, accountNonExpired, 
                        credentialsNonExpired, accountNonLocked, 
                        getAuthorities(user.getRoles()));
        currUser.setUserModel(user);
        return  currUser;
    }
    
    private static List<GrantedAuthority> getAuthorities (List<String> list) {
        List<GrantedAuthority> authorities = new ArrayList();
        for (String role : list) {
            authorities.add(new SimpleGrantedAuthority(role));
        }
        return authorities;
    }
}
