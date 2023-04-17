package c322.edu.iu.finalproject.memberservice.repository;
import c322.edu.iu.finalproject.memberservice.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {

}