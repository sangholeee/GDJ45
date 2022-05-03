package domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Student {
	private Long stuNo;
	private String name;
	private Integer kor, eng, mat;
	private double avg;
	private String grade;

}
