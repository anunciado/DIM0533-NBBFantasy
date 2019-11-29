package br.ufrn.imd.nbbfantasy.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class StatsRebounds {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private double rebounds;

    private int def_rebounds;

    private int minutes_played;

    private String name;

    private int games_played;

    private String team;

    private int off_rebounds;

    public double getRebounds ()
    {
        return rebounds;
    }

    public void setRebounds (double rebounds)
    {
        this.rebounds = rebounds;
    }

    public int getDef_rebounds ()
    {
        return def_rebounds;
    }

    public void setDef_rebounds (int def_rebounds)
    {
        this.def_rebounds = def_rebounds;
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

    public int getOff_rebounds ()
    {
        return off_rebounds;
    }

    public void setOff_rebounds (int off_rebounds)
    {
        this.off_rebounds = off_rebounds;
    }
}
