package wanted.preonboarding.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import wanted.preonboarding.domain.Recruitment;

@DataJpaTest
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
}
