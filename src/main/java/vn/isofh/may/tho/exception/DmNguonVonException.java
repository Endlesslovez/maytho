package vn.isofh.may.tho.exception;

import vn.isofh.common.exception.BaseException;
import vn.isofh.common.msg.Msg;

public class DmNguonVonException extends BaseException {

  private final static int ERROR_CODE = 1100;

  private DmNguonVonException(int code, String message) {
    super(ERROR_CODE + code, message);
  }

  private DmNguonVonException(int code, String message, Object data) {
    super(ERROR_CODE + code, message, data);
  }

  public DmNguonVonException(String message) {
    super(ERROR_CODE, message);
  }

  public DmNguonVonException(String message, Object data) {
    super(ERROR_CODE, message, data);
  }

  public static class MissingName extends DmNguonVonException {

    public MissingName() {
      super(1, Msg.getMessage("don.vi.tinh.missing.ten"));
    }
  }

  public static class DuplicateName extends DmNguonVonException {

    public DuplicateName(Object[] args) {
      super(2, Msg.getMessage("don.vi.tinh.duplicate.ten", args));
    }
  }

  public static class HasUsed extends DmNguonVonException {

    public HasUsed(String message) {
      super(3, Msg.getMessage(message));
    }
  }
}
