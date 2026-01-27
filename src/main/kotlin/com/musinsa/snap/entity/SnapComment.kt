package com.musinsa.snap.entity

import com.musinsa.common.entity.BaseEntity
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
@Table(name = "snap_comments")
@Audited
class SnapComment(
    @Column(nullable = false, length = 255)
    var comment: String
) : BaseEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    var id: Long? = null

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    var parent: SnapComment? = null

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "snap_id")
    var snap: Snap? = null

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "writer_id")
    var writer: User? = null

    @Column(nullable = false)
    var isDeleted: Boolean = false

    /* ==============================
     * 연관관계 편의 메서드
     * ============================== */
    fun assignParent(snapComment: SnapComment?) {
        if (this.parent != snapComment) {
            this.parent = snapComment
        }
    }

    fun assignSnap(snap: Snap) {
        if (this.snap != snap) {
            this.snap = snap
        }
    }

    fun assignWriter(user: User) {
        if (this.writer != user) {
            this.writer = user
        }
    }

    /* ==============================
     * 상태 변경 메서드
     * ============================== */
    fun delete() {
        this.isDeleted = true
    }
}
