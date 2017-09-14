package tk.avabin.tdg.beans.services.dtos;

import tk.avabin.tdg.beans.dtos.UserDto;
import tk.avabin.tdg.beans.entities.User;

/**
 * Created by Avabin on 18.05.2017.
 */
public interface UserDtoService {
    User dtoToEntity(UserDto dto);

    UserDto objectToDto(User entity);
}
