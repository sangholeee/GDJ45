package com.goodee.ex05.service;

import java.util.List;
import java.util.Map;

import com.goodee.ex05.domain.ProductDTO;

public interface ProductService {

	public List<ProductDTO> list1();             // 목록을 직접 반환
	public List<Map<String, Object>> list2();    // 목록을 직접 반환
	public Map<String, Object> list3();          // 객체 안에 특정 리스트를 담아서 반환
}
