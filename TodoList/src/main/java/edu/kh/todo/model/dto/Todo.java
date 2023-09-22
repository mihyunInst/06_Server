package edu.kh.todo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor	// 기본 생성자
@AllArgsConstructor // 모든 필드에 대한 매개변수 생성자
@Getter
@Setter
@ToString
public class Todo {
	private int todoNo;
	private String todoTitle;
	private String todoMemo;
	private String todoDate;
	private String todoDeleteFlag;
}
