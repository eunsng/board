package egov.web;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import egov.service.FileVO;

@Controller
public class FileController {

		//파일을 받는 클래스--> 를 사용하기위해 Resource 작성
	@Resource(name = "multipartResolver")
	CommonsMultipartResolver multipartResolver;
	
	
	@RequestMapping("fileboardWrite.do")
	public String fileboardWrite() {
		
		return "admin/fileWrite";
	}
	
	
	
	@RequestMapping("fileboardWriteSave.do")
	@ResponseBody
	public Map<String, String> insertFileboard(  MultipartHttpServletRequest multiRequest ) throws Exception {

      MultipartFile file;
      String uploadFile = "C:/Users/user/git/first-github/apple1/src/main/webapp/upload", fulldir = "", filename="";
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
            filename += file.getOriginalFilename() + "／";
            cnt++;
         }
      }
      map.put("filename", filename);
      map.put("cnt", Integer.toString(cnt));
      return map;
   }
	
	
	
	
	
	
	
}
