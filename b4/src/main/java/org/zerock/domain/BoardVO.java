package org.zerock.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Entity
@Table(name="tbl_board")
@Data
public class BoardVO {
	
	@Id //자동으로 만들어지기 때문에 제너레이트 밸류 준다
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="seq_board" )//어떤방법으로 줄것인가?
	@SequenceGenerator(name="seq_board", sequenceName="SEQ_BOARD")//시퀀스를 쓰면 시퀀스 제너레이터가 필요하다.
	private Long bno;
	
	@Column(length=300)
	private String title;
	@Lob
	private String content;
	private String writer;
	
	@CreationTimestamp
	private LocalDateTime regdate;
	@UpdateTimestamp
	private LocalDateTime updatedate;
	
}
