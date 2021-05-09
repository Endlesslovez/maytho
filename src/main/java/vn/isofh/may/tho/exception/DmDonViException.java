package vn.isofh.may.tho.exception;

import vn.isofh.common.exception.BaseException;
import vn.isofh.common.msg.Msg;

public class DmDonViException extends BaseException {

  private final static int ERROR_CODE = 1800;

  private DmDonViException(int code, String message) {
    super(ERROR_CODE + code, message);
  }

  private DmDonViException(int code, String message, Object data) {
    super(ERROR_CODE + code, message, data);
  }

  public DmDonViException(String message) {
    super(ERROR_CODE, message);
  }

  public DmDonViException(String message, Object data) {
    super(ERROR_CODE, message, data);
  }

  public static class MissingName extends DmDonViException {

    public MissingName() {
      super(1, Msg.getMessage("co.so.y.te.missing.ten"));
    }
  }

  public static class DuplicateName extends DmDonViException {

    public DuplicateName(Object[] args) {
      super(2, Msg.getMessage("co.so.y.te.duplicate.ten", args));
    }
  }

  public static class MissingMa extends DmDonViException {

    public MissingMa() {
      super(3, Msg.getMessage("co.so.y.te.missing.ma"));
    }
  }

  public static class MissingMaSoThue extends DmDonViException {

    public MissingMaSoThue() {
      super(5, Msg.getMessage("co.so.y.te.missing.ma.so.thue"));
    }
  }

  public static class DuplicateMa extends DmDonViException {

    public DuplicateMa(Object[] args) {
      super(4, Msg.getMessage("co.so.y.te.duplicate.ma", args));
    }
  }

  public static class DuplicateMaSoThue extends DmDonViException {

    public DuplicateMaSoThue(Object[] args) {
      super(6, Msg.getMessage("co.so.y.te.duplicate.ma.so.thue", args));
    }
  }

  public static class MissingLoaiDonVi extends DmDonViException {

    public MissingLoaiDonVi() {
      super(5, Msg.getMessage("don.vi.missing.loai.don.vi"));
    }
  }

  public static class HasUsed extends DmDonViException {

    public HasUsed(String message) {
      super(99, Msg.getMessage(message));
    }
  }
}
