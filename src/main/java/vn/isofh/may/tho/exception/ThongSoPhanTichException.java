package vn.isofh.may.tho.exception;

import vn.isofh.common.exception.BaseException;
import vn.isofh.common.msg.Msg;

public class ThongSoPhanTichException extends BaseException {

   private final static int ERROR_CODE = 3100;

   public ThongSoPhanTichException(int code, String cause) {
      super(ERROR_CODE + code, cause);
   }

   public static class DuplicateTen extends ThongSoPhanTichException{

      public DuplicateTen() {
         super(1, Msg.getMessage("thong.so.phan.tich.duplicate.ten"));
      }
   }
}
