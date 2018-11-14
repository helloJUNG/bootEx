package org.zerock;

import java.util.Arrays;
import java.util.stream.IntStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;
import org.zerock.domain.BoardVO;
import org.zerock.persistance.BoardRepsitory;

import lombok.Setter;
import lombok.extern.java.Log;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log
public class BoardTests {

	@Setter(onMethod_=@Autowired)
	private BoardRepsitory boardRepsitory;
	
	
	@Test
	public void testQ1() {
		
		Pageable pageable = PageRequest.of(0,  10 , Sort.Direction.DESC,"bno");
		
		Page<BoardVO> result = boardRepsitory.getList(pageable);
		//Page<Object[]> result = boardRepsitory.getList(pageable);//특정한 배열 컬럼을 가져올때 object쓴다.
		
		log.info(""+result);
		
		log.info("TOTAL PAGES: " + result.getTotalPages());
		log.info("PAGE: " + result.getNumber());
		log.info("NEXT: " + result.hasNext());
		log.info("PREV: " + result.hasPrevious());
		
		log.info("P NEXT: " + result.nextPageable());
		log.info("P PREV: " + result.previousPageable());
		
		result.getContent().forEach(vo -> log.info(""+vo));
		//result.getContent().forEach(vo -> log.info(""+Arrays.toString(vo)));
		
	}
	
	@Test
	public void testFind3() {
		
		Pageable pageable = PageRequest.of(0, 5,Sort.Direction.DESC,"bno"); //bulidup pattern,  역순으로 정렬...

		boardRepsitory.findByBnoGreaterThan(0L, pageable).forEach(vo -> log.info(""+vo));;
	}
	
	@Test
	public void testFind2() {
		
		Pageable pageable = PageRequest.of(0, 5,Sort.Direction.DESC,"bno");
		
		boardRepsitory.findByTitleContainingAndBnoGreaterThan("7", 0L ,pageable).forEach(vo -> log.info(""+vo));
		
	}
	
	@Test
	public void testFind1() {
		
		Pageable pageable = PageRequest.of(0, 10,Sort.Direction.DESC,"bno"); //bulidup pattern,  역순으로 정렬...
		
		Page<BoardVO> result = boardRepsitory.findByBnoGreaterThan(0L, pageable);
		
		log.info(""+result);
		
		log.info("TOTAL PAGES: " + result.getTotalPages());
		log.info("PAGE: " + result.getNumber());
		log.info("NEXT: " + result.hasNext());
		log.info("PREV: " + result.hasPrevious());
		
		log.info("P NEXT: " + result.nextPageable());
		log.info("P PREV: " + result.previousPageable());
		
		result.getContent().forEach(vo -> log.info(""+vo));
	}
	
	@Test
	public void testDelete() {
		
		boardRepsitory.deleteById(10L);
		
	}
	
	@Test
	public void testUpdate() {
		
		BoardVO vo= new BoardVO();
		vo.setBno(10L);
		vo.setTitle("제목 10 수정");
		vo.setContent("내용 10 수정");
		vo.setWriter("user10");
		
		boardRepsitory.save(vo);
		
	}
	
	@Test
	public void testRead() {
	
		boardRepsitory.findById(10L).ifPresent(vo -> log.info(""+vo));
		
	}
	
	@Test
	public void testInsert() {
		
		IntStream.range(179, 500).forEach(i -> {
			
			BoardVO vo = new BoardVO();
			vo.setTitle("zziggu" + i);
			vo.setContent("test" + i);
			vo.setWriter("zziggu" + (i%10));
			
			boardRepsitory.save(vo);
		});
		
	}
	
}
