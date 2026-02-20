package com.musinsa.user.entity

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
@Table(name = "user_follows")
class UserFollow : BaseEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    var id: Long? = null

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "follower_id")
    private var _follower: User? = null
    val follower: User?
        get() = _follower

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "following_id")
    private var _following: User? = null
    val following: User?
        get() = _following

    /* ==============================
     * 연관관계 편의 메서드
     * ============================== */
    fun assignFollower(user: User) {
        if (this._follower != user) {
            this._follower = user
        }
    }

    fun assignFollowing(user: User) {
        if (this._following != user) {
            this._following = user
        }
    }
}
