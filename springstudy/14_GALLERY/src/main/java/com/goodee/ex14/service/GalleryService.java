package com.goodee.ex14.service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public interface GalleryService {
	public void findGalleries(HttpServletRequest request, Model model);
	public ResponseEntity<byte[]> display(Long fileAttachNo, String type);
	public void findGalleryByNo(HttpServletRequest request, Model model);
	public ResponseEntity<Resource> download(String userAgent, Long fileAttachNo);
	// 파일첨부는 httpservletRequest 못 쓴다.
	public void save(MultipartHttpServletRequest multipartRequest, HttpServletResponse response);
	public void save2(MultipartHttpServletRequest multipartRequest, HttpServletResponse response) throws IOException;   // 단일첨부
	public void change(MultipartHttpServletRequest multipartRequest, HttpServletResponse response);
	public void remove(MultipartHttpServletRequest multipartRequest, HttpServletResponse response);
}