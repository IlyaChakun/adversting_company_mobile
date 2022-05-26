package by.iba.common.mapper.core;


import by.iba.common.domain.core.MappableObject;
import by.iba.common.dto.core.MappableObjectDTO;

import java.util.List;

public interface SimpleObjectMapper<E extends MappableObject, D extends MappableObjectDTO> {

    E toEntity(final D d);

    D toDto(final E e);

    List<D> toDtoList(final List<E> eList);
}