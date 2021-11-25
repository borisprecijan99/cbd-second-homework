package rzk;

import java.util.List;

import javax.ejb.EJB;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import model.Oglas;

public class ViewsInterceptor {

	@EJB
	private StatisticsBeanLocal statistics;

	@AroundInvoke
	public Object interceptViews(InvocationContext ctx) throws Exception {
		@SuppressWarnings("unchecked")
		List<Oglas> ads = (List<Oglas>) ctx.getMethod().invoke(ctx.getTarget(), ctx.getParameters());
		for (Oglas ad : ads) {
			statistics.updateMapViews(ad);
		}
		return ctx.proceed();
	}
}
