package chat3;

import chat2.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain3 {
    public static void main(String[] args) {

        //ctrl+alt+v : 변수 생성

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        try {
            Team team = new Team();
            team.setName("TeamA");

            em.persist(team);//영속상태가 되면 무조건 pk 값이 세팅되고 영속상태가 된다.

            Member3 member3 = new Member3();
            member3.setUsername("member1");

            member3.setTeamId(team.getId());//등록된 Team의 Id를 넣어주자.

            em.persist(member3);

            /**
             * 객체를 테이블에 맞추어 데이터 중심으로 모델링하면
             * 협력 관계를 만들 수 없다.
             *  - 테이블은 외래키로 조인을 사용해서 연관된 테이블을 찾는다.
             *  - 객체는 참조를 사용해서 연관된 객체를 찾는다.
             *  - 테이블과 객체 사이에는 이런 큰 간격이 있다.
             * */
            //꺼내기(아래방식은 DB를 통해서 계속 끄집어내야함)
            Member3 findMember = em.find(Member3.class, member3.getId());
            Long findTeamId = findMember.getTeamId();//Member와 참조된 TEAM id를 찾고
            Team findTeam = em.find(Team.class, findTeamId); // findTeamId로 또 Team을 찾고... 길다길어

            tx.commit();
        }catch (Exception e) {

        }
    }
}
