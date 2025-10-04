package com.musinsa.snap.entity

import com.musinsa.common.entity.Audit
import com.musinsa.user.entity.User
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import org.hibernate.envers.Audited

@Entity
@Table(name = "snaps")
@Audited
class Snap(
    @Column(nullable = true)
    var content: String?
) : Audit() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    var id: Long? = null

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "writer_id")
    var writer: User? = null

    @Column(nullable = false)
    var isDeleted: Boolean = false

    fun mapWriter(user: User) {
        if (this.writer != user) {
            this.writer = user
        }
    }

    fun delete() {
        this.isDeleted = true
    }
}
