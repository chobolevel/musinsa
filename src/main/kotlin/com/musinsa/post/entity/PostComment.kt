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
import org.hibernate.envers.Audited

@Entity
@Table(name = "post_comments")
@Audited
class PostComment(
    @Column(nullable = false, length = 255)
    var comment: String,
) : BaseEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false, unique = true)
    var id: Long? = null

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private var _user: User? = null
    val user: User?
        get() = _user

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private var _post: Post? = null
    val post: Post?
        get() = _post

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private var _parent: PostComment? = null
    val parent: PostComment?
        get() = _parent

    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    var isDeleted: Boolean = false

    /* ==============================
     * 생성 시 불변식 검증
     * ============================== */
    init {
        require(comment.length < 255) { "댓글은 최대 255자까지 입력 가능합니다." }
    }

    /* ==============================
     * 연관관계 편의 메서드
     * ============================== */
    fun assignUser(user: User) {
        if (this._user != user) {
            this._user = user
        }
    }

    fun assignPost(post: Post) {
        if (this._post != post) {
            this._post = post
        }
    }

    fun assignParent(parent: PostComment) {
        if (this != parent && this._parent != parent) {
            this._parent = parent
        }
    }

    /* ==============================
     * 상태 변경 메서드
     * ============================== */
    fun delete() {
        this.isDeleted = true
    }
}
