package com.musinsa.post.entity

import com.musinsa.common.entity.BaseEntity
import com.musinsa.user.entity.User
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
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

    @OneToMany(mappedBy = "post", cascade = [CascadeType.ALL], orphanRemoval = true)
    val postTagMappings: MutableSet<PostTagMapping> = mutableSetOf()

    /* ==============================
     * 생성 시 불변식 검증
     * ============================== */
    init {
        require(title.length > 5) { "제목(title)은 5자 이상이어야 합니다." }
        require(content.length > 20) { "내용(content)은 최소 20자 이상이어야 합니다." }
    }

    /* ==============================
     * 연관관계 편의 메서드
     * ============================== */
    fun assignUser(user: User) {
        if (this.user != user) {
            this.user = user
        }
    }

    fun addPostTag(postTag: PostTag) {
        val postTagMapping = PostTagMapping().also {
            it.post = this
            it.postTag = postTag
        }
        if (!this.postTagMappings.contains(postTagMapping)) {
            this.postTagMappings.add(postTagMapping)
        }
    }

    fun addPostTagInBatch(postTags: List<PostTag>) {
        postTags.forEach { this.addPostTag(it) }
    }

    /* ==============================
     * 상태 변경 메서드
     * ============================== */
    fun delete() {
        this.isDeleted = true
    }
}
