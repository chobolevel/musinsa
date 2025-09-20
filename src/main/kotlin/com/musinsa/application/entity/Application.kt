package com.musinsa.application.entity

import com.musinsa.application.entity.member.ApplicationMember
import com.musinsa.application.vo.member.ApplicationMemberType
import com.musinsa.common.entity.Audit
import com.musinsa.user.entity.User
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import org.hibernate.annotations.SQLRestriction
import org.hibernate.envers.Audited

@Entity
@Audited
@Table(name = "applications")
class Application(
    @Column(nullable = false, length = 100)
    var name: String,
    @Column(nullable = false, length = 100)
    var key: String,
    @Column(nullable = false, length = 255)
    var secretKey: String,
) : Audit() {

    @Id
    @Column(nullable = false, unique = true)
    var id: Long? = null

    @Column(nullable = false)
    var isDeleted: Boolean = false

    @OneToMany(mappedBy = "application", cascade = [CascadeType.ALL], orphanRemoval = true)
    @SQLRestriction("is_deleted = false")
    val applicationMembers: MutableList<ApplicationMember> = mutableListOf()

    fun delete() {
        this.isDeleted = true
    }

    fun addMember(user: User, memberType: ApplicationMemberType) {
        // 주인 객체에서 매핑 관리를 유지하면서 도메인 흐름에 맞는 편의 메서드 정의
        val applicationMember: ApplicationMember = ApplicationMember.create(
            application = this,
            user = user,
            memberType = memberType
        )
        this.applicationMembers.add(applicationMember)
        user.applicationMembers.add(applicationMember)
    }
}
