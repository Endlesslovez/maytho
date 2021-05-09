package vn.isofh.may.tho.exception;

import vn.isofh.common.exception.BaseException;
import vn.isofh.common.msg.Msg;

public class DmKhoangGiaException extends BaseException {

   private final static int ERROR_CODE = 2700;

   private DmKhoangGiaException(int code, String message) {
      super(ERROR_CODE + code, message);
   }

   private DmKhoangGiaException(int code, String message, Object data) {
      super(ERROR_CODE + code, message, data);
   }

   public DmKhoangGiaException(String message) {
      super(ERROR_CODE, message);
   }

   public DmKhoangGiaException(String message, Object data) {
      super(ERROR_CODE, message, data);
   }

   public static class DuplicateKhoangGia extends DmKhoangGiaException {

      public DuplicateKhoangGia() {
         super(2, Msg.getMessage("dm.khoang.gia.duplicate.khoang.gia"));
      }
   }
}
