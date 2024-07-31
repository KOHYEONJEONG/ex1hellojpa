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

    @ManyToOne //Member 입장에서는 many이면서 Team 입장에서는 one이기 때문
    @JoinColumn(name = "TEAM_ID") //연관관계 매핑
    private Team4 team;


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

    public Team4 getTeam() {
        return team;
    }

    public void setTeam(Team4 team) {
        this.team = team;
    }
}
