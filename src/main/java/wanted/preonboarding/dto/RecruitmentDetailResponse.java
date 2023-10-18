package wanted.preonboarding.dto;

import java.util.List;
import lombok.Builder;

@Builder
public record RecruitmentDetailResponse(
    Long recruitmentId,
    String name,
    String country,
    String region,
    String position,
    int bounty,
    String skill,
    String contents,
    List<Long> anotherRecruitmentIds
) {

}
