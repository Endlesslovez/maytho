package vn.isofh.may.tho.exception;

import vn.isofh.common.exception.BaseException;
import vn.isofh.common.msg.Msg;

public class DmThietBiException extends BaseException {

  private final static int ERROR_CODE = 1600;

  private DmThietBiException(int code, String message) {
    super(ERROR_CODE + code, message);
  }

  private DmThietBiException(int code, String message, Object data) {
    super(ERROR_CODE + code, message, data);
  }

  public DmThietBiException(String message) {
    super(ERROR_CODE, message);
  }

  public DmThietBiException(String message, Object data) {
    super(ERROR_CODE, message, data);
  }

  public static class MissingName extends DmThietBiException {

    public MissingName() {
      super(1, Msg.getMessage("dm.thiet.bi.missing.ten"));
    }
  }

  public static class DuplicateName extends DmThietBiException {

    public DuplicateName(Object[] args) {
      super(2, Msg.getMessage("dm.thiet.bi.duplicate.ten", args));
    }
  }

  public static class HasUsed extends DmThietBiException {

    public HasUsed(String message) {
      super(3, Msg.getMessage(message));
    }
  }

  public static class MissingTenVietTat extends DmThietBiException {

    public MissingTenVietTat() {
      super(4, Msg.getMessage("dm.thiet.bi.missing.ten.viet.tat"));
    }
  }
  public static class DuplicateMaNhomVTYT extends DmThietBiException {

    public DuplicateMaNhomVTYT() {
      super(5, Msg.getMessage("dm.thiet.bi.duplicate.ma.nhom.vtyt"));
    }
  }
}
