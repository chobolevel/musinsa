package com.musinsa.snap.entity

import com.musinsa.common.entity.Audit
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
@Table(name = "snap_images")
@Audited
class SnapImage(
    @Column(nullable = false, length = 255)
    var path: String,
    @Column(nullable = false)
    var width: Int,
    @Column(nullable = false)
    var height: Int,
    @Column(nullable = false)
    var order: Int,
) : Audit() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    var id: Long? = null

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "snap_id")
    var snap: Snap? = null

    @Column(nullable = false)
    var isDeleted: Boolean = false

    fun assignSnap(snap: Snap) {
        if (this.snap != snap) {
            this.snap = snap
        }
    }

    fun delete() {
        this.isDeleted = true
    }
}
