package chat5;

import chat2.Member;
import chat4.Member4;
import chat4.Team4;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain5 {
    public static void main(String[] args) {

        //ctrl+alt+v : 변수 생성

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        try {
            Team5 team = new Team5();
            team.setName("TeamA");


            em.persist(team);//영속상태가 되면 무조건 pk 값이 세팅되고 영속상태가 된다.

            Member5 member = new Member5();
            member.setUsername("member1");
            member.setTeam(team);

            em.persist(member);

            em.flush();  //현재 영속성 컨텍스트에 있는 디비에 쿼리를 다 날려 버림.
            em.clear();  //영속성 컨텍스트 초기화

            Member5 findMember=em.find(Member5.class, member.getId()); //영속성 컨텍스트 - 콘솔창에 select문이 없는 이유는, 1차 캐시에서 가져옴(만약에 캐시에서 안가져오고 싶다면, flush()와 clear()사용하자)

            List<Member5> member5s = findMember.getTeam().getMembers();

            for(Member5 m: member5s){
                System.out.println("m = "+m.getUsername());//m = member1
            }

            tx.commit();
        }catch (Exception e) {

        }
    }
}
