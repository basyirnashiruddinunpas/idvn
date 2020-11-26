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
    	System.out.println("EMAIL: " + email);
    	System.out.println("BEGIN");
        User user = userRepository.findByEmail(email);
        for (User ele : userRepository.findAll()) {
			System.out.println(ele.getEmail());
		}
        if (user == null) {
        	System.out.println("BEGIN2");
            throw new UsernameNotFoundException(
              "No user found with username: "+ email);
        }
    	System.out.println("BEGIN3");
        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;
        
        System.out.println("LOGIN SUCCESS HERE");
        
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
