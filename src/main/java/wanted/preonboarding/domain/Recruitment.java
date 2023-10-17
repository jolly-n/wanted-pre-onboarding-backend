package wanted.preonboarding.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Recruitment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recruitment_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    private int bounty;
    private String position;
    private String contents;
    private String skill;

    @Builder
    public Recruitment(Company company, String position, int bounty, String contents,
        String skill) {
        this.company = company;
        this.position = position;
        this.bounty = bounty;
        this.contents = contents;
        this.skill = skill;
    }

    public void update(Recruitment newRecruitment) {
        this.position = newRecruitment.getPosition();
        this.bounty = newRecruitment.getBounty();
        this.skill = newRecruitment.getSkill();
        this.contents = newRecruitment.getContents();
    }
}
