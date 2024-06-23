package org.example.asm_2.service;


import org.example.asm_2.model.KhachHangEntity;

public interface KhachHangService  extends Service{
  KhachHangEntity khachHangSearch(String sdt);
}
