package tk.avabin.tdg

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.junit4.SpringRunner
import tk.avabin.tdg.beans.controllers.UserRestController
import tk.avabin.tdg.beans.dtos.UserDto

@RunWith(SpringRunner::class)
@SpringBootTest
@DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
class UserRestTest {
    private @Autowired lateinit var userRestController: UserRestController

    @Test
    fun addUserTest() {
        val user = UserDto(
                username = "HenloUser",
                email = "email@mail.com",
                password = "notsalted",
                salt = "salteyeyeye"
        )
        val response = userRestController.addUser(user)
        assert(response.statusCode in listOf(HttpStatus.OK, HttpStatus.FOUND))
        assert(response.body.password != user.password)

    }
}