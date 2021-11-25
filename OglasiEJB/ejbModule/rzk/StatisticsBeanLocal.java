package rzk;

import javax.ejb.Local;

import model.Oglas;

@Local
public interface StatisticsBeanLocal {
	void updateMapViews(Oglas ad);

	void increaseRespondToAdCounter();
}
