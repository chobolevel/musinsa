package com.musinsa.snap.entity

import com.musinsa.common.entity.Audit
import com.musinsa.snap.vo.SnapTagType
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
@Table(name = "snap_tags")
@Audited
class SnapTag(
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    var type: SnapTagType,
    @Column(nullable = false, length = 100)
    var name: String,
) : Audit() {

    @Id
    @Column(nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Column(nullable = false)
    var isDeleted: Boolean = false

    fun delete() {
        this.isDeleted = true
    }
}
