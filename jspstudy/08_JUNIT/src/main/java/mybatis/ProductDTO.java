package mybatis;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder    // Builder 패턴을 이용해서 ProductDTO 생성
public class ProductDTO {

	private Long productNo;
	private String name;
	private Integer price;
	private String image;
	
}