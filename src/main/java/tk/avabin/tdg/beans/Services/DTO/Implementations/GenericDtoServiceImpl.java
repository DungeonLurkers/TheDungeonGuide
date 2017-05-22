package tk.avabin.tdg.beans.Services.DTO.Implementations;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.avabin.tdg.beans.Services.DTO.GenericDtoService;

/**
 * Created by Avabin on 18.05.2017.
 */
@Service
public class GenericDtoServiceImpl implements GenericDtoService {
    @Autowired
    private ModelMapper mapper;

    @Override
    public Object convertDtoToEntity(Object dto, Class entityClass) {
        return mapper.map(dto, entityClass);
    }

    @Override
    public Object convertEntityToDto(Object entity, Class dtoClass) {
        return mapper.map(entity, dtoClass);
    }
}
