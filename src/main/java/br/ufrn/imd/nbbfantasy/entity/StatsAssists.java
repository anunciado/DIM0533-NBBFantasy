package br.ufrn.imd.nbbfantasy.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class StatsAssists {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private double assists;

    private int minutes_played;

    private String name;

    private int games_played;

    private int assists_per_err;

    private String team;

    private int assists_err;

    public double getAssists ()
    {
        return assists;
    }

    public void setAssists (double assists)
    {
        this.assists = assists;
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

    public int getAssists_per_err ()
    {
        return assists_per_err;
    }

    public void setAssists_per_err (int assists_per_err)
    {
        this.assists_per_err = assists_per_err;
    }

    public String getTeam ()
    {
        return team;
    }

    public void setTeam (String team)
    {
        this.team = team;
    }

    public int getAssists_err ()
    {
        return assists_err;
    }

    public void setAssists_err (int assists_err)
    {
        this.assists_err = assists_err;
    }
}
