package wanted.preonboarding.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import wanted.preonboarding.config.QuerydslConfig;
import wanted.preonboarding.domain.Company;
import wanted.preonboarding.domain.Recruitment;

@DataJpaTest
@Import(QuerydslConfig.class)
@AutoConfigureTestDatabase(replace = Replace.NONE)
class RecruitmentRepositoryTest {

    @Autowired
    private RecruitmentRepository recruitmentRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @DisplayName("채용공고 저장 성공")
    @Test
    void save() {
        // given
        Recruitment recruitment = Recruitment.builder()
            .company(companyRepository.findById(1L).orElseThrow())
            .bounty(1000000)
            .position("백엔드 주니어 개발자")
            .contents("원티드랩에서 백엔드 주니어 개발자를 채용합니다. 자격요건은..")
            .skill("Spring")
            .build();

        // when
        recruitmentRepository.save(recruitment);

        // then
        List<Recruitment> recruitments = recruitmentRepository.findAll();
        assertThat(recruitments).hasSize(1);
    }

    @DisplayName("회사의 모든 채용공고 목록 조회")
    @Test
    void findRecruitmentIdsByCompany() {
        // given
        Company company1 = companyRepository.findById(1L).orElseThrow();
        Company company2 = companyRepository.findById(2L).orElseThrow();

        Recruitment recruitment1 = Recruitment.builder().company(company1).build();
        Recruitment recruitment2 = Recruitment.builder().company(company2).build();

        recruitmentRepository.save(recruitment1);
        recruitmentRepository.save(recruitment2);

        // when
        List<Long> recruitmentIdsByCompany1 = recruitmentRepository.findRecruitmentIdsByCompany(company1);
        List<Long> recruitmentIdsByCompany2 = recruitmentRepository.findRecruitmentIdsByCompany(company2);

        // then
        assertThat(recruitmentIdsByCompany1).containsExactly(recruitment1.getId());
        assertThat(recruitmentIdsByCompany2).containsExactly(recruitment2.getId());
    }

    @DisplayName("포지션으로 채용공고 검색")
    @Test
    void search() {
        // given
        Recruitment recruitment1 = Recruitment.builder()
            .company(companyRepository.findById(1L).orElseThrow())
            .bounty(1000000)
            .position("백엔드 주니어 개발자")
            .contents("원티드랩에서 백엔드 주니어 개발자를 채용합니다. 자격요건은..")
            .skill("Spring")
            .build();

        Recruitment recruitment2 = Recruitment.builder()
            .company(companyRepository.findById(1L).orElseThrow())
            .bounty(1200000)
            .position("프론트엔드 주니어 개발자")
            .contents("원티드랩에서 프론트엔드 주니어 개발자를 채용합니다. 자격요건은..")
            .skill("JavaScript")
            .build();

        recruitmentRepository.save(recruitment1);
        recruitmentRepository.save(recruitment2);

        // when
        List<Recruitment> recruitmentsByBackend = recruitmentRepository.findRecruitments("백엔드");
        List<Recruitment> recruitmentsByFrontend = recruitmentRepository.findRecruitments("프론트엔드");

        // then
        assertThat(recruitmentsByBackend).hasSize(1);
        assertThat(recruitmentsByBackend.get(0)).isEqualTo(recruitment1);
        assertThat(recruitmentsByFrontend).hasSize(1);
        assertThat(recruitmentsByFrontend.get(0)).isEqualTo(recruitment2);
    }
}
