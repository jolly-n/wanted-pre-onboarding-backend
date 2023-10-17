package wanted.preonboarding.dto;

import lombok.Builder;

@Builder
public record RecruitmentSearchResponse(
    Long recruitmentId,
    String name,
    String country,
    String region,
    String position,
    int bounty,
    String skill
) {

}
