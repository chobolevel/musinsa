package com.musinsa.snap.entity

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
import jakarta.persistence.OrderBy
import jakarta.persistence.Table
import org.hibernate.annotations.SQLRestriction
import org.hibernate.envers.Audited
import org.hibernate.envers.NotAudited

@Entity
@Table(name = "snaps")
@Audited
class Snap(
    @Column(nullable = true)
    var content: String?
) : BaseEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    var id: Long? = null

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "writer_id")
    var writer: User? = null

    @Column(nullable = false)
    var isDeleted: Boolean = false

    @OneToMany(mappedBy = "snap", cascade = [(CascadeType.ALL)], orphanRemoval = true)
    @SQLRestriction("is_deleted = true")
    @OrderBy("order asc")
    val snapImages: MutableList<SnapImage> = mutableListOf()

    @NotAudited
    @OneToMany(mappedBy = "snap", cascade = [(CascadeType.ALL)], orphanRemoval = true)
    @OrderBy("created_at desc")
    val snapLikes: List<SnapLike> = listOf()

    @NotAudited
    @OneToMany(mappedBy = "snap", cascade = [(CascadeType.ALL)], orphanRemoval = true)
    @OrderBy("order asc")
    val snapTagMappings: MutableList<SnapTagMapping> = mutableListOf()

    /* ==============================
     * 연관관계 편의 메서드
     * ============================== */
    fun assignWriter(user: User) {
        if (this.writer != user) {
            this.writer = user
        }
    }

    fun addSnapImage(
        snapImage: SnapImage
    ) {
        snapImage.assignSnap(snap = this)
        if (!this.snapImages.contains(snapImage)) {
            this.snapImages.add(snapImage)
        }
    }

    fun addSnapTag(snapTag: SnapTag) {
        val snapTagMapping = SnapTagMapping(
            order = this.snapTagMappings.size
        ).also { snapTagMapping ->
            snapTagMapping.assignSnap(snap = this)
            snapTagMapping.assignSnapTag(snapTag = snapTag)
        }
        if (!this.snapTagMappings.contains(snapTagMapping)) {
            this.snapTagMappings.add(snapTagMapping)
        }
    }

    fun subSnapTag(snapTag: SnapTag) {
        this.snapTagMappings.removeIf { it.snapTag == snapTag }
    }

    /* ==============================
     * 상태 변경 메서드
     * ============================== */
    fun delete() {
        this.isDeleted = true
    }

    fun deleteSnapTagInBatch(snapTagIds: List<Long>) {
        this.snapTagMappings.removeIf { snapTagMapping ->
            snapTagIds.contains(snapTagMapping.snapTag!!.id!!)
        }
    }

    fun deleteSnapImageInBatch(snapImageIds: List<Long>) {
        this.snapImages.removeIf { snapImage ->
            snapImageIds.contains(snapImage.id)
        }
    }
}
