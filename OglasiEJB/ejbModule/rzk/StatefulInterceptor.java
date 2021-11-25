package rzk;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class StatefulInterceptor {

	@AroundInvoke
	public Object interceptStateful(InvocationContext ctx) throws Exception {
		System.out.println(this.getClass().getSimpleName() + ": Stateful bean method intercepted");
		return ctx.proceed();
	}

}
