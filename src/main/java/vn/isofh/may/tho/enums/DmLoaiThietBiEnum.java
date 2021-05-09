package vn.isofh.may.tho.enums;

import vn.isofh.common.enums.IEnum;

public enum DmLoaiThietBiEnum implements IEnum {

   THIET_BI((short)0), LOAI_VTYT_I((short)1), LOAI_VTTH_II((short) 2);

   private Short value;

   DmLoaiThietBiEnum(Short value){
      this.value = value;
   }

   @Override
   public Short getValue() {
      return this.value;
   }
}
