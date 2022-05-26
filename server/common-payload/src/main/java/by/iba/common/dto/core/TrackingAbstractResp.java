package by.iba.common.dto.core;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.Null;
import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
public abstract class TrackingAbstractResp extends BaseResp {

    @Null(message = "Version will set automatically")
    private Integer version;

    @Null(message = "dateOfCreation does not required")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH.mm")
    private LocalDateTime dateOfCreation;

    @Null(message = "dateOfCreation does not required")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH.mm")
    private LocalDateTime dateOfLastUpdate;

    @PrePersist
    private void abstractEntityPreInit() {
        this.dateOfCreation = LocalDateTime.now();
        this.dateOfLastUpdate = LocalDateTime.now();
        this.version = 1;
    }

    @PreUpdate
    private void abstractEntityPreUpdate() {
        this.dateOfLastUpdate = LocalDateTime.now();
        this.version++;
    }
}