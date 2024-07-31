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

            em.persist(team);//영속상태가 되면 무조건 pk 값이 세팅되고 영속상태가 된다.

            Member4 member = new Member4();
            member.setUsername("member1");
            member.setTeam(team);

            em.persist(member);

//            em.flush();  //현재 영속성 컨텍스트에 있는 디비에 쿼리를 다 날려 버림.
//            em.clear();  //영속성 컨텍스트 초기화

            Member4 findMember=em.find(Member4.class, member.getId()); //영속성 컨텍스트 - 콘솔창에 select문이 없는 이유는, 1차 캐시에서 가져옴(만약에 캐시에서 안가져오고 싶다면, flush()와 clear()사용하자)

            Team4 findTeam = findMember.getTeam(); //연관관계 매핑을 해뒀기 때문에, 바로 꺼내서 사용 가능하다!
            System.out.println("findTeam = "+findTeam.getName()); //findTeam = TeamA

            tx.commit();
        }catch (Exception e) {

        }
    }
}
