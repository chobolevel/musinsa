package com.musinsa.auth.entity

import com.musinsa.user.entity.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.core.userdetails.UserDetails

class UserDetailsImpl(
    private val user: User
) : UserDetails {

    override fun getAuthorities(): Collection<GrantedAuthority?>? {
        return AuthorityUtils.createAuthorityList(user.role.name)
    }

    override fun getPassword(): String? {
        return user.password
    }

    override fun getUsername(): String? {
        return user.id.toString()
    }
}
