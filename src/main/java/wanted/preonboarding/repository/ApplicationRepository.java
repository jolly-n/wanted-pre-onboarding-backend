package wanted.preonboarding.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import wanted.preonboarding.domain.Application;
import wanted.preonboarding.domain.Member;
import wanted.preonboarding.domain.Recruitment;

public interface ApplicationRepository extends JpaRepository<Application, Long> {

    Optional<Application> findByMemberAndRecruitment(Member member, Recruitment recruitment);
}
