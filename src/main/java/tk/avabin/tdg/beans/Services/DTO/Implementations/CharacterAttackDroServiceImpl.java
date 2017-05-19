package tk.avabin.tdg.beans.Services.DTO.Implementations;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.avabin.tdg.beans.DTO.CharacterAttackDto;
import tk.avabin.tdg.beans.Entities.CharacterAttack;
import tk.avabin.tdg.beans.Services.DTO.CharacterAttackDtoService;

/**
 * Created by Avabin on 18.05.2017.
 */
@Service
public class CharacterAttackDroServiceImpl implements CharacterAttackDtoService {

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CharacterAttack dtoToEntity(CharacterAttackDto dto) {
        return modelMapper.map(dto, CharacterAttack.class);
    }

    @Override
    public CharacterAttackDto entityToDto(CharacterAttack entity) {
        return modelMapper.map(entity, CharacterAttackDto.class);
    }
}
