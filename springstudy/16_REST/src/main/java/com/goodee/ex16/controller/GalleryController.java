package com.goodee.ex16.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.goodee.ex16.service.GalleryService;

@Controller
public class GalleryController {

	@Autowired
	private GalleryService galleryService;
	
	@GetMapping("/gallery/management")
	public String management() {
		return "gallery/manage";
	}
	
	@ResponseBody
	@PostMapping(value="/galleries", produces="application/json")     // xml처리해야 하는 경우 application/xml로 처리하면 된다.
	public Map<String, Object> addGallery(MultipartHttpServletRequest multipartRequest){
										  // DTO 사용이 가능하다면 @RequestBody GalleryDTO gallery를 사용 (MemberController 참고)
		return galleryService.save(multipartRequest);
	}
	
	@ResponseBody
	@GetMapping("/galleries/display")
	public ResponseEntity<byte[]> display(@RequestParam String path, @RequestParam String thumbnail){
		return galleryService.display(path, thumbnail);
	}
	
	
}