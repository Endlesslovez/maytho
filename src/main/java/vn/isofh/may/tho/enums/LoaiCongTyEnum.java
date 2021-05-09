package vn.isofh.may.tho.enums;

import vn.isofh.common.enums.IEnum;

public enum LoaiCongTyEnum implements IEnum {
  NHAP_KHAU((short)10), PHAN_PHOI((short)20), SAN_XUAT((short)30);

  private Short value;

  LoaiCongTyEnum(Short value) {
    this.value = value;
  }

  @Override
  public Short getValue() {
    return this.value;
  }
}