package by.iba.common.dto.mapper;

import by.iba.common.domain.Address;
import by.iba.common.dto.AddressDTO;
import by.iba.common.mapper.core.SimpleAbstractMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper extends SimpleAbstractMapper<Address, AddressDTO> {

    @Autowired
    public AddressMapper() {
        super(Address.class, AddressDTO.class);
    }
}