package tk.avabin.tdg.beans.controllers

import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import tk.avabin.tdg.beans.dtos.FeatDto
import tk.avabin.tdg.beans.entities.Feat
import tk.avabin.tdg.beans.services.entities.FeatService

@RestController
@RequestMapping("/feat")
class FeatRestController(
        private @Autowired val modelMapper: ModelMapper,
        private @Autowired val featService: FeatService
) {
    @RequestMapping("/add")
    fun addSkill(@RequestBody featDto: FeatDto): ResponseEntity<FeatDto> {
        var mapped = modelMapper.map(featDto, Feat::class.java)
        return if (!featService.contains(mapped.name)) {
            mapped = featService.saveOrUpdate(mapped)
            ResponseEntity(modelMapper.map(mapped, FeatDto::class.java), HttpStatus.CREATED)
        } else {
            mapped = featService.getByName(mapped.name)
            ResponseEntity(modelMapper.map(mapped, FeatDto::class.java), HttpStatus.UNPROCESSABLE_ENTITY)
        }
    }

    @RequestMapping(value = "/get/{name}")
    fun getSpellByName(@PathVariable name: String): ResponseEntity<FeatDto> {
        return try {
            ResponseEntity(
                    modelMapper.map(featService.getByName(name),
                            FeatDto::class.java), HttpStatus.OK
            )
        } catch (e: Exception) {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @RequestMapping(value = "/get/{id}/id")
    fun getSpellById(@PathVariable id: Int): ResponseEntity<FeatDto> {
        return try {
            ResponseEntity(
                    modelMapper.map(featService.getById(id),
                            FeatDto::class.java), HttpStatus.OK
            )
        } catch (e: Exception) {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @RequestMapping(value = "/del/{name}", method = arrayOf(RequestMethod.DELETE))
    fun deleteSkill(@PathVariable name: String): ResponseEntity<Any> {
        return if (featService.contains(name)) {
            featService.delete(featService.getByName(name))
            ResponseEntity(HttpStatus.OK)
        } else {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }
}