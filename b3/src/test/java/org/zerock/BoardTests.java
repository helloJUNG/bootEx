package org.zerock;

import java.util.stream.IntStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;
import org.zerock.domain.BoardVO;
import org.zerock.domain.QBoardVO;
import org.zerock.persistance.BoardRepsitory;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

import lombok.Setter;
import lombok.extern.java.Log;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log
public class BoardTests {

	@Setter(onMethod_ = @Autowired)
	private BoardRepsitory boardRepsitory;

	@Test
	public void testDynamic() {

		String[] types = { "t", "c" };
		String keyword = "10";

		BooleanBuilder builder = new BooleanBuilder(); // where 조건문에만 해당 , Order by에 해당 안됨.

		QBoardVO board = QBoardVO.boardVO;
		builder.and(board.bno.gt(0));

		BooleanExpression[] arr = new BooleanExpression[types.length];

		for (int i = 0; i < types.length; i++) {
			String type = types[i];

			BooleanExpression cond = null;
			if (type.equals("t")) {
				cond = board.title.contains(keyword);
			} else if (type.equals("c")) {
				cond = board.content.contains(keyword);
			}
			arr[i] = cond;
		}
		builder.andAnyOf(arr);

		Page<BoardVO> result = boardRepsitory.findAll(builder, PageRequest.of(0, 10, Sort.Direction.DESC, "bno"));

		log.info("" + result);
	}

	@Test
	public void testWriter() {

		Page<BoardVO> result = boardRepsitory.getListByWriter("zziggu1", PageRequest.of(0, 10));

		log.info("" + result);

		result.getContent().forEach(vo -> log.info("" + vo));

	}

	@Test
	public void testContent() {

		Page<BoardVO> result = boardRepsitory.getListByContent("test1", PageRequest.of(0, 10));

		log.info("" + result);

		result.getContent().forEach(vo -> log.info("" + vo));

	}

	@Test
	public void testTitle() {

		Page<BoardVO> result = boardRepsitory.getListByTitle("10", PageRequest.of(0, 10));

		log.info("" + result);

		result.getContent().forEach(vo -> log.info("" + vo));

	}

	@Test
	public void testList() {

		Page<BoardVO> result = boardRepsitory.getList(PageRequest.of(0, 10));

		log.info("" + result);

	}

	@Test
	public void testDelete() {

		boardRepsitory.deleteById(10L);

	}

	@Test
	public void testUpdate() {

		BoardVO vo = new BoardVO();
		vo.setBno(10L);
		vo.setTitle("제목 10 수정");
		vo.setContent("내용 10 수정");
		vo.setWriter("user10");

		boardRepsitory.save(vo);

	}

	@Test
	public void testRead() {

		boardRepsitory.findById(10L).ifPresent(vo -> log.info("" + vo));

	}

	@Test
	public void testInsert() {

		IntStream.range(501, 1000).forEach(i -> {

			BoardVO vo = new BoardVO();
			vo.setTitle("test" + i);
			vo.setContent("test1" + i);
			vo.setWriter("zziggu1" + (i % 10));

			boardRepsitory.save(vo);
		});

	}

}
