package com.goodee.ex14.service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.goodee.ex14.domain.FileAttachDTO;
import com.goodee.ex14.domain.GalleryDTO;

public interface GalleryService {

	public void findGalleries(HttpServletRequest request, Model model);
	public FileAttachDTO findFileAttachByNo(Long fileAttachNo);
	public GalleryDTO findGalleryByNo(Long galleryNo);
	// 파일첨부는 httpservletRequest 못 쓴다.
	public void save(MultipartHttpServletRequest multipartRequest, HttpServletResponse response);
	public void save2(MultipartHttpServletRequest multipartRequest, HttpServletResponse response) throws IOException;   // 단일첨부
	public void change(MultipartHttpServletRequest multipartRequest, HttpServletResponse response);
	public void remove(MultipartHttpServletRequest multipartRequest, HttpServletResponse response);
	
}
