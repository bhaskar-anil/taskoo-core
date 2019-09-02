package in.taskoo.core.context;

public final class AppContext {
	public static ThreadLocal<String> api;
	public static ThreadLocal<Long> userId;
	
	public static void clearContext() {
		api=null;
		userId=null;
	}
}
