package egov.web;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import egov.service.FileService;
import egov.service.FileVO;

@Controller
public class FileController {

		//파일을 받는 클래스--> 를 사용하기위해 Resource 작성
	@Resource(name = "multipartResolver")
	CommonsMultipartResolver multipartResolver;
	
	@Resource(name = "fileService")
	FileService fileService;
	
	
	@RequestMapping("fileboardWrite.do")
	public String fileboardWrite() {
		
		return "admin/fileWrite";
	}
	
	@RequestMapping("fileboardWriteSave.do")
	@ResponseBody
	public String insertFileboard( MultipartHttpServletRequest multiRequest, FileVO vo ) throws Exception {
		
	  Map<String, String> map1 = uploadProcess(multiRequest);

      vo.setFilename(map1.get("f1"));
      vo.setFilesize(map1.get("f2"));
      
      String result = fileService.insertFileboard(vo);
      
      
     String message= "ok";
      
     if(result != null) message = "error";
     //insert  처리하기
     
      
      // map.put("filename", filename);
     // map.put("cnt", Integer.toString(cnt));
      
      
      return message;
   }
	
	public static Map<String,String> uploadProcess( MultipartHttpServletRequest multiRequest) throws Exception{
	      MultipartFile file;
	      String uploadFile = "C:/Users/user/git/first-github/apple1/src/main/webapp/upload";
	      String fulldir  = "";
	      String filename = "";
	      String filesize = "";

	      int cnt = 0;
	      Map<String, String> map = new HashMap<String, String>();
	      
	      
	      File saveFolder = new File(uploadFile);	//물리적인 위치로 인식
	      if (!saveFolder.exists()) saveFolder.mkdirs();	//폴더가없으면  디렉토리 생성
	      
	      //multiRequest가  파일을 업로드 시키고 보관을 해줌 (파일 인식)
	      Map<String, MultipartFile> files = multiRequest.getFileMap();
	      	//files : 파일이름(오리지널,암호화된 이름), 파일크기, 파일종류
	      	
	      //다중
	      Iterator<Entry<String, MultipartFile>> itr = files.entrySet().iterator();
	      while (itr.hasNext()) {  //hasNext 는 존재여부
	         Entry<String, MultipartFile> entry = itr.next();
	         file = entry.getValue();
	         if (!"".equals(file.getOriginalFilename())) {
	            fulldir = uploadFile + "/" + file.getOriginalFilename();
	            file.transferTo(new File(fulldir));
	            filename += file.getOriginalFilename() + "／"; //여기 /는 특수문자임 ／  (구분하기위해 사용함)
	            filesize += file.getSize() + "／";
	            cnt++;
	         }
	      }
	      
	      Map<String,String> map1 = new HashMap<String,String>();
	      map1.put("f1", filename);
	      map1.put("f2", filesize);
	      
		return map1;
	}
	
	
	@RequestMapping("fileboardList.do")
	public String SelectFileList (FileVO vo,Model model)
											throws Exception{
		
		//출력페이지 번호 가져오기
		int page_no = 1;
		
		//출력페이지 번호를 이용하여 SQL의 출력 범위 설정
		int s_no = 1;
		int e_no = 10;
		
		//s_no 변수와 e_no 변수의 vo 세팅
		
		//목록 출력 서비스 실행
		List<?> list = fileService.SelectFileList(vo);
		
		//총 데이터 값 얻는  서비스 실행
		int total = fileService.selectFileboardTotal(vo);
		
		//총 페이지 값을 얻는 설정
		int total_page = 1;
		
		//출력 페이지의 시작 행번호 
		int rownum = 1; 
		
		model.addAttribute("vo",vo);
		model.addAttribute("list",list);
		
		
		return "admin/fileList";
	}
	
	
	
	
	
	
}
