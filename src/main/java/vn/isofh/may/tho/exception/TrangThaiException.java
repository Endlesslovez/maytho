package vn.isofh.may.tho.exception;

import vn.isofh.common.exception.BaseException;
import vn.isofh.common.msg.Msg;

public class TrangThaiException extends BaseException {

  private final static int ERROR_CODE = 1700;

  private TrangThaiException(int code, String message) {
    super(ERROR_CODE + code, message);
  }

  private TrangThaiException(int code, String message, Object data) {
    super(ERROR_CODE + code, message, data);
  }

  public TrangThaiException(String message) {
    super(ERROR_CODE, message);
  }

  public TrangThaiException(String message, Object data) {
    super(ERROR_CODE, message, data);
  }

  public static class MissingName extends TrangThaiException {

    public MissingName() {
      super(1, Msg.getMessage("tramg.thai.missing.ten"));
    }
  }

  public static class DuplicateName extends TrangThaiException {

    public DuplicateName(Object[] args) {
      super(2, Msg.getMessage("tramg.thai.duplicate.ten", args));
    }
  }

  public static class HasUsed extends TrangThaiException {

    public HasUsed(String message) {
      super(3, Msg.getMessage(message));
    }
  }
}
