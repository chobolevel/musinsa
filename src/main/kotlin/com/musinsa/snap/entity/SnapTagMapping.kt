package com.musinsa.snap.entity

import com.musinsa.common.entity.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table

@Entity
@Table(name = "snap_tags_mapping")
class SnapTagMapping(
    @Column(nullable = false)
    var order: Int
) : BaseEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    var id: Long? = null

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "snap_id")
    var snap: Snap? = null

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "snap_tag_id")
    var snapTag: SnapTag? = null

    /* ==============================
     * 연관관계 편의 메서드
     * ============================== */
    fun assignSnap(snap: Snap) {
        if (this.snap != snap) {
            this.snap = snap
        }
    }

    fun assignSnapTag(snapTag: SnapTag) {
        if (this.snapTag != snapTag) {
            this.snapTag = snapTag
        }
    }
}
