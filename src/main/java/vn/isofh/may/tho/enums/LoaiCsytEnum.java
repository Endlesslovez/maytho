package vn.isofh.may.tho.enums;

import vn.isofh.common.enums.IEnum;

public enum LoaiCsytEnum implements IEnum {
  BENH_VIEN((short) 10), PHONG_KHAM((short) 20), TRUNG_TAM_Y_TE((short) 30), KHAC((short) 40);

  private Short value;

  LoaiCsytEnum(Short value) {
    this.value = value;
  }

  @Override
  public Short getValue() {
    return this.value;
  }
}