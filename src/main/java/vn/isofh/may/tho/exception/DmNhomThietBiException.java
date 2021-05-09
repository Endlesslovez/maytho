package vn.isofh.may.tho.exception;

import vn.isofh.common.exception.BaseException;
import vn.isofh.common.msg.Msg;

public class DmNhomThietBiException extends BaseException {

   private final static int ERROR_CODE = 1400;

   public DmNhomThietBiException(int code, String cause) {
      super(ERROR_CODE + code , cause);
   }

   public static class DuplicateMa extends DmNhomThietBiException{

      public DuplicateMa() {
         super(1, Msg.getMessage("nhom.thiet.bi.duplicate.ma"));
      }
   }

   public static class DuplicateTen extends DmNhomThietBiException{

      public DuplicateTen() {
         super(1, Msg.getMessage("nhom.thiet.bi.duplicate.ten"));
      }
   }

}
