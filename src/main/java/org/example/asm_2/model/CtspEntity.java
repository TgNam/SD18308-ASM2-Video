package org.example.asm_2.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ctsp", schema = "dbo", catalog = "DB_Java4_SD18308")
public class CtspEntity extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "id_sp")
    private SanPhamEntity sanPham;

    @ManyToOne
    @JoinColumn(name = "id_mau_sac")
    private MauSacEntity mauSac;

    @ManyToOne
    @JoinColumn(name = "id_size")
    private SizeEntity size;

    @Basic
    @Column(name = "gia_ban")
    private BigDecimal giaBan;
    @Basic
    @Column(name = "so_luong_ton")
    private Integer soLuongTon;

    @OneToMany(mappedBy = "ctspEntity")
    List<HdctEntity> hdctEntity;

}
