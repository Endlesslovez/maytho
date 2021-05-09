package vn.isofh.may.tho.exception;

import vn.isofh.common.exception.BaseException;
import vn.isofh.common.msg.Msg;

public class DmGioiThieuHeThongException extends BaseException {
   private final static int ERROR_CODE = 2800;

   private DmGioiThieuHeThongException(int code, String message) {
      super(ERROR_CODE + code, message);
   }

   private DmGioiThieuHeThongException(int code, String message, Object data) {
      super(ERROR_CODE + code, message, data);
   }

   public DmGioiThieuHeThongException(String message) {
      super(ERROR_CODE, message);
   }

   public DmGioiThieuHeThongException(String message, Object data) {
      super(ERROR_CODE, message, data);
   }

   public static class ExistRecord extends DmGioiThieuHeThongException {

      public ExistRecord() {
         super(1, Msg.getMessage("exist.record"));
      }
   }
}
