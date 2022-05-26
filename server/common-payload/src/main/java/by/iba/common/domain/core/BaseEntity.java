package by.iba.common.domain.core;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
@NoArgsConstructor
@Getter
public abstract class BaseEntity extends MappableObject {

}
