package hellojpa;

import javax.persistence.*;

@Entity //JPA를 사용하는 애라고 인식시킴
//@SequenceGenerator(name = "MEMBER_SEQ_GENERATOR",
//                sequenceName = "MEMBER_SEQ",
//                initialValue = 1, allocationSize = 50
//    )
public class Member {

    //@Id @GeneratedValue(strategy = GenerationType.AUTO)//JPA한테 PK가 뭔지 알려줘야 함.
    //@Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MEMBER_SEQ_GENERATOR")//JPA한테 PK가 뭔지 알려줘야 함.
    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;//LONG형 추천

    @Column(name = "name", nullable = false, columnDefinition = "varchar(100) default 'EMPTY'") //DB에서는 username 컬럼명이 아님 name명으로 사용하고 싶을때
    private String username;

    @Column(name = "TEAM_ID")
    private Long teamId;


}
