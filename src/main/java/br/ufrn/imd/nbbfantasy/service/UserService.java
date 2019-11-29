package br.ufrn.imd.nbbfantasy.service;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.ufrn.imd.nbbfantasy.command.UserRegistrationCommand;
import br.ufrn.imd.nbbfantasy.dao.UserDao;
import br.ufrn.imd.nbbfantasy.entity.Role;
import br.ufrn.imd.nbbfantasy.entity.User;
import br.ufrn.imd.nbbfantasy.service.interfaces.IUserService;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = userDao.findByEmail(email);
        if (user.isEmpty()){
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.get().getEmail(),
                user.get().getPassword(),
                mapRolesToAuthorities(user.get().getRoles()));
    }

    public Optional<User> findByEmail(String email){
        return userDao.findByEmail(email);
    }

    public User save(UserRegistrationCommand registration){
        User user = new User();
        user.setFirstName(registration.getFirstName());
        user.setLastName(registration.getLastName());
        user.setEmail(registration.getEmail());
        user.setPassword(passwordEncoder.encode(registration.getPassword()));
        user.setRoles(Set.of(new Role("ROLE_USER")));
        return userDao.save(user);
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }
    
    public User getUsuarioLogado() {
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String email = authentication.getName();
		Optional<User> usuario = findByEmail(email);
		return usuario.get();
    }

	public User update(User usuario) {
        return userDao.save(usuario);
	}
}
