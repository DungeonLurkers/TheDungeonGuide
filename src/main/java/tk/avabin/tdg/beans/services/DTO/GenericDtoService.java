package tk.avabin.tdg.beans.services.DTO;

/**
 * Created by Avabin on 18.05.2017.
 */
public interface GenericDtoService {
    Object convertDtoToEntity(Object dto, Class entityClass);

    Object convertEntityToDto(Object entity, Class dtoClass);
}
