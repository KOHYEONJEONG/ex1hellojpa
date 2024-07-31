package chat3;

import javax.persistence.*;

@Entity
public class Member3 {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Member_SEQ_GENERATOR")
    private Long id;

    @Column(name = "name", nullable = false)
    private String username;

    @Column(name = "TEAM_ID")
    private Long teamId;

    public Member3() {
    }

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

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }
}
