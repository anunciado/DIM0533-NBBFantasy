package br.ufrn.imd.nbbfantasy.service.interfaces;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetailsService;

import br.ufrn.imd.nbbfantasy.command.UserRegistrationCommand;
import br.ufrn.imd.nbbfantasy.entity.User;

public interface IUserService extends UserDetailsService {

    Optional<User> findByEmail(String email);

    User save(UserRegistrationCommand registration);
}