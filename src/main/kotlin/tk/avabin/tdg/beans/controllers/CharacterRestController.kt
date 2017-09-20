package tk.avabin.tdg.beans.controllers

import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import tk.avabin.tdg.beans.dtos.CharacterDto
import tk.avabin.tdg.beans.entities.Character
import tk.avabin.tdg.beans.services.entities.CharacterService
import tk.avabin.tdg.beans.services.entities.UserService

@RestController
@RequestMapping("/character")
class CharacterRestController(
        private @Autowired val characterService: CharacterService,
        private @Autowired val userService: UserService,
        private @Autowired val modelMapper: ModelMapper
) {

    @RequestMapping(value = "/add", method = arrayOf(RequestMethod.POST))
    fun addCharacter(@RequestBody characterDto: CharacterDto): ResponseEntity<CharacterDto> {
        var mapped = modelMapper.map(characterDto, Character::class.java)
        return if (!characterService.contains(mapped.name)) {
            mapped = characterService.saveOrUpdate(mapped)
            ResponseEntity(modelMapper.map(mapped, CharacterDto::class.java), HttpStatus.CREATED)
        } else {
            val body = modelMapper.map(characterService.getByName(mapped.name), CharacterDto::class.java)
            ResponseEntity(body, HttpStatus.UNPROCESSABLE_ENTITY)
        }
    }

    @RequestMapping(value = "/get/{name}", method = arrayOf(RequestMethod.GET))
    fun getCharacter(@PathVariable name: String): ResponseEntity<CharacterDto> {
        return try {
            ResponseEntity(
                    modelMapper.map(characterService.getByName(name),
                            CharacterDto::class.java), HttpStatus.OK
            )
        } catch (e: Exception) {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @RequestMapping(value = "/owner/{characterName}/{userName}", method = arrayOf(RequestMethod.PUT))
    fun connectCharacterAndUser(
            @PathVariable characterName: String,
            @PathVariable userName: String
    ): ResponseEntity<Any> {
        return try {
            val character = characterService.getByName(characterName)
            val user = userService.getByUsername(userName)
            character.owner = user
            characterService.saveOrUpdate(character)
            ResponseEntity(modelMapper.map(character, CharacterDto::class.java), HttpStatus.OK)
        } catch (e: Exception) {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @RequestMapping(value = "/del/{name}", method = arrayOf(RequestMethod.DELETE))
    fun deleteCharacter(@PathVariable name: String): ResponseEntity<Any> {
        return if (characterService.contains(name)) {
            characterService.delete(characterService.getByName(name))
            ResponseEntity(HttpStatus.OK)
        } else {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }
}