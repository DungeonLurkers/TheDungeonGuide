package tk.avabin.tdg

import lombok.extern.java.Log
import org.junit.Test
import org.junit.runner.RunWith
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import tk.avabin.tdg.beans.dtos.CharacterDto
import tk.avabin.tdg.beans.dtos.RPGClassAndLevelDto
import tk.avabin.tdg.beans.dtos.RPGSessionDto
import tk.avabin.tdg.beans.dtos.UserDto
import tk.avabin.tdg.beans.entities.Character
import tk.avabin.tdg.beans.entities.RPGClassAndLevel
import tk.avabin.tdg.beans.entities.User
import tk.avabin.tdg.config.AppConfig

@RunWith(SpringRunner::class)
@SpringBootTest(classes = arrayOf(
        AppConfig::class,
        RPGSessionDto::class,
        CharacterDto::class,
        RPGClassAndLevelDto::class
))
@Log
class MapperTest {
    private @Autowired lateinit var modelMapper: ModelMapper

    @Test
    fun userMappingTest() {
        val user = User(
                id = 0,
            name = "Test",
                email = "email@m.ail",
                password = "notSalted",
                salt = "saltey"
        )
        val mappedDto = modelMapper.map(user, UserDto::class.java)
        assert(mappedDto.id == user.id)
        assert(mappedDto.name == user.name)
        assert(mappedDto.email == user.email)
        assert(mappedDto.salt == user.salt)
    }

    @Test
    fun rpgClassAndLevelAndCharacterMappingsTest() {
        val character = Character()
        character.name = "TestCharacter"
        character.age = 20

        val rpgClassAndLevel = RPGClassAndLevel()
        rpgClassAndLevel.id = 2
        rpgClassAndLevel.rpgClass = "Warrior"

        character.classesAndLevels = HashSet(setOf(rpgClassAndLevel))

        val characterMapped = modelMapper.map(character, CharacterDto::class.java)
        val classAndLevelMapped = modelMapper.map(rpgClassAndLevel, RPGClassAndLevelDto::class.java)

        assert(characterMapped.id == character.id)
        assert(characterMapped.name == character.name)
        assert(classAndLevelMapped in characterMapped.classesAndLevels)

        assert(classAndLevelMapped.id == rpgClassAndLevel.id)
        assert(classAndLevelMapped.rpgClass == rpgClassAndLevel.rpgClass)

        val remappedCharacter = modelMapper.map(characterMapped, Character::class.java)

        assert(remappedCharacter == character)
        assert(rpgClassAndLevel in remappedCharacter.classesAndLevels!!)
    }

}