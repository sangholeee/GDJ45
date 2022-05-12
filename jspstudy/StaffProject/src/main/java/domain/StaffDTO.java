package domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder

public class StaffDTO {
	
	private String sno;
	private String name;
	private String dept;
	private Long salary;

}
