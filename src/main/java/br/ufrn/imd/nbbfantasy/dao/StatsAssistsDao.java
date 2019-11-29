package br.ufrn.imd.nbbfantasy.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ufrn.imd.nbbfantasy.entity.StatsAssists;

@Repository
public interface StatsAssistsDao extends JpaRepository<StatsAssists, Long> {

	List<StatsAssists> findByName(String name);

}
