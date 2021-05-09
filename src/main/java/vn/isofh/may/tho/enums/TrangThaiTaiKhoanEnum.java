package vn.isofh.may.tho.enums;

import vn.isofh.common.enums.IEnum;

public enum TrangThaiTaiKhoanEnum implements IEnum {
  DANG_HOAT_DONG((short)0), DANG_KHOA((short)10);

  private Short value;

  TrangThaiTaiKhoanEnum(Short value) {
    this.value = value;
  }

  @Override
  public Short getValue() {
    return this.value;
  }
}