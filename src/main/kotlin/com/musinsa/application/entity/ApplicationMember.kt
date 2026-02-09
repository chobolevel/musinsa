package com.musinsa.application.entity

import com.musinsa.application.vo.member.ApplicationMemberType
import com.musinsa.common.entity.BaseEntity
import com.musinsa.user.entity.User
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import org.hibernate.envers.Audited

@Entity
@Audited
@Table(name = "application_members")
class ApplicationMember(
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    var type: ApplicationMemberType,
) : BaseEntity() {

    @Id
    @Column(nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "application_id")
    var application: Application? = null

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    var user: User? = null

    @Column(nullable = false)
    var isDeleted: Boolean = false

    fun delete() {
        this.isDeleted = true
    }

    companion object {
        fun create(application: Application, user: User, memberType: ApplicationMemberType): ApplicationMember {
            return ApplicationMember(
                type = memberType
            ).also {
                // 주인 객체에서 매핑 관리
                it.application = application
                it.user = user
            }
        }
    }
}
