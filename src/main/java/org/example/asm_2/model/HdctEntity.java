package org.example.asm_2.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "hdct", schema = "dbo", catalog = "DB_Java4_SD18308")
public class HdctEntity extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "id_hoa_don")
    private HoaDonEntity hoaDon;
    @ManyToOne
    @JoinColumn(name = "id_ctsp")
    private CtspEntity ctspEntity;
    @Basic
    @Column(name = "so_luong_mua")
    private Integer soLuongMua;
    @Basic
    @Column(name = "gia_ban")
    private BigDecimal giaBan;
    @Basic
    @Column(name = "tong_tien")
    private BigDecimal tongTien;



}
