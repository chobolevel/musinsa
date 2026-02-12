package com.musinsa.post.entity

import com.musinsa.post.vo.PostImageType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import org.hibernate.envers.Audited
import org.hibernate.envers.NotAudited

@Entity
@Table(name = "post_images")
@Audited
class PostImage(
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    var type: PostImageType,
    @Column(nullable = false, length = 255)
    var path: String,
    @Column(nullable = false)
    var width: Int,
    @Column(nullable = false)
    var height: Int,
    @Column(nullable = false)
    var sortOrder: Int,
) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false, unique = true)
    var id: Long? = null

    @NotAudited
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    var post: Post? = null

    /* ==============================
     * 생성 시 불변식 검증
     * ============================== */
    init {
        require(width >= 0) { "이미지 너비(width)은(는) 0 이상이어야 합니다." }
        require(height >= 0) { "이미지 높이(height)은(는) 0 이상이어야 합니다." }
        require(sortOrder >= 0) { "이미지 정렬 순서(sort_order)은(는) 0 이상이어야 합니다." }
    }

    /* ==============================
     * 연관관계 편의 메서드
     * ============================== */
    fun assignPost(post: Post) {
        if (this.post != post) {
            this.post = post
        }
    }
}
