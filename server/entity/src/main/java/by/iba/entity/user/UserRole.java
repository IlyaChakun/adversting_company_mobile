package by.iba.entity.user;

import by.iba.common.domain.core.FullAbstractEntity;
import by.iba.common.domain.core.TrackingAbstractEntity;
import lombok.*;

import javax.persistence.*;


@Entity
@Table(name = "roles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRole extends FullAbstractEntity {

    @Column(name = "name", nullable = false, unique = true)
    private String name;

}
