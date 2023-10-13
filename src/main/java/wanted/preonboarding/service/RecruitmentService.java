package wanted.preonboarding.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import wanted.preonboarding.domain.Company;
import wanted.preonboarding.domain.Recruitment;
import wanted.preonboarding.dto.RecruitmentCreateRequest;
import wanted.preonboarding.repository.CompanyRepository;
import wanted.preonboarding.repository.RecruitmentRepository;

@Service
@RequiredArgsConstructor
public class RecruitmentService {

    private final RecruitmentRepository recruitmentRepository;
    private final CompanyRepository companyRepository;

    public Long create(RecruitmentCreateRequest recruitmentCreateRequest) {
        Company company = companyRepository.findById(recruitmentCreateRequest.companyId())
            .orElseThrow();

        Recruitment recruitment = Recruitment.builder()
            .company(company)
            .position(recruitmentCreateRequest.position())
            .bounty(recruitmentCreateRequest.bounty())
            .contents(recruitmentCreateRequest.contents())
            .skill(recruitmentCreateRequest.skill())
            .build();

        return recruitmentRepository.save(recruitment).getId();
    }
}
