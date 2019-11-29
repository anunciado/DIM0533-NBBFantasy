package br.ufrn.imd.nbbfantasy.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.ufrn.imd.nbbfantasy.dao.PlayerDao;
import br.ufrn.imd.nbbfantasy.entity.Player;

@Service
public class PlayerService {

    @Autowired
    private PlayerDao playerDao;
    
    public Page<Player> findPaginated(Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Player> players = findAll();
        List<Player> list;
 
        if (players.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, players.size());
            list = players.subList(startItem, toIndex);
        }
 
        Page<Player> bookPage = new PageImpl<Player>(list, PageRequest.of(currentPage, pageSize), players.size());
        return bookPage;
    }
    
    public List<Player> findAll() {
        return playerDao.findAll();
    }

    public Optional<Player> findById(Long id)  {
        return playerDao.findById(id);
    }

    public Player save(Player player) {
        return playerDao.save(player);
    }
    
    public void delete(Player entity) {
    	playerDao.delete(entity);
    }
    
    public void update(Player entity) {
    	playerDao.save(entity);
    }
}
