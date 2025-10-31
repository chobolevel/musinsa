package com.musinsa.brand.entity

import com.musinsa.common.entity.Audit
import com.musinsa.common.vo.NationType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.hibernate.envers.Audited

@Entity
@Table(name = "brands")
@Audited
class Brand(
    @Column(nullable = false, length = 100)
    var name: String,
    @Column(nullable = true, length = 100)
    var englishName: String? = null,
    @Column(nullable = false)
    var yearOfLaunch: Int,
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    var nation: NationType,
    @Column(nullable = false, length = 255)
    var description: String,
) : Audit() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    var id: Long? = null

    @Column(nullable = false)
    var isDeleted: Boolean = false

    fun delete() {
        this.isDeleted = true
    }
}
