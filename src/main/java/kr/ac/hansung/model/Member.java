package kr.ac.hansung.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Member {
	private String id; //학번
	private String name; //이름
	private String phone; //연락처
	private String updateTime; //업데이트된 시각
	private String coinCount; //잡은 코인 개수
}
