package com.musinsa.application.entity

import com.musinsa.common.entity.Audit
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
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

    fun delete() {
        this.isDeleted = true
    }
}
