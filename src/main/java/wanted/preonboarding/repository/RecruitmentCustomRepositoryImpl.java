package wanted.preonboarding.repository;

import static wanted.preonboarding.domain.QRecruitment.recruitment;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import wanted.preonboarding.domain.Recruitment;

@RequiredArgsConstructor
public class RecruitmentCustomRepositoryImpl implements RecruitmentCustomRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Recruitment> findRecruitments(String searchCondition) {
        return queryFactory.selectFrom(recruitment)
            .where(containsCondition(searchCondition))
            .fetch();
    }

    private BooleanBuilder containsCondition(String condition) {
        BooleanBuilder builder = new BooleanBuilder();

        return builder
            .or(containsPosition(condition))
            .or(containsSkill(condition))
            .or(containsCompanyName(condition));
    }

    private BooleanExpression containsPosition(String word) {
        return word != null ? recruitment.position.containsIgnoreCase(word) : null;
    }

    private BooleanExpression containsSkill(String word) {
        return word != null ? recruitment.skill.containsIgnoreCase(word) : null;
    }

    private BooleanExpression containsCompanyName(String word) {
        return word != null ? recruitment.company.name.containsIgnoreCase(word) : null;
    }
}
