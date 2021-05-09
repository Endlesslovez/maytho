package vn.isofh.may.tho.enums;

import vn.isofh.common.enums.IEnum;

public enum LoaiThietBiEnum implements IEnum {

  THIET_BI((short) 0),
  NHOM_VTYT((short) 1);

  private Short value;

  LoaiThietBiEnum(Short value) {
    this.value = value;
  }

  @Override
  public Short getValue() {
    return this.value;
  }
}
