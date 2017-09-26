package tk.avabin.tdg

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import tk.avabin.tdg.beans.entities.UserRole
import tk.avabin.tdg.beans.services.entities.UserService
import java.util.function.Consumer

@Service("userDetailsService")
open class TdgUserDetailsService(
    private @Autowired val userService: UserService
) : UserDetailsService {
    @Transactional(readOnly = true)
    override fun loadUserByUsername(username: String): UserDetails {
        val user: tk.avabin.tdg.beans.entities.User = userService.getByName(username)
        val authorities: List<GrantedAuthority> = buildUserAuthority(user.roles)

        return buildUserForAuthentication(user, authorities)
    }

    private fun buildUserForAuthentication(
        user: tk.avabin.tdg.beans.entities.User,
        authorities: List<GrantedAuthority>): User {

        return User(user.name, user.password, authorities)
    }

    private fun buildUserAuthority(userRoles: Set<UserRole>): List<GrantedAuthority> {
        val setAuths = HashSet<GrantedAuthority>()

        userRoles.forEach(Consumer { userRole: UserRole -> setAuths.add(SimpleGrantedAuthority(userRole.role)) })

        return ArrayList(setAuths)
    }
}