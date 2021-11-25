package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the OglasKorisnik database table.
 * 
 */
@Entity
@Table(name="OglasKorisnik")
@NamedQuery(name="Korisnik.findAll", query="SELECT k FROM Korisnik k")
public class Korisnik implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idKorisnik")
	private int id;

	@Column(name="nickname")
	private String nadimak;

	@Column(name="password")
	private String lozinka;

	@Column(name="username")
	private String korisnickoIme;

	//bi-directional many-to-one association to Oglas
	@OneToMany(mappedBy="korisnik")
	private List<Oglas> oglasi;

	//bi-directional many-to-one association to PrijavaNaOglas
	@OneToMany(mappedBy="korisnik")
	private List<PrijavaNaOglas> prijaveNaOglas;

	public Korisnik() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNadimak() {
		return this.nadimak;
	}

	public void setNadimak(String nadimak) {
		this.nadimak = nadimak;
	}

	public String getLozinka() {
		return this.lozinka;
	}

	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}

	public String getKorisnickoIme() {
		return this.korisnickoIme;
	}

	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
	}

	public List<Oglas> getOglasi() {
		return this.oglasi;
	}

	public void setOglasi(List<Oglas> oglasi) {
		this.oglasi = oglasi;
	}

	public Oglas addOglasi(Oglas oglasi) {
		getOglasi().add(oglasi);
		oglasi.setKorisnik(this);

		return oglasi;
	}

	public Oglas removeOglasi(Oglas oglasi) {
		getOglasi().remove(oglasi);
		oglasi.setKorisnik(null);

		return oglasi;
	}

	public List<PrijavaNaOglas> getPrijaveNaOglas() {
		return this.prijaveNaOglas;
	}

	public void setPrijaveNaOglas(List<PrijavaNaOglas> prijaveNaOglas) {
		this.prijaveNaOglas = prijaveNaOglas;
	}

	public PrijavaNaOglas addPrijaveNaOgla(PrijavaNaOglas prijaveNaOgla) {
		getPrijaveNaOglas().add(prijaveNaOgla);
		prijaveNaOgla.setKorisnik(this);

		return prijaveNaOgla;
	}

	public PrijavaNaOglas removePrijaveNaOgla(PrijavaNaOglas prijaveNaOgla) {
		getPrijaveNaOglas().remove(prijaveNaOgla);
		prijaveNaOgla.setKorisnik(null);

		return prijaveNaOgla;
	}

}