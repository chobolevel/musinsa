package com.musinsa.user.entity

import com.musinsa.common.entity.Audit
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.hibernate.envers.Audited

@Entity
@Audited
@Table(name = "users")
class User(
    @Column(nullable = false, length = 80)
    val email: String,
    @Column(nullable = false, length = 255)
    var password: String,
    @Column(nullable = false, length = 80)
    var nickname: String
) : Audit() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Column(nullable = false)
    var resigned: Boolean = false

    fun resign() {
        this.resigned = true
    }
}
