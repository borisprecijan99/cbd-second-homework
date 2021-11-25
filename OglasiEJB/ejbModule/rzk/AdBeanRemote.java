package rzk;

import java.util.List;

import javax.ejb.Remote;

import model.Oglas;

@Remote
public interface AdBeanRemote {
	boolean login(String username, String password);

	List<Oglas> searchAds(String text);

	boolean createAd(String text);

	boolean respondToAd(int id, String text);
}
