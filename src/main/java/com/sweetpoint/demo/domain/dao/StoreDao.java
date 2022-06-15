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
@Table(name = "m_store")
@SQLDelete(sql = "UPDATE m_store SET deleted_at = CURRENT_TIMESTAMP WHERE id = ?")
@Where(clause = "deleted_at IS NULL")
public class StoreDao extends BaseDao implements Serializable {

    private static final long serialVersionUID = -4130848546964499314L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "store_name", nullable = false)
    private String storeName;

    @Column(name = "description", nullable = false)
    private String description;
}
