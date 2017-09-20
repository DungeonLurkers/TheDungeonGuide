package tk.avabin.tdg.beans.controllers

import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import tk.avabin.tdg.beans.dtos.RPGSessionDto
import tk.avabin.tdg.beans.entities.RPGSession
import tk.avabin.tdg.beans.services.entities.RPGSessionService
import tk.avabin.tdg.beans.services.entities.UserService

@RestController
@RequestMapping("/rpgsession")
class RPGSessionRestController(
        private @Autowired val modelMapper: ModelMapper,
        private @Autowired val rpgSessionService: RPGSessionService,
        private @Autowired val userService: UserService
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

    @RequestMapping(value = "/gm/{session}/{user}", method = arrayOf(RequestMethod.PUT))
    fun setGameMaster(
            @PathVariable session: String,
            @PathVariable user: String
    ): ResponseEntity<Any> {
        return try {
            val sessionObject = rpgSessionService.getByName(session)
            val userObject = userService.getByUsername(user)
            sessionObject.gameMaster = userObject
            rpgSessionService.saveOrUpdate(sessionObject)
            ResponseEntity(modelMapper.map(sessionObject, RPGSessionDto::class.java), HttpStatus.OK)
        } catch (e: Exception) {
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