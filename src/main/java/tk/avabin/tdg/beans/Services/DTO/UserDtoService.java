package tk.avabin.tdg.beans.Services.DTO;

import tk.avabin.tdg.beans.DTO.UserDto;
import tk.avabin.tdg.beans.Entities.User;

/**
 * Created by Avabin on 18.05.2017.
 */
public interface UserDtoService {
    User dtoToEntity(UserDto dto);

    UserDto objectToDto(User entity);
}
