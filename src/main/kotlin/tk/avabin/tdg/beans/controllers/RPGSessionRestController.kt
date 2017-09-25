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
import tk.avabin.tdg.beans.dtos.RPGSessionDto
import tk.avabin.tdg.beans.entities.RPGSession
import tk.avabin.tdg.beans.services.entities.CharacterService
import tk.avabin.tdg.beans.services.entities.RPGSessionService
import tk.avabin.tdg.beans.services.entities.UserService

@RestController
@RequestMapping("/rpgsession")
class RPGSessionRestController(
        private @Autowired val modelMapper: ModelMapper,
        private @Autowired val rpgSessionService: RPGSessionService,
        private @Autowired val userService: UserService,
        private @Autowired val characterService: CharacterService
) {
    @RequestMapping("/add")
    fun addSkill(@RequestBody rpgSessionDto: RPGSessionDto): ResponseEntity<RPGSessionDto> {
        var mapped = modelMapper.map(rpgSessionDto, RPGSession::class.java)
        return if (!rpgSessionService.contains(mapped.name)) {
            mapped = rpgSessionService.saveOrUpdate(mapped)
            ResponseEntity(modelMapper.map(mapped, RPGSessionDto::class.java), HttpStatus.CREATED)
        } else {
            mapped = rpgSessionService.getByName(mapped.name)
            ResponseEntity(modelMapper.map(mapped, RPGSessionDto::class.java), HttpStatus.UNPROCESSABLE_ENTITY)
        }
    }

    @RequestMapping(value = "/get/{name}")
    fun getSpellByName(@PathVariable name: String): ResponseEntity<RPGSessionDto> {
        return try {
            ResponseEntity(
                    modelMapper.map(rpgSessionService.getByName(name),
                            RPGSessionDto::class.java), HttpStatus.OK
            )
        } catch (e: Exception) {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @RequestMapping(value = "/get/{id}/id")
    fun getSpellById(@PathVariable id: Int): ResponseEntity<RPGSessionDto> {
        return try {
            ResponseEntity(
                    modelMapper.map(rpgSessionService.getById(id),
                            RPGSessionDto::class.java), HttpStatus.OK
            )
        } catch (e: Exception) {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @RequestMapping(value = "/gm/{user}/{session}", method = arrayOf(RequestMethod.PUT))
    fun setGameMaster(
            @PathVariable session: String,
            @PathVariable user: String
    ): ResponseEntity<Any> {
        return if (rpgSessionService.contains(session) && userService.contains(user)) {
            val sessionObject = rpgSessionService.getByName(session)
            val userObject = userService.getByName(user)
            sessionObject.gameMaster = userObject
            val modded = rpgSessionService.saveOrUpdate(sessionObject)
            ResponseEntity(modelMapper.map(modded, RPGSessionDto::class.java), HttpStatus.OK)
        } else {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @RequestMapping(value = "/player/{player}/{session}", method = arrayOf(RequestMethod.PUT))
    fun addPlayer(
            @PathVariable session: String,
            @PathVariable player: String
    ): ResponseEntity<Any> {
        return if (rpgSessionService.contains(session) && userService.contains(player)) {
            val sessionObject = rpgSessionService.getByName(session)
            val userObject = userService.getByName(player)
            sessionObject.players += userObject
            val modded = rpgSessionService.saveOrUpdate(sessionObject)
            ResponseEntity(modelMapper.map(modded, RPGSessionDto::class.java), HttpStatus.OK)
        } else {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @RequestMapping(value = "/character/{character}/{session}", method = arrayOf(RequestMethod.PUT))
    fun addCharacter(
            @PathVariable session: String,
            @PathVariable character: String
    ): ResponseEntity<Any> {
        return if (rpgSessionService.contains(session) && characterService.contains(character)) {
            val sessionObject = rpgSessionService.getByName(session)
            val characterObject = characterService.getByName(character)
            if (characterObject.owner !in sessionObject.players) return ResponseEntity(HttpStatus.NOT_FOUND)
            sessionObject.characters += characterObject
            val modded = rpgSessionService.saveOrUpdate(sessionObject)
            ResponseEntity(modelMapper.map(modded, RPGSessionDto::class.java), HttpStatus.OK)
        } else {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @RequestMapping(value = "/del/{name}", method = arrayOf(RequestMethod.DELETE))
    fun deleteSkill(@PathVariable name: String): ResponseEntity<Any> {
        return if (rpgSessionService.contains(name)) {
            rpgSessionService.delete(rpgSessionService.getByName(name))
            ResponseEntity(HttpStatus.OK)
        } else {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }
}