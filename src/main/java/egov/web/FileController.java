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
	      String uploadFile = "c:/upload3" , fulldir = "", filename="";
	      int cnt = 0;
	      Map<String, String> map = new HashMap<String, String>();
	      File saveFolder = new File(uploadFile);
	      if (!saveFolder.exists()) saveFolder.mkdirs();
	      
	      Map<String, MultipartFile> files = multiRequest.getFileMap();
	      
	      Iterator<Entry<String, MultipartFile>> itr = files.entrySet().iterator();
	      while (itr.hasNext()) {
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
