package chat1;

import javax.persistence.*;

//@Entity
@SequenceGenerator(name="MEMBER_SEQ_GENERATOR",
                    sequenceName = "MEMBER_SEQ",
                    initialValue = 1,   // initialValue : 기본값 1
                    allocationSize = 1) // allocationSize :기본값 50
//create sequence member_seq start with 1 increment by 50
public class MemberPK {

    //직접할당 @Id
    //자동생성 @GeneratedValue
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator ="MEMBER_SEQ_GENERATOR" )
    private Long id; //웬만하면 String < Integer < Long을 사용하자, 나중에 타입변경하는 게 더 힘들고, 요새 성능이 좋아서 Long 타입으로 해도 괜춘!

    private String name;

    public MemberPK() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
