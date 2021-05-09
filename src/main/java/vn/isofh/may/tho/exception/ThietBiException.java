package vn.isofh.may.tho.exception;

import vn.isofh.common.exception.BaseException;
import vn.isofh.common.msg.Msg;

public class ThietBiException extends BaseException {

  private final static int ERROR_CODE = 2000;

  private ThietBiException(int code, String message) {
    super(ERROR_CODE + code, message);
  }

  private ThietBiException(int code, String message, Object data) {
    super(ERROR_CODE + code, message, data);
  }

  public ThietBiException(String message) {
    super(ERROR_CODE, message);
  }

  public ThietBiException(String message, Object data) {
    super(ERROR_CODE, message, data);
  }

  public static class MissingDonVi extends ThietBiException {

    public MissingDonVi() {
      super(1, Msg.getMessage("thiet.bi.missing.don.vi"));
    }
  }
}
