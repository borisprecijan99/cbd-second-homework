package rzk;

import javax.ejb.EJB;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class RespondToAdInterceptor {

	@EJB
	private StatisticsBeanLocal statistics;

	@AroundInvoke
	public Object interceptRespondToAdMethod(InvocationContext ctx) throws Exception {
		statistics.increaseRespondToAdCounter();
		return ctx.proceed();
	}
}
