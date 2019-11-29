package br.ufrn.imd.nbbfantasy.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ufrn.imd.nbbfantasy.entity.StatsRebounds;

@Repository
public interface StatsReboundsDao extends JpaRepository<StatsRebounds, Long> {

	List<StatsRebounds> findByName(String name);

}
