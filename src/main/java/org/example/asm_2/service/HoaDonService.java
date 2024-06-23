package org.example.asm_2.service;


import org.example.asm_2.model.HoaDonEntity;

import java.util.List;

public interface HoaDonService extends Service {
    List<Object[]> findBill();
}
