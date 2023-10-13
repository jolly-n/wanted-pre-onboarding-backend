package wanted.preonboarding.dto;

public record RecruitmentCreateRequest(
    Long companyId,
    String position,
    int bounty,
    String contents,
    String skill
) {

}
