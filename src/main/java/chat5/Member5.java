package chat5;

import chat4.Team4;

import javax.persistence.*;

@Entity
public class Member5 {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Member_SEQ_GENERATOR")
    private Long id;

    @Column(name = "name", nullable = false)
    private String username;

    //주인 (테이블의 FK가 있다면 그 객체가 주인이된다.)
    @ManyToOne //Member 입장에서는 many이면서 Team 입장에서는 one이기 때문
    @JoinColumn(name = "TEAM_ID") //연관관계 매핑
    private Team5 team;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Team5 getTeam() {
        return team;
    }

    public void setTeam(Team5 team) {
        this.team = team;
        team.getMembers().add(this);//this는 Member 자신이다.
    }

//    public void changeTeam(Team5 team) {
//        this.team = team;
//        team.getMembers().add(this);//this는 Member 자신이다.
//    }
}
