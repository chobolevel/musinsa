package com.musinsa.product.entity

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
import org.hibernate.annotations.SQLDelete
import org.hibernate.envers.Audited
import org.hibernate.envers.NotAudited

@Entity
@Table(name = "product_images")
@Audited
@SQLDelete(sql = "UPDATE product_images SET is_deleted = 1 WHERE id = ?")
class ProductImage(
    @Column(nullable = false, length = 255)
    var path: String,
    @Column(nullable = false)
    var width: Int,
    @Column(nullable = false)
    var height: Int,
    @Column(nullable = false)
    var sortOrder: Int,
) : BaseEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false, unique = true)
    var id: Long? = null

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    @NotAudited
    var product: Product? = null

    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    var isDeleted: Boolean = false

    /* ==============================
     * 연관관계 편의 메서드
     * ============================== */
    fun assignProduct(product: Product) {
        if (this.product != product) {
            this.product = product
        }
    }

    /* ==============================
     * 상태 변경 메서드
     * ============================== */
    fun delete() {
        this.isDeleted = true
    }
}
