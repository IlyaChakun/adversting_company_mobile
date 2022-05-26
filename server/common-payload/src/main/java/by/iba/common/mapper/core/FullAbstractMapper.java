package by.iba.common.mapper.core;


import by.iba.common.domain.core.BaseEntity;
import by.iba.common.dto.core.BaseAbstractReq;
import by.iba.common.dto.core.BaseResp;
import org.modelmapper.Converter;

import java.util.Objects;

public abstract class FullAbstractMapper<E extends BaseEntity, D extends BaseResp, R extends BaseAbstractReq>
        extends SimpleAbstractMapper<E, D>
        implements FullObjectMapper<E, D, R> {

    private final Class<E> entityClass;

    public FullAbstractMapper(Class<E> entityClass, Class<D> dtoClass) {
        super(entityClass, dtoClass);
        this.entityClass = entityClass;
    }

    @Override
    public E toEntityFromReq(final R req) {
        return Objects.isNull(req)
                ? null
                : mapper.map(req, entityClass);
    }

    protected Converter<R, E> toEntityFromReqConverter() {
        return context -> {
            R source = context.getSource();
            E destination = context.getDestination();
            mapSpecificFields(source, destination);
            return context.getDestination();
        };
    }

    protected void mapSpecificFields(final R source, final E destination) {
    }
}