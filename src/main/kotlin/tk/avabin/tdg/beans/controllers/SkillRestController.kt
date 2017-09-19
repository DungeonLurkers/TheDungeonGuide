package tk.avabin.tdg.beans.controllers

import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import tk.avabin.tdg.beans.dtos.SkillDto
import tk.avabin.tdg.beans.entities.Skill
import tk.avabin.tdg.beans.services.entities.SkillService

@RestController
@RequestMapping("/skill")
class SkillRestController(
        private @Autowired val skillService: SkillService,
        private @Autowired val modelMapper: ModelMapper
) {

    @RequestMapping("/add")
    fun addSkill(@RequestBody skillDto: SkillDto): ResponseEntity<SkillDto> {
        if (skillService.contains(skillDto.name)) return ResponseEntity(skillDto, HttpStatus.UNPROCESSABLE_ENTITY)
        return try {
            val skill: Skill = modelMapper.map(skillDto, Skill::class.java)
            skillService.saveOrUpdate(skill)
            ResponseEntity(skillDto, HttpStatus.CREATED)
        } catch (e: Exception) {
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @RequestMapping(value = "/get/{name}")
    fun getSpellByName(@PathVariable name: String): ResponseEntity<SkillDto> {
        return try {
            ResponseEntity(
                    modelMapper.map(skillService.getByName(name),
                            SkillDto::class.java), HttpStatus.OK
            )
        } catch (e: Exception) {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @RequestMapping(value = "/get/{id}/id")
    fun getSpellById(@PathVariable id: Int): ResponseEntity<SkillDto> {
        return try {
            ResponseEntity(
                    modelMapper.map(skillService.getById(id),
                            SkillDto::class.java), HttpStatus.OK
            )
        } catch (e: Exception) {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @RequestMapping(value = "/del/{name}", method = arrayOf(RequestMethod.DELETE))
    fun deleteSkill(@PathVariable name: String): ResponseEntity<Any> {
        return if (skillService.contains(name)) {
            skillService.delete(skillService.getByName(name))
            ResponseEntity(HttpStatus.OK)
        } else {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }
}