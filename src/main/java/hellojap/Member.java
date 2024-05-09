package hellojap;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity //JPA를 사용하는 애라고 인식시킴
public class Member {

    @Id//JPA한테 PK가 뭔지 알려줘야 함.
    private Long id;
    private String name;

    //ALT+ INSERT
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
