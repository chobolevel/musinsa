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
import org.hibernate.envers.NotAudited

@Entity
@Table(name = "product_options")
@Audited
class ProductOption(
    @Column(nullable = false, length = 100)
    var name: String,
    @Column(nullable = false)
    var sortOrder: Int,
    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    var isRequired: Boolean
) : Audit() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false, unique = true)
    var id: Long? = null

    @NotAudited
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    var product: Product? = null

    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    var isDeleted: Boolean = false

    fun assignProduct(product: Product) {
        if (this.product != product) {
            this.product = product
        }
    }

    fun delete() {
        this.isDeleted = true
    }
}
