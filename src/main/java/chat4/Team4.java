package chat4;

import chat2.Member;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

//@Entity
public class Team4 {
    @Id
    @GeneratedValue
    @Column(name="TEAM_ID")
    private Long id;
    private String name;

    public Team4() {
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
