package wanted.preonboarding.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wanted.preonboarding.domain.Company;
import wanted.preonboarding.domain.Recruitment;
import wanted.preonboarding.dto.RecruitmentCreateRequest;
import wanted.preonboarding.dto.RecruitmentEditRequest;
import wanted.preonboarding.repository.CompanyRepository;
import wanted.preonboarding.repository.RecruitmentRepository;

@Service
@RequiredArgsConstructor
public class RecruitmentService {

    public static final String NOT_FOUND_RECRUITMENT = "ID[%s] 해당 공고를 찾을 수 없습니다.";

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

    @Transactional
    public Long edit(Long recruitmentId, RecruitmentEditRequest recruitmentEditRequest) {
        Recruitment recruitment = recruitmentRepository.findById(recruitmentId)
            .orElseThrow(() -> new EntityNotFoundException(String.format(NOT_FOUND_RECRUITMENT, recruitmentId)));

        Recruitment newRecruitment = Recruitment.builder()
            .position(recruitmentEditRequest.position())
            .bounty(recruitmentEditRequest.bounty())
            .contents(recruitmentEditRequest.contents())
            .skill(recruitmentEditRequest.skill())
            .build();

        recruitment.update(newRecruitment);
        return recruitmentId;
    }
}
