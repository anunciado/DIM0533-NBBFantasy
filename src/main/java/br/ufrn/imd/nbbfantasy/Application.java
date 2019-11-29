package br.ufrn.imd.nbbfantasy;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import br.ufrn.imd.nbbfantasy.dao.PlayerDao;
import br.ufrn.imd.nbbfantasy.dao.StatsAssistsDao;
import br.ufrn.imd.nbbfantasy.dao.StatsPointsDao;
import br.ufrn.imd.nbbfantasy.dao.StatsReboundsDao;
import br.ufrn.imd.nbbfantasy.entity.Json;
import br.ufrn.imd.nbbfantasy.entity.Json2;
import br.ufrn.imd.nbbfantasy.entity.Json3;
import br.ufrn.imd.nbbfantasy.entity.Json4;

@SpringBootApplication
public class Application {

	private static final Logger log = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}
	
	@Bean
	public CommandLineRunner run(RestTemplate restTemplate, PlayerDao playerDao, StatsPointsDao statsPointsDao, StatsReboundsDao statsReboundsDao, StatsAssistsDao statsAssistsDao) throws Exception {
		int[] weeks2019 = {1, 2, 3, 4, 5, 6, 7};
		int[] weeksAll = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36};
		int[] seasons = {11, 12};

		return args -> {
			Json json = restTemplate.getForObject("https://nbb-api.herokuapp.com/athletes", Json.class);
			json.getTeam_to_players().forEach(teams -> {
				teams.getTeam1().forEach(player -> playerDao.save(player));
				teams.getTeam2().forEach(player -> playerDao.save(player));
				teams.getTeam3().forEach(player -> playerDao.save(player));
				teams.getTeam4().forEach(player -> playerDao.save(player));
				teams.getTeam5().forEach(player -> playerDao.save(player));
				teams.getTeam6().forEach(player -> playerDao.save(player));
				teams.getTeam7().forEach(player -> playerDao.save(player));
				teams.getTeam8().forEach(player -> playerDao.save(player));
				teams.getTeam9().forEach(player -> playerDao.save(player));
				teams.getTeam10().forEach(player -> playerDao.save(player));
				teams.getTeam11().forEach(player -> playerDao.save(player));
				teams.getTeam12().forEach(player -> playerDao.save(player));
				teams.getTeam13().forEach(player -> playerDao.save(player));
				teams.getTeam14().forEach(player -> playerDao.save(player));
				teams.getTeam15().forEach(player -> playerDao.save(player));
				teams.getTeam16().forEach(player -> playerDao.save(player));
			});
			
			Arrays.stream(seasons).forEach(season -> {
				if(season == 12) {
					Arrays.stream(weeks2019).forEach(week -> {
						Json2 json2 = restTemplate.getForObject("https://nbb-api.herokuapp.com/points?week="+ week + "&season=" + season, Json2.class);
						json2.getPlayers().forEach(stats -> statsPointsDao.save(stats));
					});
				}
				else {
					Arrays.stream(weeksAll).forEach(week -> {
						Json2 json2 = restTemplate.getForObject("https://nbb-api.herokuapp.com/points?week="+ week + "&season=" + season, Json2.class);
						json2.getPlayers().forEach(stats -> statsPointsDao.save(stats));
					});
				}				
			});
			
			Arrays.stream(seasons).forEach(season -> {
				if(season == 12) {
					Arrays.stream(weeks2019).forEach(week -> {
						Json3 json3 = restTemplate.getForObject("https://nbb-api.herokuapp.com/rebounds?week="+ week + "&season=" + season, Json3.class);
						json3.getPlayers().forEach(stats -> statsReboundsDao.save(stats));
					});
				}
				else {
					Arrays.stream(weeksAll).forEach(week -> {
						Json3 json3 = restTemplate.getForObject("https://nbb-api.herokuapp.com/rebounds?week="+ week + "&season=" + season, Json3.class);
						json3.getPlayers().forEach(stats -> statsReboundsDao.save(stats));
					});
				}				
			});
			
			Arrays.stream(seasons).forEach(season -> {
				if(season == 12) {
					Arrays.stream(weeks2019).forEach(week -> {
						Json4 json4 = restTemplate.getForObject("https://nbb-api.herokuapp.com/assists?week="+ week + "&season=" + season, Json4.class);
						json4.getPlayers().forEach(stats -> statsAssistsDao.save(stats));
					});
				}
				else {
					Arrays.stream(weeksAll).forEach(week -> {
						Json4 json4 = restTemplate.getForObject("https://nbb-api.herokuapp.com/assists?week="+ week + "&season=" + season, Json4.class);
						json4.getPlayers().forEach(stats -> statsAssistsDao.save(stats));
					});
				}				
			});
			
			log.info("Players found with findAll():");
			log.info("-------------------------------");
			playerDao.findAll().forEach(System.out::println);
		
		};
	}

}
