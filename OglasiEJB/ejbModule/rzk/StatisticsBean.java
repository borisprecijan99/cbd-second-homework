package rzk;

import java.util.HashMap;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Oglas;

@Singleton
@Startup
public class StatisticsBean implements StatisticsBeanLocal {

	private HashMap<Integer, Integer> mapViews;
	private int respondToAdCounter;

	@PersistenceContext
	private EntityManager em;

	@Resource
	private TimerService timerService;

	@SuppressWarnings("unused")
	private Timer timer;

	public StatisticsBean() {
		mapViews = new HashMap<Integer, Integer>();
	}

	public HashMap<Integer, Integer> getMapViews() {
		return mapViews;
	}

	public void setMapViews(HashMap<Integer, Integer> mapViews) {
		this.mapViews = mapViews;
	}

	@Override
	public void increaseRespondToAdCounter() {
		respondToAdCounter = respondToAdCounter + 1;
	}

	@Override
	public void updateMapViews(Oglas ad) {
		if (mapViews.containsKey(ad.getId())) {
			mapViews.put(ad.getId(), mapViews.get(ad.getId()) + 1);
		} else {
			mapViews.put(ad.getId(), 1);
		}
	}

	@PostConstruct
	public void startTimer() {
		TimerConfig timerConfig = new TimerConfig();
		timerConfig.setPersistent(false);
		timer = timerService.createIntervalTimer(0, 15 * 60 * 1000, timerConfig);
	}

	@Schedule(persistent = false)
	private void printCounter() {
		System.out.println(this.getClass().getSimpleName() + ": Respond to ad counter at the end of the day is "
				+ respondToAdCounter);
		respondToAdCounter = 0;
	}

	@Timeout
	private void updateDatabase() {
		for (Entry<Integer, Integer> entry : mapViews.entrySet()) {
			Oglas ad = em.find(Oglas.class, entry.getKey());
			ad.setBrojPregleda(ad.getBrojPregleda() + entry.getValue());
			em.merge(ad);
		}
		mapViews.clear();
		System.out.println(this.getClass().getSimpleName() + ": Database updated");
	}
}
