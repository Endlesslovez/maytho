package vn.isofh.may.tho.exception;

import vn.isofh.common.exception.BaseException;
import vn.isofh.common.msg.Msg;

public class PhuongPhapXetNghiemException extends BaseException {

   private final static int ERROR_CODE = 3100;

   public PhuongPhapXetNghiemException(int code, String mes) {
      super(ERROR_CODE + code, mes);
   }

   public static class ExistRecord extends PhuongPhapXetNghiemException {

      public ExistRecord() {
         super(ERROR_CODE, Msg.getMessage("loai.thiet.bi.missing.ten"));
      }
   }

}
