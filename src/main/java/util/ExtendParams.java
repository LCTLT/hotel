package util;

public class ExtendParams {
	public ExtendParams(){}
	public ExtendParams(String sys_service_provider_id){
		this.sys_service_provider_id = sys_service_provider_id;
	}

	private static String sys_service_provider_id;

	public static String getSys_service_provider_id() {
		return sys_service_provider_id;
	}

}
