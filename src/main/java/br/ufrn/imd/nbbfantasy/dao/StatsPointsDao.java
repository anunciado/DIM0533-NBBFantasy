package br.ufrn.imd.nbbfantasy.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ufrn.imd.nbbfantasy.entity.StatsPoints;

@Repository
public interface StatsPointsDao extends JpaRepository<StatsPoints, Long> {

	List<StatsPoints> findByName(String name);

}
