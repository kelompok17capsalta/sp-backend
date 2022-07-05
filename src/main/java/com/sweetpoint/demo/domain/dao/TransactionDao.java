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
@Table(name = "m_transaction")
@SQLDelete(sql = "UPDATE m_transaction SET deleted_at = CURRENT_TIMESTAMP WHERE id = ? ")
@Where(clause = "deleted_at IS NULL")
public class TransactionDao extends BaseDao implements Serializable {

    private static final long serialVersionUID = 1799740307115560979L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "descriptions", nullable = false)
    private String descriptions;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "points", nullable = false)
    private Integer points;

    @ManyToOne
    private UserDao user;

    @Column(name = "category", nullable = false)
    private String category;

    @Column(name = "credentials", nullable = false)
    private String credentials;

    @Column(name = "provider", nullable = false)
    private String provider;

    @Column(name = "denom", nullable = false)
    private Integer denom;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductDao product;

}
