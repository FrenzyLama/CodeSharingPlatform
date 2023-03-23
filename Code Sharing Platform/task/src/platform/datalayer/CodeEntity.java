package platform.datalayer;

import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.UUID;

@Table(name = "CODES_SNIPPETS")
@Entity
public class CodeEntity {

    @Id
    @Column(name = "id")
    private String id;

    @NotBlank
    @Column(name = "code")
    private String code;

    @PrePersist
    public void onInsert() {
        date = LocalDateTime.now();
    }

    @CreatedDate
    @Column(name = "date")
    private LocalDateTime date;

    @Column(name = "remaining_views")
    private Long reminingViews;

    @Column(name = "max_views")
    private Long maxViews;

    @Column(name = "max_Time_For_View")
    private Long maxTimeForView;

    @Column(name = "remaining_time")
    private Long remainingTime;

    public CodeEntity() {
        UUID uuid = UUID.randomUUID();
        id = uuid.toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Long getReminingViews() {
        return reminingViews;
    }

    public void setReminingViews(Long reminingViews) {
        this.reminingViews = reminingViews;
    }

    public Long getMaxViews() {
        return maxViews;
    }

    public void setMaxViews(Long maxViews) {
        this.maxViews = maxViews;
    }

    public Long getMaxTimeForView() {
        return maxTimeForView;
    }

    public void setMaxTimeForView(Long maxTimeForView) {
        this.maxTimeForView = maxTimeForView;
    }

    public Long getRemainingTime() {
        return remainingTime;
    }

    public void setRemainingTime(Long remainingTime) {
        this.remainingTime = remainingTime;
    }
}

