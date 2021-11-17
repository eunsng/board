package egov.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egov.service.FileService;
import egov.service.FileVO;

@Service("fileService")
public class FileImpl implements FileService {

	@Resource(name="fileDAO")
	FileDAO fileDAO;
	
	@Override
	public String insertFileboard(FileVO vo) throws Exception {
		return fileDAO.insertFileboard(vo);
	}

	@Override
	public List<?> SelectFileList(FileVO vo) throws Exception {
		return fileDAO.SelectFileList(vo);
	}

}
