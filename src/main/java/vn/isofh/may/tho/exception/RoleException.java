package vn.isofh.may.tho.exception;

import vn.isofh.common.exception.BaseException;
import vn.isofh.common.msg.Msg;

public class RoleException extends BaseException {

  private final static int ERROR_CODE = 1900;

  private RoleException(int code, String message) {
    super(ERROR_CODE + code, message);
  }

  private RoleException(int code, String message, Object data) {
    super(ERROR_CODE + code, message, data);
  }

  public RoleException(String message) {
    super(ERROR_CODE, message);
  }

  public RoleException(String message, Object data) {
    super(ERROR_CODE, message, data);
  }

  public static class MissingName extends RoleException {

    public MissingName() {
      super(1, Msg.getMessage("role.missing.ten"));
    }
  }

  public static class DuplicateName extends RoleException {

    public DuplicateName(Object[] args) {
      super(2, Msg.getMessage("role.duplicate.ten", args));
    }
  }

  public static class HasUsed extends RoleException {

    public HasUsed(String message) {
      super(3, Msg.getMessage(message));
    }
  }
}
