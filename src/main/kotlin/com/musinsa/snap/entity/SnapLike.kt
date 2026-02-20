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
    private var _snap: Snap? = null
    val snap: Snap?
        get() = _snap

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private var _user: User? = null
    val user: User?
        get() = _user

    /* ==============================
     * 연관관계 편의 메서드
     * ============================== */
    fun assignSnap(snap: Snap) {
        if (this._snap != snap) {
            this._snap = snap
        }
    }

    fun assignUser(user: User) {
        if (this._user != user) {
            this._user = user
        }
    }
}
