package com.musinsa.user.entity

import com.musinsa.common.entity.BaseEntity
import com.musinsa.user.vo.UserGender
import com.musinsa.user.vo.UserGrade
import com.musinsa.user.vo.UserRole
import com.musinsa.user.vo.UserSignUpType
import com.musinsa.user.vo.UserStatus
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.hibernate.envers.Audited
import java.time.LocalDate

@Entity
@Audited
@Table(name = "users")
class User(
    @Column(nullable = true, unique = true, length = 100)
    val username: String? = null,
    @Column(nullable = true, length = 255)
    var password: String? = null,
    @Column(nullable = true, unique = true, length = 255)
    var socialId: String? = null,
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    var signUpType: UserSignUpType,
    @Column(nullable = false, unique = true, length = 255)
    var email: String,
    @Column(nullable = false, unique = true, length = 100)
    var name: String,
    @Column(nullable = false, unique = true, length = 100)
    var phone: String,
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    var gender: UserGender,
    @Column(nullable = false)
    var birthDate: LocalDate,
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    var status: UserStatus,
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    var grade: UserGrade,
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    var role: UserRole,
    @Column(nullable = false)
    var pointBalance: Int = 0,
) : BaseEntity() {

    @Id
    @Column(nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Column(nullable = false)
    var isDeleted: Boolean = false

    /* ==============================
     * 상태 변경 메서드
     * ============================== */
    fun delete() {
        this.isDeleted = true
    }
}
