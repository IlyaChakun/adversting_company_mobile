package by.iba.common.domain.core;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;
import java.util.Objects;

@MappedSuperclass
@NoArgsConstructor
@Getter
public abstract class TrackingAbstractEntity extends BaseEntity {

    @Column(name = "version")
    private Integer version;

    @Column(name = "date_of_creation")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime dateOfCreation;

    @Column(name = "date_of_last_update")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
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
        if (Objects.nonNull(this.version))
            this.version++;
    }
}
