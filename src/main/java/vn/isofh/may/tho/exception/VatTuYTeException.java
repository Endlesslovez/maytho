package vn.isofh.may.tho.exception;

import vn.isofh.common.exception.BaseException;
import vn.isofh.common.msg.Msg;

public class VatTuYTeException extends BaseException {

  private final static int ERROR_CODE = 3400;

  private VatTuYTeException(int code, String message) {
    super(ERROR_CODE + code, message);
  }

  private VatTuYTeException(int code, String message, Object data) {
    super(ERROR_CODE + code, message, data);
  }

  public VatTuYTeException(String message) {
    super(ERROR_CODE, message);
  }

  public VatTuYTeException(String message, Object data) {
    super(ERROR_CODE, message, data);
  }


  public static class DuplicateMSanPham extends VatTuYTeException {

    public DuplicateMSanPham(String message) {
      super(1, Msg.getMessage(message));
    }
  }
}
