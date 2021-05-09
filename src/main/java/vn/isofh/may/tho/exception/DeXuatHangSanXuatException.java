package vn.isofh.may.tho.exception;

import vn.isofh.common.exception.BaseException;
import vn.isofh.common.msg.Msg;

public class DeXuatHangSanXuatException extends BaseException {

  private final static int ERROR_CODE = 3900;

  private DeXuatHangSanXuatException(int code, String message) {
    super(ERROR_CODE + code, message);
  }

  private DeXuatHangSanXuatException(int code, String message, Object data) {
    super(ERROR_CODE + code, message, data);
  }

  public DeXuatHangSanXuatException(String message) {
    super(ERROR_CODE, message);
  }

  public DeXuatHangSanXuatException(String message, Object data) {
    super(ERROR_CODE, message, data);
  }

  public static class MissingName extends DeXuatHangSanXuatException {

    public MissingName() {
      super(1, Msg.getMessage("de.xuat.hang.sx.missing.name"));
    }
  }

  public static class MissingReason extends DeXuatHangSanXuatException {

    public MissingReason() {
      super(2, Msg.getMessage("de.xuat.hang.sx.missing.reason"));
    }
  }

  public static class InvalidDto extends DeXuatHangSanXuatException {

    public InvalidDto() {
      super(3, Msg.getMessage("de.xuat.hang.sx.invalid.hang.san.xuat"));
    }
  }


}
