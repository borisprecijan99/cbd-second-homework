package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the OglasPrijava database table.
 * 
 */
@Entity
@Table(name="OglasPrijava")
@NamedQuery(name="PrijavaNaOglas.findAll", query="SELECT p FROM PrijavaNaOglas p")
public class PrijavaNaOglas implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idPrijava")
	private int id;

	@Column(name="text")
	private String tekst;

	//bi-directional many-to-one association to Oglas
	@ManyToOne
	@JoinColumn(name="idOglas")
	private Oglas oglas;

	//bi-directional many-to-one association to Korisnik
	@ManyToOne
	@JoinColumn(name="idKorisnik")
	private Korisnik korisnik;

	public PrijavaNaOglas() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTekst() {
		return this.tekst;
	}

	public void setTekst(String tekst) {
		this.tekst = tekst;
	}

	public Oglas getOglas() {
		return this.oglas;
	}

	public void setOglas(Oglas oglas) {
		this.oglas = oglas;
	}

	public Korisnik getKorisnik() {
		return this.korisnik;
	}

	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}

}