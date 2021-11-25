package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the Oglas database table.
 * 
 */
@Entity
@NamedQuery(name="Oglas.findAll", query="SELECT o FROM Oglas o")
public class Oglas implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idOglas")
	private int id;

	private int brojPregleda;

	@Column(name="text")
	private String tekst;

	//bi-directional many-to-one association to Korisnik
	@ManyToOne
	@JoinColumn(name="idKorisnik")
	private Korisnik korisnik;

	//bi-directional many-to-one association to PrijavaNaOglas
	@OneToMany(mappedBy="oglas")
	private List<PrijavaNaOglas> prijaveNaOglas;

	public Oglas() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBrojPregleda() {
		return this.brojPregleda;
	}

	public void setBrojPregleda(int brojPregleda) {
		this.brojPregleda = brojPregleda;
	}

	public String getTekst() {
		return this.tekst;
	}

	public void setTekst(String tekst) {
		this.tekst = tekst;
	}

	public Korisnik getKorisnik() {
		return this.korisnik;
	}

	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}

	public List<PrijavaNaOglas> getPrijaveNaOglas() {
		return this.prijaveNaOglas;
	}

	public void setPrijaveNaOglas(List<PrijavaNaOglas> prijaveNaOglas) {
		this.prijaveNaOglas = prijaveNaOglas;
	}

	public PrijavaNaOglas addPrijaveNaOgla(PrijavaNaOglas prijaveNaOgla) {
		getPrijaveNaOglas().add(prijaveNaOgla);
		prijaveNaOgla.setOglas(this);

		return prijaveNaOgla;
	}

	public PrijavaNaOglas removePrijaveNaOgla(PrijavaNaOglas prijaveNaOgla) {
		getPrijaveNaOglas().remove(prijaveNaOgla);
		prijaveNaOgla.setOglas(null);

		return prijaveNaOgla;
	}

}