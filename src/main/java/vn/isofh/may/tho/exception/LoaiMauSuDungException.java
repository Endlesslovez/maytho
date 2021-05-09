package vn.isofh.may.tho.exception;

import vn.isofh.common.exception.BaseException;
import vn.isofh.common.msg.Msg;

public class LoaiMauSuDungException extends BaseException {

   private final static int ERROR_CODE = 3200;

   public LoaiMauSuDungException(int code, String cause) {
      super(ERROR_CODE + code, cause);
   }

   public static class DuplicateTen extends LoaiMauSuDungException{

      public DuplicateTen() {
         super(1, Msg.getMessage("loai.mau.su.dung.duplicate.ten"));
      }
   }
}
