package vn.isofh.may.tho.exception;

import vn.isofh.common.exception.BaseException;
import vn.isofh.common.msg.Msg;

public class DmModelException extends BaseException {

  private final static int ERROR_CODE = 2100;

  private DmModelException(int code, String message) {
    super(ERROR_CODE + code, message);
  }

  private DmModelException(int code, String message, Object data) {
    super(ERROR_CODE + code, message, data);
  }

  public DmModelException(String message) {
    super(ERROR_CODE, message);
  }

  public DmModelException(String message, Object data) {
    super(ERROR_CODE, message, data);
  }

  public static class MissingName extends DmModelException {

    public MissingName() {
      super(1, Msg.getMessage("dm.model.missing.ten"));
    }
  }

  public static class DuplicateName extends DmModelException {

    public DuplicateName(Object[] args) {
      super(2, Msg.getMessage("dm.model.duplicate.ten", args));
    }
  }

  public static class HasUsed extends DmModelException {

    public HasUsed(String message) {
      super(3, Msg.getMessage(message));
    }
  }
}
