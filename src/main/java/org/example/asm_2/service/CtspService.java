package org.example.asm_2.service;

import org.example.asm_2.model.CtspEntity;

import java.util.List;

public interface CtspService extends Service{
    List<CtspEntity> findCTSP();
}
