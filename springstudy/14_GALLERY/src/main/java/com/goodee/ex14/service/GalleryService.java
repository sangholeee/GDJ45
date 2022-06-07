package com.goodee.ex14.service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public interface GalleryService {
	
	// 갤러리 목록
	public void findGalleries(HttpServletRequest request, Model model);
	
	// 갤러리 상세 보기
	public void findGalleryByNo(HttpServletRequest request, Model model);
	public ResponseEntity<byte[]> display(Long fileAttachNo, String type);
	public ResponseEntity<Resource> download(String userAgent, Long fileAttachNo);
	
	// 갤러리 삽입
	// 파일첨부 시 enctype="multipart/form-data" -> httpservletRequest 못 쓴다, MultipartHttpServletRequest 사용해야 함.
	public void save(MultipartHttpServletRequest multipartRequest, HttpServletResponse response);
	public void save2(MultipartHttpServletRequest multipartRequest, HttpServletResponse response) throws IOException;   // 단일첨부
	
	// 갤러리 삭제
	public void removeGallery(HttpServletRequest request, HttpServletResponse response);
	
	// 갤러리 수정
	public void change(MultipartHttpServletRequest multipartRequest, HttpServletResponse response);
	
	// 첨부 파일 삭제
	public void removeFileAttach(Long fileAttachNo);
}