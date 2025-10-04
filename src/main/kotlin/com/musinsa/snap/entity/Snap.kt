package com.musinsa.snap.entity

import com.musinsa.common.entity.Audit
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
import org.hibernate.annotations.SQLRestriction
import org.hibernate.envers.Audited

@Entity
@Table(name = "snaps")
@Audited
class Snap(
    @Column(nullable = true)
    var content: String?
) : Audit() {

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
    val snapImages: MutableList<SnapImage> = mutableListOf()

    fun assignWriter(user: User) {
        if (this.writer != user) {
            this.writer = user
        }
    }

    fun delete() {
        this.isDeleted = true
    }

    fun addSnapImage(
        url: String,
        width: Int,
        height: Int,
        order: Int
    ) {
        SnapImage(
            url = url,
            width = width,
            height = height,
            order = order
        ).also { snapImage ->
            snapImage.assignSnap(snap = this)
            if (!this.snapImages.contains(snapImage)) {
                this.snapImages.add(snapImage)
            }
        }
    }
}
