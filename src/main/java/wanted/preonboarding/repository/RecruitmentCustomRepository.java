package wanted.preonboarding.repository;

import java.util.List;
import wanted.preonboarding.domain.Recruitment;

public interface RecruitmentCustomRepository {

    List<Recruitment> findRecruitments(String searchCondition);
}
