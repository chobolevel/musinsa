package com.musinsa.post.entity

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
@Table(name = "post_tag_mappings")
class PostTagMapping : BaseEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false, unique = true)
    var id: Long? = null

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    var post: Post? = null
        protected set

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "post_tag_id")
    var postTag: PostTag? = null
        protected set

    /* ==============================
     * 연관관계 편의 메서드
     * ============================== */
    fun assignPost(post: Post) {
        if (this.post != post) {
            this.post = post
        }
    }

    fun assignPostTag(postTag: PostTag) {
        if (this.postTag != postTag) {
            this.postTag = postTag
        }
    }
}
