package com.musinsa.product.entity

import com.musinsa.common.entity.Audit
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
    var iconImageUrl: String,
) : Audit() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false, unique = true)
    var id: Long? = null

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    var parent: ProductCategory? = null

    @Column(nullable = false)
    var isDeleted: Boolean = false

    fun assignParent(parent: ProductCategory?) {
        if (this.parent != parent) {
            this.parent = parent
        }
    }

    fun delete() {
        this.isDeleted = true
    }
}
