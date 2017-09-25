package tk.avabin.tdg.beans.controllers

import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import tk.avabin.tdg.beans.dtos.CharacterAttackDto
import tk.avabin.tdg.beans.entities.CharacterAttack
import tk.avabin.tdg.beans.services.entities.CharacterAttackService
import tk.avabin.tdg.beans.services.entities.ItemService

@RestController
@RequestMapping("/attack")
class CharacterAttackRestController(
        private @Autowired val characterAttackService: CharacterAttackService,
        private @Autowired val itemService: ItemService,
        private @Autowired val modelMapper: ModelMapper
) {
    @RequestMapping("/add")
    fun add(@RequestBody languageDto: CharacterAttackDto): ResponseEntity<CharacterAttackDto> {
        var mapped = modelMapper.map(languageDto, CharacterAttack::class.java)
        return if (!characterAttackService.contains(mapped.name)) {
            mapped = characterAttackService.saveOrUpdate(mapped)
            ResponseEntity(modelMapper.map(mapped, CharacterAttackDto::class.java), HttpStatus.CREATED)
        } else {
            mapped = characterAttackService.getByName(mapped.name)
            ResponseEntity(modelMapper.map(mapped, CharacterAttackDto::class.java), HttpStatus.UNPROCESSABLE_ENTITY)
        }
    }

    @RequestMapping(value = "/get/{name}")
    fun getSpellByName(@PathVariable name: String): ResponseEntity<CharacterAttackDto> {
        return try {
            ResponseEntity(
                    modelMapper.map(characterAttackService.getByName(name),
                            CharacterAttackDto::class.java), HttpStatus.OK
            )
        } catch (e: Exception) {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @RequestMapping(value = "/get/{id}/id")
    fun getByName(@PathVariable id: Int): ResponseEntity<CharacterAttackDto> {
        return try {
            ResponseEntity(
                    modelMapper.map(characterAttackService.getById(id),
                            CharacterAttackDto::class.java), HttpStatus.OK
            )
        } catch (e: Exception) {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @RequestMapping(value = "/item/{attack}/{item}", method = arrayOf(RequestMethod.PUT))
    fun connect(@PathVariable attack: String,
                @PathVariable item: String): ResponseEntity<Any> {
        return if (characterAttackService.contains(attack) && itemService.contains(item)) {
            val itemObject = itemService.getByName(item)
            val attackObject = characterAttackService.getByName(attack)
            attackObject.attackItem = itemObject
            val modded = characterAttackService.saveOrUpdate(attackObject)
            ResponseEntity(modelMapper.map(modded, CharacterAttackDto::class.java), HttpStatus.OK)
        } else {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @RequestMapping(value = "/del/{name}", method = arrayOf(RequestMethod.DELETE))
    fun delete(@PathVariable name: String): ResponseEntity<Any> {
        return if (characterAttackService.contains(name)) {
            characterAttackService.delete(characterAttackService.getByName(name))
            ResponseEntity(HttpStatus.OK)
        } else {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }
}