package tk.avabin.tdg.beans.Converters;

import org.modelmapper.AbstractConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tk.avabin.tdg.beans.DTO.CharacterDto;
import tk.avabin.tdg.beans.Entities.Character;
import tk.avabin.tdg.beans.Entities.*;

/**
 * Created by avabin on 22.05.17.
 */
@Component
public class CharacterConverter extends AbstractConverter<Character, CharacterDto> {
    @Autowired
    private CharacterDto dto;

    @Override
    protected CharacterDto convert(Character source) {
        if (source.getOwner() != null)
            dto.setOwnerId(source.getOwner().getId());
        if (source.getClassesAndLevels() != null)
            dto.setClassesAndLevelsIds(source.getClassesAndLevels()
                    .stream().mapToInt(RPGClassAndLevel::getId).toArray());
        if (source.getItems() != null)
            dto.setItemsIds(source.getItems().stream().mapToInt(Item::getId).toArray());
        if (source.getSpells() != null)
            dto.setSpellsIds(source.getSpells().stream().mapToInt(Spell::getId).toArray());
        if (source.getSkills() != null)
            dto.setSkillsIds(source.getSkills().stream().mapToInt(Skill::getId).toArray());
        if (source.getFeats() != null)
            dto.setFeatsIds(source.getFeats().stream().mapToInt(Feat::getId).toArray());
        if (source.getLanguages() != null)
            dto.setLanguagesIds(source.getLanguages().stream().mapToInt(Language::getId).toArray());
        return dto;
    }
}
