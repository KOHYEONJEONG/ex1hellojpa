package chat5;

import chat2.Member;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Team5 {
    @Id
    @GeneratedValue
    @Column(name="TEAM_ID")
    private Long id;
    private String name;

    @OneToMany(mappedBy="team") // Member의 team이랑 연결되어 있다는 뜻(team으로 맵핑되어있다 뜻)
    private List<Member5> members = new ArrayList<>();

    public Team5() {
    }

    public List<Member5> getMembers() {
        return members;
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
