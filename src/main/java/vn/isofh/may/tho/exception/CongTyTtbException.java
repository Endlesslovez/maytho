package vn.isofh.may.tho.exception;

import vn.isofh.common.exception.BaseException;
import vn.isofh.common.msg.Msg;

public class CongTyTtbException extends BaseException {

  private final static int ERROR_CODE = 1400;

  private CongTyTtbException(int code, String message) {
    super(ERROR_CODE + code, message);
  }

  private CongTyTtbException(int code, String message, Object data) {
    super(ERROR_CODE + code, message, data);
  }

  public CongTyTtbException(String message) {
    super(ERROR_CODE, message);
  }

  public CongTyTtbException(String message, Object data) {
    super(ERROR_CODE, message, data);
  }

  public static class MissingName extends CongTyTtbException {

    public MissingName() {
      super(1, Msg.getMessage("cong.ty.ttb.missing.ten"));
    }
  }

  public static class MissingTinhThanhPho extends CongTyTtbException {

    public MissingTinhThanhPho() {
      super(2, Msg.getMessage("cong.ty.ttb.missing.tinh.thanh.pho"));
    }
  }

  public static class MissingQuanHuyen extends CongTyTtbException {

    public MissingQuanHuyen() {
      super(3, Msg.getMessage("cong.ty.ttb.missing.quan.huyen"));
    }
  }

  public static class MissingXaPhuong extends CongTyTtbException {

    public MissingXaPhuong() {
      super(4, Msg.getMessage("cong.ty.ttb.missing.xa.phuong"));
    }
  }

  public static class MissingSoDienThoai extends CongTyTtbException {

    public MissingSoDienThoai() {
      super(5, Msg.getMessage("cong.ty.ttb.missing.so.dien.thoai"));
    }
  }

  public static class MissingMaSoThue extends CongTyTtbException {

    public MissingMaSoThue() {
      super(6, Msg.getMessage("cong.ty.ttb.missing.ma.so.thue"));
    }
  }

  public static class MissingEmail extends CongTyTtbException {

    public MissingEmail() {
      super(7, Msg.getMessage("cong.ty.ttb.missing.email"));
    }
  }

  public static class MissingLoaiCongTy extends CongTyTtbException {

    public MissingLoaiCongTy() {
      super(8, Msg.getMessage("cong.ty.ttb.missing.loai.cong.ty"));
    }
  }

  public static class DuplicateName extends CongTyTtbException {

    public DuplicateName(Object[] args) {
      super(9, Msg.getMessage("cong.ty.ttb.duplicate.ten", args));
    }
  }

  public static class DuplicateMaSoThue extends CongTyTtbException {

    public DuplicateMaSoThue(Object[] args) {
      super(10, Msg.getMessage("cong.ty.ttb.duplicate.ma.so.thue", args));
    }
  }

  public static class HasUsed extends CongTyTtbException {

    public HasUsed(String message) {
      super(11, Msg.getMessage(message));
    }
  }
}
