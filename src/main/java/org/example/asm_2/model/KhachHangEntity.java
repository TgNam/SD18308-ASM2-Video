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
@Table(name = "khach_hang", schema = "dbo", catalog = "DB_Java4_SD18308")
public class KhachHangEntity extends BaseEntity {
    @Basic
    @Column(name = "ho_ten")
    private String hoTen;
    @Basic
    @Column(name = "dia_chi")
    private String diaChi;
    @Basic
    @Column(name = "sdt")
    private String sdt;
    @OneToMany(mappedBy = "khachHang")
    List<HoaDonEntity> hoaDonEntity;
}
