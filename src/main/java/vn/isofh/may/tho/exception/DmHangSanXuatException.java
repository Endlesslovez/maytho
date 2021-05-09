package vn.isofh.may.tho.exception;

import vn.isofh.common.exception.BaseException;
import vn.isofh.common.msg.Msg;

public class DmHangSanXuatException extends BaseException {

  private final static int ERROR_CODE = 1200;

  private DmHangSanXuatException(int code, String message) {
    super(ERROR_CODE + code, message);
  }

  private DmHangSanXuatException(int code, String message, Object data) {
    super(ERROR_CODE + code, message, data);
  }

  public DmHangSanXuatException(String message) {
    super(ERROR_CODE, message);
  }

  public DmHangSanXuatException(String message, Object data) {
    super(ERROR_CODE, message, data);
  }

  public static class MissingName extends DmHangSanXuatException {

    public MissingName() {
      super(1, Msg.getMessage("hang.san.xuat.missing.ten"));
    }
  }

  public static class DuplicateName extends DmHangSanXuatException {

    public DuplicateName(Object[] args) {
      super(2, Msg.getMessage("hang.san.xuat.duplicate.ten", args));
    }
  }

  public static class HasUsed extends DmHangSanXuatException {

    public HasUsed(String message) {
      super(3, Msg.getMessage(message));
    }
  }
}
