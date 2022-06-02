package com.goodee.ex14.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.goodee.ex14.domain.FileAttachDTO;
import com.goodee.ex14.domain.GalleryDTO;

@Mapper
public interface GalleryMapper {

	public int insertGallery(GalleryDTO gallery);
	public int insertFileAttach(FileAttachDTO fileAttach);
	
	// 단일첨부
	public int insertGallery2(GalleryDTO gallery);
	
	public int selectGalleryCount();
	public List<FileAttachDTO> selectGalleryList(Map<String, Object> map);		
	
	public FileAttachDTO selectFileAttachByNo(Long fileAttachNo);
	
}
