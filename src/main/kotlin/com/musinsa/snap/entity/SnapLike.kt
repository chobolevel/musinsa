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

@Entity
@Table(name = "snap_likes")
class SnapLike : BaseEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    var id: Long? = null

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "snap_id")
    var snap: Snap? = null
        protected set

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    var user: User? = null
        protected set

    /* ==============================
     * 연관관계 편의 메서드
     * ============================== */
    fun assignSnap(snap: Snap) {
        if (this.snap != snap) {
            this.snap = snap
        }
    }

    fun assignUser(user: User) {
        if (this.user != user) {
            this.user = user
        }
    }
}
