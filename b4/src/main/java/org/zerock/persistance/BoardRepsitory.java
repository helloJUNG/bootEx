package org.zerock.persistance;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.zerock.domain.BoardVO;

public interface BoardRepsitory extends CrudRepository<BoardVO, Long> {
	
	//대소문자 철저히 지켜야됨
	@Query("select b from BoardVO b where b.bno > 0")
	public Page<BoardVO> getList(Pageable pageable);

	public Page<BoardVO> findByBnoGreaterThan(Long bno, Pageable pageble);
	
//	public List<BoardVO> findByBnoGreaterThan(Long bno, Pageable pageble);
	
	public List<BoardVO> findByTitleContainingAndBnoGreaterThan(String keyword,Long bno,Pageable pageable); //타이틀의 타입도 여기서 결정.. like 대신 Containing쓴다.
	
}
