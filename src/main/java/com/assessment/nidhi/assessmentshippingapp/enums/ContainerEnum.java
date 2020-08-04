package com.assessment.nidhi.assessmentshippingapp.enums;

public enum ContainerEnum {
	AVAILABLE,
	WAITING_FOR_PICKUP,
	IN_TRANSIT,
	READY_TO_RETURN;
	
	public static String getValue(ContainerEnum containerEnum) {
		return containerEnum.name();
	}
}
