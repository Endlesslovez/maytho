package vn.isofh.may.tho.enums;

import vn.isofh.common.enums.IEnum;

public enum LoaiNhomEnum implements IEnum {
   NHOM_THIET_BI((short) 0), NHOM_TBYT((short)1);

   private Short value;

   LoaiNhomEnum(Short value){
      this.value = value;
   }

   @Override
   public Short getValue() {
      return this.value;
   }
}
