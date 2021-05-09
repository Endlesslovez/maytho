package vn.isofh.may.tho.exception;

import vn.isofh.common.exception.BaseException;
import vn.isofh.common.msg.Msg;

public class DmLoaiThietBiException extends BaseException {

  private final static int ERROR_CODE = 1000;

  private DmLoaiThietBiException(int code, String message) {
    super(ERROR_CODE + code, message);
  }

  private DmLoaiThietBiException(int code, String message, Object data) {
    super(ERROR_CODE + code, message, data);
  }

  public DmLoaiThietBiException(String message) {
    super(ERROR_CODE, message);
  }

  public DmLoaiThietBiException(String message, Object data) {
    super(ERROR_CODE, message, data);
  }

  public static class MissingName extends DmLoaiThietBiException {

    public MissingName() {
      super(1, Msg.getMessage("loai.thiet.bi.missing.ten"));
    }
  }

  public static class DuplicateName extends DmLoaiThietBiException {

    public DuplicateName(Object[] args) {
      super(2, Msg.getMessage("loai.thiet.bi.duplicate.ten", args));
    }
  }

  public static class HasUsed extends DmLoaiThietBiException {

    public HasUsed(String message) {
      super(3, Msg.getMessage(message));
    }
  }

  public static class MissingDmThietBi extends DmLoaiThietBiException {

    public MissingDmThietBi() {
      super(4, Msg.getMessage("loai.thiet.bi.missing.thiet.bi"));
    }
  }

  public static class ExistThietBi extends DmLoaiThietBiException {

    public ExistThietBi() {
      super(5, Msg.getMessage("loai.thiet.bi.ton.tai.thiet.bi"));
    }
  }

  public static class ExistDmLoaiThietBiIdAtDmTenVTYT extends DmLoaiThietBiException {

    public ExistDmLoaiThietBiIdAtDmTenVTYT() {
      super(6, Msg.getMessage("loai.thiet.bi.ton.tai.id"));
    }
  }

  public static class DuplicateCode extends DmLoaiThietBiException {

    public DuplicateCode() {
      super(7, Msg.getMessage("loai.VTYT.duplicate.ma"));
    }
  }
}
