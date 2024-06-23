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
@Table(name = "mau_sac", schema = "dbo", catalog = "DB_Java4_SD18308")
public class MauSacEntity extends  BaseEntity {
    @Basic
    @Column(name = "ma_mau")
    private String maMau;
    @Basic
    @Column(name = "ten_mau")
    private String tenMau;

    @OneToMany(mappedBy = "mauSac")
    List<CtspEntity> ctspEntity;
}
