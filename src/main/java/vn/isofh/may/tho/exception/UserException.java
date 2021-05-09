package vn.isofh.may.tho.exception;

import vn.isofh.common.exception.BaseException;
import vn.isofh.common.msg.Msg;

public class UserException extends BaseException {

  private final static int ERROR_CODE = 1500;

  private UserException(int code, String message) {
    super(ERROR_CODE + code, message);
  }

  private UserException(int code, String message, Object data) {
    super(ERROR_CODE + code, message, data);
  }

  public UserException(String message) {
    super(ERROR_CODE, message);
  }

  public UserException(String message, Object data) {
    super(ERROR_CODE, message, data);
  }

  public static class DuplicateUsername extends UserException {

    public DuplicateUsername(String message) {
      super(0, message);
    }
  }

  public static class MissingUsername extends UserException {

    public MissingUsername() {
      super(1, Msg.getMessage("user.missing.user.name"));
    }
  }

  public static class InvalidUsername extends UserException {

    public InvalidUsername(String message) {
      super(2, message);
    }
  }

  public static class InvalidPassword extends UserException {

    public InvalidPassword(String message) {
      super(3, message);
    }
  }

  public static class NotExistsByUsername extends UserException {

    public NotExistsByUsername(String message) {
      super(4, message);
    }
  }

  public static class InvalidUser extends UserException {

    public InvalidUser(String message) {
      super(5, message);
    }
  }

  public static class UserHasLocked extends BaseException {

    public UserHasLocked(String message) {
      super(ERROR_CODE + 8, message);
    }
  }
}
