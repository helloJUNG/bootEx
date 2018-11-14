package org.zerock.domain;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity //Entity Class 설계
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_members")
@Builder
@Data
public class MemberVO {

	@Id
	@Column(length=50)  //아무것도  안주면 기본이 255
	private String mid;
	
	@NotNull
	@Column(length=200)
	private String mpw;
	@Column(length=100)
	private String mname;
	private String email;
	
	@CreationTimestamp
	private Timestamp regdate;
	@UpdateTimestamp
	private Timestamp updateDate;
	
}
