package tk.avabin.tdg.beans.controllers

import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import tk.avabin.tdg.beans.dtos.CharacterDto
import tk.avabin.tdg.beans.dtos.UserDto
import tk.avabin.tdg.beans.entities.Character
import tk.avabin.tdg.beans.entities.User
import tk.avabin.tdg.beans.services.PasswordEncryptionService
import tk.avabin.tdg.beans.services.SaltGeneratorService
import tk.avabin.tdg.beans.services.entities.CharacterService
import tk.avabin.tdg.beans.services.entities.UserService
import java.sql.SQLException
import kotlin.streams.toList

/**
 * Created by Avabin on 18.05.2017.
 */
@RestController
@RequestMapping("/user")
class UserRestController(
        private @Autowired val mapper: ModelMapper,
        private @Autowired val userService: UserService,
        private @Autowired val characterService: CharacterService,
        private @Autowired val saltGeneratorService: SaltGeneratorService,
        private @Autowired val passwordEncryptionService: PasswordEncryptionService) {

    @RequestMapping(value = "/add", method = arrayOf(RequestMethod.POST))
    fun addUser(@RequestBody userBody: UserDto): ResponseEntity<UserDto> {
        val user: User = mapper.map<User>(userBody, User::class.java)
        if( !userService.contains(user.username)) {
            user.salt = saltGeneratorService.nextSaltAsString()
            val newPass = passwordEncryptionService.getEncryptedPassAsB64String(user.password, user.salt)
            user.password = newPass
        } else {
            val respbody = mapper.map(
                    userService.getByUsername(user.username),
                    UserDto::class.java
            )
            return ResponseEntity(respbody, HttpStatus.FOUND)
        }
        try {
            userService.saveOrUpdate(user)
        } catch (e: SQLException) {
            return ResponseEntity(HttpStatus.NOT_FOUND)
        }
        return ResponseEntity(mapper.map(user, UserDto::class.java), HttpStatus.CREATED)
    }

    @RequestMapping(value = "/get/{name}", method = arrayOf(RequestMethod.GET))
    fun getUserByName(@PathVariable name: String): ResponseEntity<UserDto> {
        val user: User? = userService.getByUsername(name)
        val body: UserDto
        try {
            body = mapper.map(user, UserDto::class.java)
        } catch (e: Exception) {
            return ResponseEntity(HttpStatus.NOT_FOUND)
        }
        return ResponseEntity(body, HttpStatus.OK)
    }

    @RequestMapping(value = "/get/{id}/id", method = arrayOf(RequestMethod.GET))
    fun getuserById(@PathVariable id: Int): ResponseEntity<UserDto> {
        val user: User? = userService.getById(id)
        val body: UserDto
        try {
            body = mapper.map(user, UserDto::class.java)
        } catch (e: Exception) {
            return ResponseEntity(HttpStatus.NOT_FOUND)
        }
        return ResponseEntity(body, HttpStatus.OK)

    }

    @RequestMapping(value = "/get/{name}/characters", method = arrayOf(RequestMethod.GET))
    fun getCharactersByUser(@PathVariable name: String): ResponseEntity<List<CharacterDto>> {
        return try {
            val owner = userService.getByUsername(name)
            val charArray: ArrayList<Character> = ArrayList(characterService.getAllByOwner(owner))
            val respArray = charArray.stream().map { character: Character? -> mapper.map(character, CharacterDto::class.java) }.toList()
            ResponseEntity(respArray, HttpStatus.OK)
        } catch (e: Exception) {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

}
