package br.ufrn.imd.nbbfantasy.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Player {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private int allstar_appearances;
	private String birth_place;
	private String date;
	private String fullname; 
	private double height; 
	private String name;
	private String photo_src_link;
	private String player_page_link; 
	private String position; 
	private String team_name; 
	
	@Transient
	private double pontosPredicao;
	
	@Transient
	private double rebotesPredicao;
	
	@Transient
	private double assistenciasPredicao;
	
	@Transient
	private String points_link; 

	@Transient
	private String rebounds_link; 
	
	@Transient
	private String assists_link; 
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	private String team_page_link; 
	private double weight;
	
	public int getAllstar_appearances() {
		return allstar_appearances;
	}
	
	public void setAllstar_appearances(int allstar_appearances) {
		this.allstar_appearances = allstar_appearances;
	}
	
	public String getBirth_place() {
		return birth_place;
	}
	
	public void setBirth_place(String birth_place) {
		this.birth_place = birth_place;
	}
	
	public String getDate() {
		return date;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	
	public String getFullname() {
		return fullname;
	}
	
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	
	public double getHeight() {
		return height;
	}
	
	public void setHeight(double height) {
		this.height = height;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPhoto_src_link() {
		return photo_src_link;
	}
	
	public void setPhoto_src_link(String photo_src_link) {
		this.photo_src_link = photo_src_link;
	}
	
	public String getPlayer_page_link() {
		return player_page_link;
	}
	
	public void setPlayer_page_link(String player_page_link) {
		this.player_page_link = player_page_link;
	}
	
	public String getPosition() {
		return position;
	}
	
	public void setPosition(String position) {
		this.position = position;
	}
	
	public String getTeam_name() {
		return team_name;
	}
	
	public void setTeam_name(String team_name) {
		this.team_name = team_name;
	}
	
	public String getTeam_page_link() {
		return team_page_link;
	}
	
	public void setTeam_page_link(String team_page_link) {
		this.team_page_link = team_page_link;
	}
	
	public double getWeight() {
		return weight;
	}
	
	public void setWeight(double weight) {
		this.weight = weight;
	}
	
	public double getPontosPredicao() {
		return pontosPredicao;
	}

	public void setPontosPredicao(double pontosPredicao) {
		this.pontosPredicao = pontosPredicao;
	}

	public double getRebotesPredicao() {
		return rebotesPredicao;
	}

	public void setRebotesPredicao(double rebotesPredicao) {
		this.rebotesPredicao = rebotesPredicao;
	}

	public double getAssistenciasPredicao() {
		return assistenciasPredicao;
	}

	public void setAssistenciasPredicao(double assistenciasPredicao) {
		this.assistenciasPredicao = assistenciasPredicao;
	}
	
	public String getPoints_link() {
		return points_link;
	}

	public void setPoints_link(String points_link) {
		this.points_link = points_link;
	}

	public String getRebounds_link() {
		return rebounds_link;
	}

	public void setRebounds_link(String rebounds_link) {
		this.rebounds_link = rebounds_link;
	}

	public String getAssists_link() {
		return assists_link;
	}

	public void setAssists_link(String assists_link) {
		this.assists_link = assists_link;
	}

	@Override
	public String toString() {
		return "Player [allstar_appearances=" + allstar_appearances + ", birth_place=" + birth_place + ", date=" + date
				+ ", fullname=" + fullname + ", height=" + height + ", name=" + name + ", photo_src_link="
				+ photo_src_link + ", player_page_link=" + player_page_link + ", position=" + position + ", team_name="
				+ team_name + ", team_page_link=" + team_page_link + ", weight=" + weight + "]";
	}
}
