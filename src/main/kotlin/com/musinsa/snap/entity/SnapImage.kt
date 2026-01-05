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
    var sortOrder: Int,
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

    /* ==============================
     * 생성 시 불변식 검증
     * ============================== */
    init {
        require(width >= 0) { "스냅 이미지 너비(width)는 0보다 크거나 같아야 합니다." }
        require(height >= 0) { "스냅 이미지 높이(height)는 0보다 크거나 같아야 합니다." }
        require(sortOrder >= 0) { "스냅 이미지 정렬 순서(order)는 0보다 크거나 같아야 합니다." }
    }

    /* ==============================
     * 연관관계 편의 메서드
     * ============================== */
    fun assignSnap(snap: Snap) {
        if (this.snap != snap) {
            this.snap = snap
        }
    }

    /* ==============================
     * 상태 변경 메서드
     * ============================== */
    fun delete() {
        this.isDeleted = true
    }
}
