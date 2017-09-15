package tk.avabin.tdg.beans.controllers

import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RestController
import tk.avabin.tdg.beans.services.entities.CharacterService
import tk.avabin.tdg.beans.services.entities.UserService

@RestController
class CharacterRestController(
        private @Autowired val characterService: CharacterService,
        private @Autowired val userService: UserService,
        private @Autowired val modelMapper: ModelMapper
)