package br.ufrn.imd.nbbfantasy.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ufrn.imd.nbbfantasy.entity.Player;

@Repository
public interface PlayerDao extends JpaRepository<Player, Long> {

    Optional<Player> findByName(String name);

}
