package wanted.preonboarding.service;

import jakarta.persistence.EntityNotFoundException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import wanted.preonboarding.domain.Application;
import wanted.preonboarding.domain.Member;
import wanted.preonboarding.domain.Recruitment;
import wanted.preonboarding.dto.ApplicationCreateRequest;
import wanted.preonboarding.repository.ApplicationRepository;
import wanted.preonboarding.repository.MemberRepository;
import wanted.preonboarding.repository.RecruitmentRepository;

@Service
@RequiredArgsConstructor
public class ApplicationService {

    public static final String NOT_FOUND_MEMBER = "ID[%s] 해당 회원를 찾을 수 없습니다.";
    public static final String NOT_FOUND_RECRUITMENT = "ID[%s] 해당 공고를 찾을 수 없습니다.";
    public static final String DUPLICATE_APPLICATION = "이미 지원 결과가 존재합니다.";

    private final ApplicationRepository applicationRepository;
    private final MemberRepository memberRepository;
    private final RecruitmentRepository recruitmentRepository;

    public Long create(ApplicationCreateRequest request) {
        Member member = memberRepository.findById(request.memberId())
            .orElseThrow(() -> new EntityNotFoundException(String.format(NOT_FOUND_MEMBER, request.memberId())));

        Recruitment recruitment = recruitmentRepository.findById(request.recruitmentId())
            .orElseThrow(() -> new EntityNotFoundException(String.format(NOT_FOUND_RECRUITMENT, request.recruitmentId())));

        Optional<Application> savedApplication = applicationRepository.findByMemberAndRecruitment(member, recruitment);

        if (savedApplication.isPresent()) {
            throw new IllegalStateException(DUPLICATE_APPLICATION);
        }

        return applicationRepository.save(new Application(member, recruitment)).getId();
    }
}
