package org.zerock;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;
import org.zerock.domain.PDS;
import org.zerock.domain.PDSFile;
import org.zerock.persistence.PDSRepository;

import lombok.Setter;
import lombok.extern.java.Log;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log
@Commit
public class Test2 {
	
	@Setter(onMethod_=@Autowired)
	private PDSRepository pdsRepository;
	
	@Test
	public void testInsert() {
		//트랜잭션과 번호 따지는 것을 고려 해야된다.
		
		PDS vo = new PDS();
		vo.setTitle("Sample PDS");
		vo.setWriter("user00");
		
		List<PDSFile> list = IntStream.range(0, 3).mapToObj(i -> {
			
			PDSFile fileObj = new PDSFile();
			fileObj.setFname("파일이름" + i);
			
			return fileObj;
			
		}).collect(Collectors.toList());
		
		vo.setFiles(list);
		
		log.info("" + vo);
		
		pdsRepository.save(vo);
	}
	
	@Test
	public void testRead() {
		
		pdsRepository.findById(1L).ifPresent(vo -> log.info(""+vo));
		
	}
	
	@Transactional
	@Test
	public void testUpdateFile() {
		
		pdsRepository.upadatePDSFile(3L, "새로운 파일이름");
		
	}
	
	@Transactional
	@Test
	public void testDeleteFile() {
		
		pdsRepository.deletePDSFile(1L);
		
	}
	
	
	@Transactional
	@Test
	public void testOldway() {
		PDS pds = pdsRepository.findById(1L).get();
		
		List<PDSFile> files = pds.getFiles();
		
		log.info(""+files);
		
		PDSFile newFile = new PDSFile();
		newFile.setFno(2L);
		newFile.setFname("수정된파일");
		
		int idx = files.indexOf(newFile);
		
		log.info("idx: " + idx);

		if(idx >= 0) {
			files.remove(idx);
			files.add(newFile);
		}
		
		pds.setFiles(files);
		
		pdsRepository.save(pds);
		
	}
	
}
