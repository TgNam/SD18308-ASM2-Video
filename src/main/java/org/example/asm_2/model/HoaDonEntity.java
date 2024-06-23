package org.example.asm_2.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "hoa_don", schema = "dbo", catalog = "DB_Java4_SD18308")
public class HoaDonEntity extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "id_khach_hang")
    private KhachHangEntity khachHang;
    @Basic
    @Column(name = "dia_chi")
    private String diaChi;
    @Basic
    @Column(name = "so_dien_thoai")
    private String soDienThoai;

    @OneToMany(mappedBy = "hoaDon")
    List<HdctEntity> hdctEntity;

}
