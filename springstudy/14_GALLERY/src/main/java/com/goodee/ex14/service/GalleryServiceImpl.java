package com.goodee.ex14.service;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.goodee.ex14.domain.FileAttachDTO;
import com.goodee.ex14.domain.GalleryDTO;
import com.goodee.ex14.mapper.GalleryMapper;
import com.goodee.ex14.util.MyFileUtils;
import com.goodee.ex14.util.PageUtils;

import net.coobird.thumbnailator.Thumbnails;

@Service
public class GalleryServiceImpl implements GalleryService {

	@Autowired
	private GalleryMapper galleryMapper;
	
	@Override
	public void findGalleries(HttpServletRequest request, Model model) {

		// page 파라미터
		Optional<String> opt = Optional.ofNullable(request.getParameter("page"));
		int page = Integer.parseInt(opt.orElse("1"));
		
		// 전체 레코드 개수
		int totalRecord = galleryMapper.selectGalleryCount();
		
		// PageEntity 계산
		PageUtils pageUtils = new PageUtils();
		pageUtils.setPageEntity(totalRecord, page);
		
		// beginRecord + endRecord => Map
		Map<String, Object> map = new HashMap<>();
		map.put("beginRecord", pageUtils.getBeginRecord());
		map.put("endRecord", pageUtils.getEndRecord());
		
		// beginRecord ~ endRecord 목록
		List<FileAttachDTO> galleries = galleryMapper.selectGalleryList(map);
		
		// list.jsp로 보낼 데이터
		model.addAttribute("totalRecord", totalRecord);
		model.addAttribute("galleries", galleries);
		model.addAttribute("beginNo", totalRecord - (page - 1) * pageUtils.getRecordPerPage());
		model.addAttribute("paging", pageUtils.getPaging(request.getContextPath() + "/gallery/list"));
		
	}

	@Override
	public GalleryDTO findGalleryByNo(Long galleryNo) {
		
		return null;
		
	}
	
	@Override
	public FileAttachDTO findFileAttachByNo(Long fileAttachNo) {
		
		return galleryMapper.selectFileAttachByNo(fileAttachNo);
	}
	

	@Transactional
	@Override
	public void save(MultipartHttpServletRequest multipartRequest, HttpServletResponse response) {
		
		// 전달된 파라미터
		String writer = multipartRequest.getParameter("writer");
		String title = multipartRequest.getParameter("title");
		String content = multipartRequest.getParameter("content");
		
		// IP
		Optional<String> opt = Optional.ofNullable(multipartRequest.getHeader("X-Forwarded-For"));
		String ip = opt.orElse(multipartRequest.getRemoteAddr());
		
		// GalleryDTO
		GalleryDTO gallery = GalleryDTO.builder()
				.writer(writer)
				.title(title)
				.content(content)
				.ip(ip)
				.build();
		
		// Gallery Insert 수행
		// System.out.println(gallery);       // INSERT 수행 전에는 gallery에 galleryNo값이 없다.
		
		int galleryResult = galleryMapper.insertGallery(gallery);  // INSERT 수행
		
		// System.out.println(gallery);       // INSERT 수행 후에는 selectKey에 의해서 gallery에 galleryNo값이 저장된다.
		
		// 결론. FILE_ATTACH 테이블에 INSERT할 galleryNo 정보는 gallery에 저장되어 있다.
		
		// 파일 첨부(다중 첨부이기 때문에 List 사용)
		
		int fileAttachResult = 0;
		
		
		// 첨부된 모든 파일들
		List<MultipartFile> files = multipartRequest.getFiles("files");   // 파라미터 files
		
		for (MultipartFile multipartFile : files) {
			
			// 예외 처리는 기본으로 필요함.
			try {
				
				// 첨부가 없을 수 있으므로 점검해야 함.
				if(multipartFile != null && multipartFile.isEmpty() == false) {  // 첨부가 있다.(둘 다 필요함)
					
					// 첨부파일의 본래 이름(origin)
					String origin = multipartFile.getOriginalFilename();
					origin = origin.substring(origin.lastIndexOf("\\") + 1);   // IE는 본래 이름에 전체 경로가 붙어서 파일명만 빼야 함.
					
					// 첨부파일의 저장된 이름(saved)
					String saved = MyFileUtils.getUuid(origin);
					
					// 첨부파일의 저장 경로(디렉터리)
					String path = MyFileUtils.getTodayPath();
					
					// 저장 경로(디렉터리) 없으면 만들기
					File dir = new File(path);
					if(dir.exists() == false) {
						dir.mkdirs();
					}
					
					// 첨부파일
					File file = new File(dir, saved);
					
					// 첨부파일 확인
					String contentType = Files.probeContentType(file.toPath());    // 이미지의 Content-Type(image/jpeg, image/png, image/gif)
					if(contentType.startsWith("image")) {
						
						// 첨부파일 서버에 저장(업로드)
						multipartFile.transferTo(file);
						
						// 썸네일 서버에 저장(썸네일 정보는 DB에 저장되지 않음)
						Thumbnails.of(file)
							.size(100, 100)
							.toFile(new File(dir, "s_" + saved));
						
						// FileAttachDTO
						FileAttachDTO fileAttach = FileAttachDTO.builder()
								.path(path)
								.origin(origin)
								.saved(saved)
								.gallery(new GalleryDTO(gallery.getGalleryNo(), null, null, null, null, null, null, null))
								.build();
						
						// FileAttach INSERT 수행
						fileAttachResult += galleryMapper.insertFileAttach(fileAttach);
						
						
					}
					
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		// 응답
		try {
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			if(galleryResult == 1 && fileAttachResult == files.size()) {
				out.println("<script>");
				out.println("alert('갤러리가 등록되었습니다.')");
				out.println("location.href='" + multipartRequest.getContextPath() + "/gallery/list'");
				out.println("</script>");
				out.close();
			} else {
				out.println("<script>");
				out.println("alert('갤러리가 등록되지 않았습니다.')");
				out.println("history.back()");
				out.println("</script>");
				out.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	
	}
	
	// 단일 첨부
	@Override
	public void save2(MultipartHttpServletRequest multipartRequest, HttpServletResponse response) throws IOException {
		
		/*
		// 전달된 파라미터
		String writer = multipartRequest.getParameter("writer");
		String title = multipartRequest.getParameter("title");
		String content = multipartRequest.getParameter("content");
		
		// IP
		Optional<String> opt = Optional.ofNullable(multipartRequest.getHeader("X-Forwarded-For"));
		String ip = opt.orElse(multipartRequest.getRemoteAddr());
		
		MultipartFile multipartFile = multipartRequest.getFile("files");
		
		if(multipartFile != null && multipartFile.isEmpty() == false) {  // 첨부가 있다.(둘 다 필요함)
			
			// 첨부파일의 본래 이름(origin)
			String origin = multipartFile.getOriginalFilename();
			origin = origin.substring(origin.lastIndexOf("\\") + 1);   // IE는 본래 이름에 전체 경로가 붙어서 파일명만 빼야 함.
			
			// 첨부파일의 저장된 이름(saved)
			String saved = MyFileUtils.getUuid(origin);
			
			// 첨부파일의 저장 경로(디렉터리)
			String path = MyFileUtils.getTodayPath();
			
			// 저장 경로(디렉터리) 없으면 만들기
			File dir = new File(path);
			if(dir.exists() == false) {
				dir.mkdirs();
			}
			
			// 첨부파일
			File file = new File(dir, saved);
			
			// 첨부파일 확인
			String contentType = Files.probeContentType(file.toPath());    // 이미지의 Content-Type(image/jpg, image/png, image/gif)
			if(contentType.startsWith("image")) {
				
				// 첨부파일 서버에 저장(업로드)
				multipartFile.transferTo(file);
				
				// 썸네일 서버에 저장(썸네일 정보는 DB에 저장되지 않음)
				Thumbnails.of(file)
					.size(100, 100)
					.toFile(new File(dir, "s_" + saved));
					
			
				
				
				
				}
					
			GalleryDTO gallery = GalleryDTO.builder()
					.writer(writer)
					.title(title)
					.content(content)
					.path(path)
					.origin(origin)
					.saved(saved)
					.ip(ip)
					.build();	
			int galleryResult = galleryMapper.insertGallery2(gallery);
			
				
			

		
		// 응답
		try {
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			if(galleryResult == 1) {
				out.println("<script>");
				out.println("alert('갤러리가 등록되었습니다.')");
				out.println("location.href='" + multipartRequest.getContextPath() + "/gallery/list'");
				out.println("</script>");
				out.close();
			} else {
				out.println("<script>");
				out.println("alert('갤러리가 등록되지 않았습니다.')");
				out.println("history.back()");
				out.println("</script>");
				out.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		}
		 */
	}
	
	@Override
	public void change(MultipartHttpServletRequest multipartRequest, HttpServletResponse response) {

	}

	@Override
	public void remove(MultipartHttpServletRequest multipartRequest, HttpServletResponse response) {

	}

}
