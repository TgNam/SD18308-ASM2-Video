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
@Table(name = "danh_muc", schema = "dbo", catalog = "DB_Java4_SD18308")
public class DanhMucEntity extends BaseEntity {
    @Basic
    @Column(name = "ma_danh_muc")
    private String maDanhMuc;
    @Basic
    @Column(name = "ten_danh_muc")
    private String tenDanhMuc;
    @OneToMany(mappedBy = "danhMucEntity")
    List<SanPhamEntity> sanPhamEntities;

}
