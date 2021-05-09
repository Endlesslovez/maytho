package vn.isofh.may.tho.exception;

import vn.isofh.common.exception.BaseException;
import vn.isofh.common.msg.Msg;

public class DmDonViTinhException extends BaseException {

  private final static int ERROR_CODE = 1100;

  private DmDonViTinhException(int code, String message) {
    super(ERROR_CODE + code, message);
  }

  private DmDonViTinhException(int code, String message, Object data) {
    super(ERROR_CODE + code, message, data);
  }

  public DmDonViTinhException(String message) {
    super(ERROR_CODE, message);
  }

  public DmDonViTinhException(String message, Object data) {
    super(ERROR_CODE, message, data);
  }

  public static class MissingName extends DmDonViTinhException {

    public MissingName() {
      super(1, Msg.getMessage("don.vi.tinh.missing.ten"));
    }
  }

  public static class DuplicateName extends DmDonViTinhException {

    public DuplicateName(Object[] args) {
      super(2, Msg.getMessage("don.vi.tinh.duplicate.ten", args));
    }
  }

  public static class HasUsed extends DmDonViTinhException {

    public HasUsed(String message) {
      super(3, Msg.getMessage(message));
    }
  }
}
