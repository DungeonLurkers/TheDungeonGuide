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
        var mapped = modelMapper.map(skillDto, Skill::class.java)
        return if (!skillService.contains(mapped.name)) {
            mapped = skillService.saveOrUpdate(mapped)
            ResponseEntity(modelMapper.map(mapped, SkillDto::class.java), HttpStatus.CREATED)
        } else {
            mapped = skillService.getByName(mapped.name)
            ResponseEntity(modelMapper.map(mapped, SkillDto::class.java), HttpStatus.UNPROCESSABLE_ENTITY)
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