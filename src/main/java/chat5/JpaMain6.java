package chat5;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain6 {
    /**
     * 양방향 연관관계 주의사항.
     *  -  순수 객체 상태를 고려해서 항상 양쪽에 값을 설정하자
     *  - 연관관계 편의 메소드를 생성하자 (주인에 넣어도 되고, 주인이 아닌곳에 넣어도 되지만 한곳에만 넣어주자)
     *      - EX) setTeam(team)으로 이동하자!! + ctrl+SHIFT+F
     *  - 양방향 매핑 시에 무한루프를 조심하자
     *  ex) toString(), lombok, JSON 생성 라이브러리
     *   -- toString()은 되도록 사용하지 말자.
     *   -- JSON 생성 라이브러리 : 엔티티는 절대 반환하지 말자 - 단순하게 값만 있는걸 반환하면 괜찮음.
     */
    public static void main(String[] args) {

        //ctrl+alt+v : 변수 생성

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        try {
            Team5 team = new Team5();
            team.setName("TeamA");
            //team.getMembers().add(member);//연관관계 주인이 아닌이상 읽기전용이기 때문에 등록이 되지 않는다!!
            em.persist(team);//영속상태가 되면 무조건 pk 값이 세팅되고 영속상태가 된다.

            Member5 member = new Member5();
            member.setUsername("member1");
            member.setTeam(team);//연관관계 주인이기에 등록가능!
            em.persist(member);

            //(중요)무의미하지만 양방향 연관관계일 경우에는 주인이 아닌 객체여도(읽기 전용) 넣어주자.
            //아니면 setTeam5(team)메서드의 아래 코드를 넣어주면 된다.
            //team.getMembers().add(member);//flush()랑 clear()이 없다면 있어야한다!!

      //      em.flush();  //현재 영속성 컨텍스트에 있는 디비에 쿼리를 다 날려 버림.(이제 find()사용 시 캐시가 아닌, db에서 조회할거임. - select ....)
      //      em.clear();  //영속성 컨텍스트 초기화

            Team5 findTeam = em.find(Team5.class, team.getId());//1차캐시에서 가져오겠지?
            List<Member5> members = findTeam.getMembers();

            System.out.println("================");
            for(Member5 m : members){
                System.out.println("m = "+m.getUsername());
            }
            System.out.println("================");

            tx.commit();
        }catch (Exception e) {

        }
    }
}
