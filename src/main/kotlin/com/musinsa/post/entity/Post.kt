package com.musinsa.post.entity

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
@Table(name = "posts")
class Post(
    @Column(nullable = false, length = 100)
    var title: String,
    @Column(nullable = false, length = 255)
    var content: String,
) : BaseEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false, unique = true)
    var id: Long? = null

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    var user: User? = null

    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    var isDeleted: Boolean = false

    /* ==============================
     * 연관관계 편의 메서드
     * ============================== */
    fun assignUser(user: User) {
        if (this.user != user) {
            this.user = user
        }
    }

    /* ==============================
     * 상태 변경 메서드
     * ============================== */
    fun delete() {
        this.isDeleted = true
    }
}
