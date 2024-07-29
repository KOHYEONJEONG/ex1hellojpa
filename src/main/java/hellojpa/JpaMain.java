package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {

    //src/main/resources/META-INF/persistence.xml  name속성="hello"
    //ctrl+alt+v : 변수 생성
        //
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
    EntityManager em = emf.createEntityManager();
    EntityTransaction tx = em.getTransaction();

    //모든 데이터를 변경하는 모든 작업은 jpa에서 꼭 trasction안에서 작업을 해야한다.
    tx.begin();

    try { //정상적일 때는 커밋을 하고

        //저장
        Team team = new Team();
        team.setName("TeamA");
        em.persist(team);

        //비영속성
        Member member = new Member();
        member.setUsername("member1");
        member.setTeam(team); //(중요) jpa가 알아서 Team에서 pk를 꺼내서 fk 값에 인서트할때 사용

        //영속성
        em.persist(member);

        /**
         * 플러시(FLUSH)
         * 순서1) INSERT 쿼리가 작성 됨.
         * 순서2) 데이터베이스 트랜직션이 커밋 됨.
         *
         * 쓰기 지연 SQL 저장소에 있던 영속성 컨텍스트 데이터가 db에 반영된다.
         * */
        em.flush();
        em.clear();

        Member findMember = em.find(Member.class, member.getId());// id로 조회해서 꺼내겠다
        Team findTime = findMember.getTeam();//바로 꺼낼 수 있음.(연관관계 매핑때문에)
        System.out.println("findTeam = "+findTime.getName()); // findTeam = TeamA

        //양방향 연관관계(멤버에서 팀으로, 팀에서 멤버로 왔다갔다)
        List<Member> members = findMember.getTeam().getMembers();

        for (Member m:members){
            System.out.println("m= "+m.getUsername());
        }

        //영속성 수정
     /*   Member updateM = em.find(Member.class, 2L);
        updateM.setUsername("고현정");
*/
        tx.commit();
    }catch (Exception e){
        //문제가 생기면 롤백
        tx.rollback();
    }finally {
        em.close();
    }
    emf.close();

    }
}
