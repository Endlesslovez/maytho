package vn.isofh.may.tho.enums;

import vn.isofh.common.enums.IEnum;

public enum DinhTinhDinhLuongEnum implements IEnum {

   DINH_TINH((short) 1), DINH_LUONG((short) 2), BAN_DINH_LUONG((short) 3), HO_TRO((short) 4);

   private Short value;

   DinhTinhDinhLuongEnum(Short value) {
      this.value = value;
   }

   @Override
   public Short getValue() {
      return this.value;
   }
}
