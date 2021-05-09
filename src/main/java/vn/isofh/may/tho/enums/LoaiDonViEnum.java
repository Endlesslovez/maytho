package vn.isofh.may.tho.enums;

import vn.isofh.common.enums.IEnum;

public enum LoaiDonViEnum implements IEnum {
  CO_SO_Y_TE((short) 10),
  CONG_TY_TTB((short) 20),
  CO_QUAN_QUAN_LY((short) 30),
  HANG_SAN_XUAT((short) 40);

  private Short value;

  LoaiDonViEnum(Short value) {
    this.value = value;
  }

  @Override
  public Short getValue() {
    return this.value;
  }
}