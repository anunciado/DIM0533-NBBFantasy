package br.ufrn.imd.nbbfantasy.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import br.ufrn.imd.nbbfantasy.entity.Player;
import br.ufrn.imd.nbbfantasy.service.StatsService;
import br.ufrn.imd.nbbfantasy.service.UserService;

@Controller
@RequestMapping("/team")
public class TeamController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	StatsService statsService;
	
	@GetMapping("/")
    public String root() {
        return "team/index";
    }
	
	@ModelAttribute("books")
    public Set<Player> messages() {
        Set<Player> time = userService.getUsuarioLogado().getTeam();
        time.stream().forEach(player -> {
        	double pontosPredicao = statsService.getPontosPredicao(player);
    		double rebotesPredicao = statsService.getRebotesPredicao(player);
    		double assistenciasPredicao = statsService.getAssistenciasPredicao(player);    		
    		player.setPontosPredicao(pontosPredicao);
    		player.setRebotesPredicao(rebotesPredicao);
    		player.setAssistenciasPredicao(assistenciasPredicao);
        });    
        return time;
    }
}
