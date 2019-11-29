package br.ufrn.imd.nbbfantasy.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.ufrn.imd.nbbfantasy.entity.Player;
import br.ufrn.imd.nbbfantasy.entity.User;
import br.ufrn.imd.nbbfantasy.service.PlayerService;
import br.ufrn.imd.nbbfantasy.service.StatsService;
import br.ufrn.imd.nbbfantasy.service.UserService;

@Controller
@RequestMapping("/player")
public class PlayerController {
	
	@Autowired
	PlayerService playerService;
	
	@Autowired
	StatsService statsService;
	
	@Autowired
	UserService userService;
	
	Player player;
	
	@GetMapping("/")
	public String root(
      Model model, 
      @RequestParam("page") Optional<Integer> page, 
      @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(12);
 
        Page<Player> playerPage = playerService.findPaginated(PageRequest.of(currentPage - 1, pageSize));
 
        model.addAttribute("playerPage", playerPage);
 
        int totalPages = playerPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                .boxed()
                .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
 
        return "player/index";
    }
	
	@GetMapping("/{id}")
    public String player(@PathVariable("id") Long id) {
		player = playerService.findById(id).get();
		double pontosPredicao = statsService.getPontosPredicao(player);
		double rebotesPredicao = statsService.getRebotesPredicao(player);
		double assistenciasPredicao = statsService.getAssistenciasPredicao(player);
		
		String points_link = statsService.getPointsLink(player);
		String rebounds_link = statsService.getReboundsLink(player);
		String assists_link = statsService.getAssistsLink(player);
		
		player.setPoints_link(points_link);
		player.setRebounds_link(rebounds_link);
		player.setAssists_link(assists_link);
		
		player.setPontosPredicao(pontosPredicao);
		player.setRebotesPredicao(rebotesPredicao);
		player.setAssistenciasPredicao(assistenciasPredicao);
		
        return "player/player";
    }
	
	@ModelAttribute("player")
    public Player player() {
        return player;
    }
	
	@RequestMapping("/favorite/{id}")
    public String favorite(@PathVariable("id") Long id) {
        if (id != null) {
        	Optional<Player> player = playerService.findById(id);
        	User usuario = userService.getUsuarioLogado();
        	usuario.getTeam().add(player.get());
        	userService.update(usuario);
        }
        return "redirect:/player/";
    }
	
	@RequestMapping("/unfavorite/{id}")
    public String unfavorite(@PathVariable("id") Long id) {
		if (id != null) {
			Optional<Player> player = playerService.findById(id);
        	User usuario = userService.getUsuarioLogado();
        	usuario.getTeam().remove(player.get());
        	userService.update(usuario);
		}
		return "redirect:/player/";
    }
}