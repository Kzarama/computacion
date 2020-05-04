package com.TP.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.TP.demo.model.TsscAdmin;
import com.TP.demo.repositories.IAdminRepository;

@Service
public class MyCustomUserDetailsService implements UserDetailsService {

	@Autowired
	private IAdminRepository adminRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		TsscAdmin admin = adminRepository.findByUser(username).get(0);
		if (admin != null) {
			User.UserBuilder builder = User.withUsername(username).password(admin.getPassword())
					.roles(admin.getSuperAdmin());
			return builder.build();
		} else {
			throw new UsernameNotFoundException("User not found.");
		}
	}
}