package vn.isofh.may.tho.enums;

import vn.isofh.common.enums.IEnum;

public enum PhanLoaiTtbEnum implements IEnum {

  A((short) 1), B((short) 2), C((short) 3), D((short) 4);

  private Short value;

  PhanLoaiTtbEnum(Short value) {
    this.value = value;
  }

  @Override
  public Short getValue() {
    return this.value;
  }
}
