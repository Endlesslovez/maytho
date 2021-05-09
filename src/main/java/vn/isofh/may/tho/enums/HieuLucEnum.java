package vn.isofh.may.tho.enums;

import vn.isofh.common.enums.IEnum;

public enum HieuLucEnum implements IEnum {
   Co((short) 0), KHONG((short) 1);

   private Short value;

   HieuLucEnum(Short value) {
      this.value = value;
   }


   @Override
   public Short getValue() {
      return this.value;
   }
}
