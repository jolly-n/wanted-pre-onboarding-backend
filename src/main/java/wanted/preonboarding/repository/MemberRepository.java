package wanted.preonboarding.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wanted.preonboarding.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

}
