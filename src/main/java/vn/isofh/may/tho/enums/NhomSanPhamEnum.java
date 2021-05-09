package vn.isofh.may.tho.enums;

import vn.isofh.common.enums.IEnum;

public enum NhomSanPhamEnum implements IEnum {

  TRANG_THIET_BI_YT((short) 1), VAT_TU_TIEU_HAO((short) 2), IVD((short) 3);

  private Short value;

  NhomSanPhamEnum(Short value) {
    this.value = value;
  }

  @Override
  public Short getValue() {
    return this.value;
  }


}
