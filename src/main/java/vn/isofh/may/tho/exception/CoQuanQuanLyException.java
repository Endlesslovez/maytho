package vn.isofh.may.tho.exception;

import vn.isofh.common.exception.BaseException;
import vn.isofh.common.msg.Msg;

public class CoQuanQuanLyException extends BaseException {

  private final static int ERROR_CODE = 1300;

  private CoQuanQuanLyException(int code, String message) {
    super(ERROR_CODE + code, message);
  }

  private CoQuanQuanLyException(int code, String message, Object data) {
    super(ERROR_CODE + code, message, data);
  }

  public CoQuanQuanLyException(String message) {
    super(ERROR_CODE, message);
  }

  public CoQuanQuanLyException(String message, Object data) {
    super(ERROR_CODE, message, data);
  }

  public static class MissingName extends CoQuanQuanLyException {

    public MissingName() {
      super(1, Msg.getMessage("co.quan.quan.ly.missing.ten"));
    }
  }

  public static class MissingTinhThanhPho extends CoQuanQuanLyException {

    public MissingTinhThanhPho() {
      super(2, Msg.getMessage("co.quan.quan.ly.missing.tinh.thanh.pho"));
    }
  }

  public static class MissingQuanHuyen extends CoQuanQuanLyException {

    public MissingQuanHuyen() {
      super(3, Msg.getMessage("co.quan.quan.ly.missing.quan.huyen"));
    }
  }

  public static class MissingSoDienThoai extends CoQuanQuanLyException {

    public MissingSoDienThoai() {
      super(4, Msg.getMessage("co.quan.quan.ly.missing.so.dien.thoai"));
    }
  }

  public static class DuplicateName extends CoQuanQuanLyException {

    public DuplicateName(Object[] args) {
      super(4, Msg.getMessage("co.quan.quan.ly.duplicate.ten", args));
    }
  }

  public static class DuplicateMaSoThue extends CoQuanQuanLyException {

    public DuplicateMaSoThue(Object[] args) {
      super(5, Msg.getMessage("co.quan.quan.ly.duplicate.ma.so.thue", args));
    }
  }

  public static class HasUsed extends CoQuanQuanLyException {

    public HasUsed(String message) {
      super(3, Msg.getMessage(message));
    }
  }
}
