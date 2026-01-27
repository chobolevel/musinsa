package com.musinsa.user.entity.follow

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
@Table(name = "user_follows")
class UserFollow : BaseEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    var id: Long? = null

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "follower_id")
    var follower: User? = null

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "following_id")
    var following: User? = null

    /* ==============================
     * 연관관계 편의 메서드
     * ============================== */
    fun assignFollower(user: User) {
        if (this.follower != user) {
            this.follower = user
        }
    }

    fun assignFollowing(user: User) {
        if (this.following != user) {
            this.following = user
        }
    }
}
