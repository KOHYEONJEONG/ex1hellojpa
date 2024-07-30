package chat1;

import chat2.Member;
import chat2.RoleType;
import chat2.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {

    //ctrl+alt+v : 변수 생성
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
    EntityManager em = emf.createEntityManager();
    EntityTransaction tx = em.getTransaction();

    //모든 데이터를 변경하는 모든 작업은 jpa에서 꼭 trasction안에서 작업을 해야한다.
    tx.begin();

    try { //정상적일 때는 커밋을 하고
        Member2 member2 = new Member2();
        member2.setId(1L);
        member2.setUsername("A");
        member2.setRoleType(RoleType.USER);

        em.persist(member2);

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
