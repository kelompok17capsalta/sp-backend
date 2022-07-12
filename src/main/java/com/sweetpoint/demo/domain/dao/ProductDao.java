package com.sweetpoint.demo.domain.dao;

import com.sweetpoint.demo.domain.common.BaseDao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "m_product")
@SQLDelete(sql = "UPDATE m_product SET deleted_at = CURRENT_TIMESTAMP WHERE id = ? ")
@Where(clause = "deleted_at IS NULL")
public class ProductDao extends BaseDao implements Serializable {

    private static final long serialVersionUID = 7289144500816668297L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "product_name", nullable = false)
    private String productName;

    @Column(name = "denom", nullable = false)
    private Integer denom;

    @Column(name = "category", nullable = false)
    private String category;

    @Column(name = "descriptions", nullable = false)
    private String descriptions;

    @Column(name = "points", nullable = false)
    private Integer points;

    @Column(name = "stock", nullable = false)
    private Integer stock;

    @Column(name = "image", nullable = false)
    private String image;
}
