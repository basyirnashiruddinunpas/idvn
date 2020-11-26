package org.masbas.idvn.models;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

public class CurrentUser extends org.springframework.security.core.userdetails.User {

	public CurrentUser(String username, String password, boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		// TODO Auto-generated constructor stub
	}
	
	public User getUserModel() {
		return userModel;
	}

	public void setUserModel(User userModel) {
		this.userModel = userModel;
	}

	private User userModel;
	

}
