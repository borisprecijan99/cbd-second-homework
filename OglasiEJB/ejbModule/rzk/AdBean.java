package rzk;

import java.util.List;

import javax.ejb.Stateful;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import model.Korisnik;
import model.Oglas;
import model.PrijavaNaOglas;

@Stateful
@Interceptors(value = StatefulInterceptor.class)
public class AdBean implements AdBeanRemote {

	private Korisnik user;

	@PersistenceContext
	private EntityManager em;

	public AdBean() {

	}

	@Override
	public boolean login(String username, String password) {
		TypedQuery<Korisnik> query = em.createQuery(
				"SELECT k FROM Korisnik k WHERE k.korisnickoIme=:username AND k.lozinka=:password", Korisnik.class);
		query.setParameter("username", username);
		query.setParameter("password", password);
		List<Korisnik> users = query.getResultList();
		try {
			user = users.get(0);
			System.out.println(this.getClass().getSimpleName() + ": Logged in user with id=" + user.getId());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	@Interceptors(value = ViewsInterceptor.class)
	public List<Oglas> searchAds(String text) {
		TypedQuery<Oglas> query = em
				.createQuery("SELECT o FROM Oglas o WHERE o.korisnik != :user AND o.tekst LIKE :text", Oglas.class);
		query.setParameter("text", text + "%");
		query.setParameter("user", user);
		return query.getResultList();
	}

	@Override
	public boolean createAd(String text) {
		if (text != null && !text.trim().isEmpty()) {
			try {
				Oglas ad = new Oglas();
				ad.setKorisnik(user);
				ad.setTekst(text);
				em.persist(ad);
				return true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	@Override
	@Interceptors(value = RespondToAdInterceptor.class)
	public boolean respondToAd(int id, String text) {
		if (text != null && !text.trim().isEmpty()) {
			PrijavaNaOglas respondToAd = new PrijavaNaOglas();
			respondToAd.setKorisnik(user);
			Oglas ad = em.find(Oglas.class, id);
			respondToAd.setOglas(ad);
			respondToAd.setTekst(text);
			em.persist(respondToAd);
			return true;
		}
		return false;
	}

}
