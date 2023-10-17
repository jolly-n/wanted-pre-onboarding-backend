package wanted.preonboarding.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import wanted.preonboarding.dto.RecruitmentCreateRequest;
import wanted.preonboarding.dto.RecruitmentCreateResponse;
import wanted.preonboarding.dto.RecruitmentEditRequest;
import wanted.preonboarding.dto.RecruitmentEditResponse;
import wanted.preonboarding.dto.RecruitmentSearchResponse;
import wanted.preonboarding.service.RecruitmentService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/recruitments")
public class RecruitmentController {

    private final RecruitmentService recruitmentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RecruitmentCreateResponse create(@RequestBody RecruitmentCreateRequest request) {
        return new RecruitmentCreateResponse(recruitmentService.create(request));
    }

    @PutMapping("/{recruitmentId}")
    public RecruitmentEditResponse edit(@PathVariable Long recruitmentId, @RequestBody RecruitmentEditRequest request) {
        return new RecruitmentEditResponse(recruitmentService.edit(recruitmentId, request));
    }

    @DeleteMapping("/{recruitmentId}")
    public ResponseEntity<String> delete(@PathVariable Long recruitmentId) {
        recruitmentService.delete(recruitmentId);
        return ResponseEntity.ok("공고 삭제 성공");
    }

    @GetMapping
    public List<RecruitmentSearchResponse> search(@RequestParam(name = "search", required = false) String searchCond) {
        return recruitmentService.search(searchCond);
    }
}
