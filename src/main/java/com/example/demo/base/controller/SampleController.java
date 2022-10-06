package com.example.demo.base.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.base.service.ICalendarService;
import com.example.demo.base.service.IGalleryService;
import com.example.demo.base.service.IMissionService;
import com.example.demo.base.service.IUserService;
import com.example.demo.base.vo.CalendarVo;
import com.example.demo.base.vo.GalleryVo;
import com.example.demo.base.vo.MissionVo;
import com.example.demo.base.vo.UserVo;

@RestController
public class SampleController {
	@Autowired
	ICalendarService ICalendarService;
	@Autowired
	IMissionService IMissionService;
	@Autowired
	IGalleryService IGalleryService;
	@Autowired
	IUserService IUserService;

	@GetMapping("/api/info")
	public String projectInfo() {
		return "Sample Project.";
	}
	
	// ------------ 회원가입 ------------
	@GetMapping("/api/userList")
	public List<UserVo> loginList() {
		List<UserVo> list = IUserService.listDao();
		return list;
	}
	
	@PostMapping("/api/userList2")
	public String userlistoverlapdel(@RequestBody UserVo request, Model model) {
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("id", request.getId());
		paramMap.put("password", request.getPassword());
		paramMap.put("name", request.getName());
		
		System.out.println(request.getId());
		int result = IUserService.writeDao(paramMap);
		
		String rstStr = "등록되었습니다.";
		if (result == 0) {
			rstStr = "실패하였습니다.";
		}
		return rstStr;
	}
	

	// ------------ 캘린더 ------------
	@GetMapping("/api/calendarList")
	public List<CalendarVo> calList() {
		List<CalendarVo> list2 = ICalendarService.list();
		return list2;
	}

	@PostMapping("/api/calendarList2")
	public String calendarlistoverlapdel(@RequestBody CalendarVo request, Model model) {
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("calDate", request.getCalDate());
		paramMap.put("calTitle", request.getCalTitle());
		paramMap.put("calContents", request.getCalContents());
		System.out.println(request.getCalDate());
		int result = ICalendarService.writeDao(paramMap);
		String rstStr = "추가되었습니다.";
		if (result == 0) {
			rstStr = "실패하였습니다.";
		}
		return rstStr;
	}
	
	@PostMapping("/api/calendarListDel/{Id}") // 이미지 삭제
	public String calDelete(@PathVariable Integer Id) {
		ICalendarService.delete(Id);
		
		return "redirect:/api/calendarList";
	}
	
	// ------------ 미션 ------------
	@GetMapping("/api/missionList")
	public List<MissionVo> mList() {
		List<MissionVo> list = IMissionService.list();
		return list;
	}
	
	@PostMapping("/api/missionList2")
	public String missionlistoverlapdel(@RequestBody MissionVo request, Model model) {
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("miTitle", request.getMiTitle());
		paramMap.put("miMember", request.getMiMember());
		System.out.println(request.getMiTitle());
		System.out.println(request.getMiMember());
		
		int result = IMissionService.writeDao(paramMap);
		String rstStr = "추가되었습니다.";
		if (result == 0) {
			rstStr = "실패하였습니다.";
		}
		return rstStr;
	}
	
	// 삭제
	@PostMapping("/api/missionListDel/{Id}")
	public String missionDelete(@PathVariable Integer Id) {
		IMissionService.delete(Id);
		
		return "redirect:/api/missionList";
	}
	
	// ------------ 갤러리 ------------
	@GetMapping("/api/galleryListGet/{file}") // 이미지 경로로 접근할 수 있도록 허용하는 코드
	  public ResponseEntity<Resource> display(@PathVariable(name="file") String file) {
		  String path = "C:\\togetherR\\img";
		  String folder = "\\";
		  
		  Resource resource = new FileSystemResource(path + folder + file);
		  if(!resource.exists()) 
				return new ResponseEntity<Resource>(HttpStatus.NOT_FOUND);
			HttpHeaders header = new HttpHeaders();
			Path filePath = null;
			try{
				filePath = Paths.get(path + folder + file);
				header.add("Content-type", Files.probeContentType(filePath));
			}catch(IOException e) {
				e.printStackTrace();
			}
			return new ResponseEntity<Resource>(resource, header, HttpStatus.OK);
	  }
	
	@GetMapping("/api/galleryList")
	public List<GalleryVo> list() {
		List<GalleryVo> list = IGalleryService.list();
		return list;
	}
	
	// 이미지 업로드
	@PostMapping("/api/galleryList")
	// file이라는 Key값을 가져오는 @RequestParam 찾아보기
	public String Gallery(@RequestParam("file") MultipartFile file, @RequestParam("title") String fileTitle, @RequestParam("content") String fileContent) {
		String uploadFolder = "C:\\togetherR\\img"; // 경로는 \\ 두개 표시한다.
		
		ZoneOffset seoulZoneOffset = ZoneOffset.of("+09:00");
		String currentout = ZonedDateTime.now(seoulZoneOffset).toString();
		System.out.println("Current Instant = "+ currentout);
		
		UUID uuid = UUID.randomUUID(); // 랜덤 id값 생성
		String fileUniqueName = uuid.toString(); // string형태로 변환
		String[] uuids = uuid.toString().split("-");
		String uniqueName = uuids[0]; // 첫번째 문자열만 가져오기
		 
		File saveImg = new File(uploadFolder + "\\" + uniqueName + ".jpg");
		System.out.println("생성된 고유문자열" + uniqueName);
		
		
		try {
			file.transferTo(saveImg); // 파일을 경로에 저장
		} catch(IllegalStateException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("fileUniqueName", uniqueName);
		paramMap.put("fileTitle", fileTitle);
		paramMap.put("fileContent", fileContent);
		paramMap.put("uploadFolder", uploadFolder);
		paramMap.put("uploadTime", currentout.substring(0, 19));
		
		int result = IGalleryService.writeDao(paramMap);
		  String rstStr = "추가되었습니다.";
		  if(result==0) {
			  rstStr = "실패하였습니다.";
		  }
		  
		  return uniqueName;
	}
	
	@PostMapping("/api/galleryListDel/{fileUniqueName}") // 이미지 삭제
	public String delete(@PathVariable String fileUniqueName, Map<String, String> map) {
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("fileUniqueName", fileUniqueName);
		
		IGalleryService.delete(paramMap);
		return "redirect:/api/galleryList";
	}
}