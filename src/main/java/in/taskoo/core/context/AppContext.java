package in.taskoo.core.context;

import org.springframework.data.domain.Pageable;

public final class AppContext {
	public static ThreadLocal<String> api;
	public static ThreadLocal<Long> userId;
	public static ThreadLocal<Pageable> pageable;
	
	public static void clearContext() {
		api=null;
		userId=null;
	}
}
