package tk.avabin.tdg.beans.controllers

import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
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

    @RequestMapping("/add")
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
}