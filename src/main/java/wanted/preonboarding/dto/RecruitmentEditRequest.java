package wanted.preonboarding.dto;

public record RecruitmentEditRequest(
    String position,
    int bounty,
    String contents,
    String skill
) {

}
