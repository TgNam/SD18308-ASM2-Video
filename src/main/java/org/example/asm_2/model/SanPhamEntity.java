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
@Table(name = "san_pham", schema = "dbo", catalog = "DB_Java4_SD18308")
public class SanPhamEntity extends BaseEntity {
    @Basic
    @Column(name = "ma_san_pham")
    private String maSanPham;
    @Basic
    @Column(name = "ten_san_pham")
    private String tenSanPham;
    @ManyToOne
    @JoinColumn(name = "id_danh_muc")
    private DanhMucEntity danhMucEntity;

    @OneToMany(mappedBy = "sanPham")
    List<CtspEntity> ctspEntity;

}
