package org.example.asm_2.service;


import org.example.asm_2.model.HdctEntity;

import java.util.List;

public interface HdctService extends Service {
    List<HdctEntity> Hdct(int id);

}
