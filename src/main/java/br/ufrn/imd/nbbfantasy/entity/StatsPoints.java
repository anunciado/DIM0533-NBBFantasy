package br.ufrn.imd.nbbfantasy.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class StatsPoints {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private int two_pts_basket;

    private int minutes_played;

    private String name;

    private int games_played;

    private String team;

    private int free_throw_pts;

    private double points;

    private int three_pts_basket;

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getTwo_pts_basket ()
    {
        return two_pts_basket;
    }

    public void setTwo_pts_basket (int two_pts_basket)
    {
        this.two_pts_basket = two_pts_basket;
    }

    public int getMinutes_played ()
    {
        return minutes_played;
    }

    public void setMinutes_played (int minutes_played)
    {
        this.minutes_played = minutes_played;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public int getGames_played ()
    {
        return games_played;
    }

    public void setGames_played (int games_played)
    {
        this.games_played = games_played;
    }

    public String getTeam ()
    {
        return team;
    }

    public void setTeam (String team)
    {
        this.team = team;
    }

    public int getFree_throw_pts ()
    {
        return free_throw_pts;
    }

    public void setFree_throw_pts (int free_throw_pts)
    {
        this.free_throw_pts = free_throw_pts;
    }

    public double getPoints ()
    {
        return points;
    }

    public void setPoints (double points)
    {
        this.points = points;
    }

    public int getThree_pts_basket ()
    {
        return three_pts_basket;
    }

    public void setThree_pts_basket (int three_pts_basket)
    {
        this.three_pts_basket = three_pts_basket;
    }
}
