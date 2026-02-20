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
import org.hibernate.envers.Audited

@Entity
@Table(name = "product_categories")
@Audited
class ProductCategory(
    @Column(nullable = false, length = 100)
    var name: String,
    @Column(nullable = false, length = 255)
    var iconImagePath: String,
) : BaseEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false, unique = true)
    var id: Long? = null

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private var _parent: ProductCategory? = null
    val parent: ProductCategory?
        get() = _parent

    @Column(nullable = false)
    var isDeleted: Boolean = false

    /* ==============================
     * 연관관계 편의 메서드
     * ============================== */
    fun assignParent(parent: ProductCategory?) {
        if (this._parent != parent) {
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
