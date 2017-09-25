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
import tk.avabin.tdg.beans.dtos.ItemDto
import tk.avabin.tdg.beans.entities.Item
import tk.avabin.tdg.beans.services.entities.ItemService

@RestController
@RequestMapping("/item")
class ItemRestController(
        private @Autowired val modelMapper: ModelMapper,
        private @Autowired val itemService: ItemService
) {
    @RequestMapping("/add")
    fun addSkill(@RequestBody itemDto: ItemDto): ResponseEntity<ItemDto> {
        var mapped = modelMapper.map(itemDto, Item::class.java)
        return if (!itemService.contains(mapped.name)) {
            mapped = itemService.saveOrUpdate(mapped)
            ResponseEntity(modelMapper.map(mapped, ItemDto::class.java), HttpStatus.CREATED)
        } else {
            mapped = itemService.getByName(mapped.name)
            ResponseEntity(modelMapper.map(mapped, ItemDto::class.java), HttpStatus.UNPROCESSABLE_ENTITY)
        }
    }

    @RequestMapping(value = "/get/{name}")
    fun getSpellByName(@PathVariable name: String): ResponseEntity<ItemDto> {
        return try {
            ResponseEntity(
                    modelMapper.map(itemService.getByName(name),
                            ItemDto::class.java), HttpStatus.OK
            )
        } catch (e: Exception) {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @RequestMapping(value = "/get/{id}/id")
    fun getSpellById(@PathVariable id: Int): ResponseEntity<ItemDto> {
        return try {
            ResponseEntity(
                    modelMapper.map(itemService.getById(id),
                            ItemDto::class.java), HttpStatus.OK
            )
        } catch (e: Exception) {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @RequestMapping(value = "/del/{name}", method = arrayOf(RequestMethod.DELETE))
    fun deleteSkill(@PathVariable name: String): ResponseEntity<Any> {
        return if (itemService.contains(name)) {
            itemService.delete(itemService.getByName(name))
            ResponseEntity(HttpStatus.OK)
        } else {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }
}