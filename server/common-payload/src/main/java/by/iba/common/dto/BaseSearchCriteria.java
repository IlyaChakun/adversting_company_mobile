package by.iba.common.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;


@Getter
@Setter
@NoArgsConstructor
public class BaseSearchCriteria {

    private Integer page = 0;

    private Integer size = 10;

    public Pageable getPageable() {
        return PageRequest.of(page, size);
    }
}
