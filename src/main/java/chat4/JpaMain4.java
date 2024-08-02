package chat4;

import chat2.Team;
import chat3.Member3;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain4 {
    public static void main(String[] args) {

        //ctrl+alt+v : 변수 생성

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        try {
            Team4 team = new Team4();
            team.setName("TeamA");

            Team4 team2 = new Team4();
            team2.setName("TeamB");

            em.persist(team);//영속상태가 되면 무조건 pk 값이 세팅되고 영속상태가 된다.
            em.persist(team2);//영속상태가 되면 무조건 pk 값이 세팅되고 영속상태가 된다.

            Member4 member = new Member4();
            member.setUsername("member1");
            member.setTeam(team);

            em.persist(member);

            Member4 findMember=em.find(Member4.class, member.getId()); //영속성 컨텍스트 - 콘솔창에 select문이 없는 이유는, 1차 캐시에서 가져옴(만약에 캐시에서 안가져오고 싶다면, flush()와 clear()사용하자)

            //단방향 연관관계 매핑을 해뒀기 때문에, 바로 꺼내서 사용 가능하다! 다만 ㅅㄷ
            Team4 findTeam = findMember.getTeam();

            System.out.println("findTeam = "+findTeam.getName()); //findTeam = TeamA


            //수정도 가능하다
            Team4 newTeam = em.find(Team4.class, 2L);
            findMember.setTeam(newTeam);//ID:1을 가진 Member의 TEAM_ID는 1->2로 변경된다

            tx.commit();
        }catch (Exception e) {

        }
    }
}
