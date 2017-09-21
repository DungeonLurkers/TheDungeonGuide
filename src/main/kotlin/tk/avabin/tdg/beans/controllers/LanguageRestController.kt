package tk.avabin.tdg.beans.controllers

import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import tk.avabin.tdg.beans.dtos.LanguageDto
import tk.avabin.tdg.beans.entities.Language
import tk.avabin.tdg.beans.services.entities.LanguageService

@RestController
@RequestMapping("/language")
class LanguageRestController(
        private @Autowired val languageService: LanguageService,
        private @Autowired val modelMapper: ModelMapper
) {

    @RequestMapping("/add")
    fun add(@RequestBody languageDto: LanguageDto): ResponseEntity<LanguageDto> {
        var mapped = modelMapper.map(languageDto, Language::class.java)
        return if (!languageService.contains(mapped.name)) {
            mapped = languageService.saveOrUpdate(mapped)
            ResponseEntity(modelMapper.map(mapped, LanguageDto::class.java), HttpStatus.CREATED)
        } else {
            mapped = languageService.getByName(mapped.name)
            ResponseEntity(modelMapper.map(mapped, LanguageDto::class.java), HttpStatus.UNPROCESSABLE_ENTITY)
        }
    }

    @RequestMapping(value = "/get/{name}")
    fun getByName(@PathVariable name: String): ResponseEntity<LanguageDto> {
        return try {
            ResponseEntity(
                    modelMapper.map(languageService.getByName(name),
                            LanguageDto::class.java), HttpStatus.OK
            )
        } catch (e: Exception) {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @RequestMapping(value = "/get/{id}/id")
    fun getByName(@PathVariable id: Int): ResponseEntity<LanguageDto> {
        return try {
            ResponseEntity(
                    modelMapper.map(languageService.getById(id),
                            LanguageDto::class.java), HttpStatus.OK
            )
        } catch (e: Exception) {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @RequestMapping(value = "/del/{name}", method = arrayOf(RequestMethod.DELETE))
    fun delete(@PathVariable name: String): ResponseEntity<Any> {
        return if (languageService.contains(name)) {
            languageService.delete(languageService.getByName(name))
            ResponseEntity(HttpStatus.OK)
        } else {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }
}