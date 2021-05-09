package vn.isofh.may.tho.enums;

import vn.isofh.common.enums.IEnum;

public enum LoaiThongSoPhanTichEnum implements IEnum {

   THONG_SO_PHAN_TICH((short) 0), DICH_XET_NGHIEM((short) 1), MA_HO_SO((short) 2);

   private Short value;

   LoaiThongSoPhanTichEnum(Short value) {
      this.value = value;
   }

   @Override
   public Short getValue() {
      return this.value;
   }
}