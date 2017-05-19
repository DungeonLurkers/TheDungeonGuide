package tk.avabin.tdg.beans.Services.DTO.Implementations;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import tk.avabin.tdg.beans.Services.DTO.GenericDtoService;

/**
 * Created by Avabin on 18.05.2017.
 */
public class GenericDtoServiceImpl implements GenericDtoService {
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Object convertDtoToEntity(Object dto, Class entityClass) {
        return modelMapper.map(dto, entityClass);
    }

    @Override
    public Object convertEntityToDto(Object entity, Class dtoClass) {
        return modelMapper.map(entity, dtoClass);
    }
}
