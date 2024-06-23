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
@Table(name = "size", schema = "dbo", catalog = "DB_Java4_SD18308")
public class SizeEntity extends BaseEntity {
    @Basic
    @Column(name = "ma_size")
    private String maSize;
    @Basic
    @Column(name = "ten_size")
    private String tenSize;
    @OneToMany(mappedBy = "size")
    List<CtspEntity> ctspEntity;
}
