package org.zerock.persistence;

import org.apache.ibatis.annotations.Delete;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.zerock.domain.PDS;

public interface PDSRepository extends CrudRepository<PDS, Long> {

	//수정
	@Modifying
	@Query("UPDATE FROM PDSFile f set f.fname = ?2 WHERE f.fno = ?1")
	public int upadatePDSFile(Long fno, String newFileName);
	
	//삭제
	@Modifying
	@Query("DELETE FROM PDSFile f WHERE f.fno = ?1")
	public int deletePDSFile(Long fno);
	
}
