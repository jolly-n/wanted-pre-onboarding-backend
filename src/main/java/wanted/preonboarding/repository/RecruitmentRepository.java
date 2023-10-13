package wanted.preonboarding.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wanted.preonboarding.domain.Recruitment;

public interface RecruitmentRepository extends JpaRepository<Recruitment, Long> {

}
