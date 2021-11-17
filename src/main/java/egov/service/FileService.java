package egov.service;

import java.util.List;

public interface FileService {
	/*
	 * 파일 저장처리
	 */
	String insertFileboard(FileVO vo) throws Exception;
	
	/*
	 * FileList 출력
	 */
	List<?> SelectFileList(FileVO vo) throws Exception;
}
