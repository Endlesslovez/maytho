package vn.isofh.may.tho.enums;

import vn.isofh.common.enums.IEnum;

public enum DeviceEnum implements IEnum {

  TTB_CSYT((short) 1), TTB_CT((short) 2), TTB_INTRO((short) 3), TTB_VT_TIEU_HAO((short) 4);

  private Short value;

  DeviceEnum(Short value) {
    this.value = value;
  }

  @Override
  public Short getValue() {
    return this.value;
  }
}
