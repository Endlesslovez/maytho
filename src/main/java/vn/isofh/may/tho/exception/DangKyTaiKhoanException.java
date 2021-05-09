package vn.isofh.may.tho.exception;

import vn.isofh.common.exception.BaseException;
import vn.isofh.common.msg.Msg;

public class DangKyTaiKhoanException extends BaseException {

  private final static int ERROR_CODE = 3300;

  private DangKyTaiKhoanException(int code, String message) {
    super(ERROR_CODE + code, message);
  }

  private DangKyTaiKhoanException(int code, String message, Object data) {
    super(ERROR_CODE + code, message, data);
  }

  public DangKyTaiKhoanException(String message) {
    super(ERROR_CODE, message);
  }

  public DangKyTaiKhoanException(String message, Object data) {
    super(ERROR_CODE, message, data);
  }

  public static class DuplicateEmail extends DangKyTaiKhoanException {

    public DuplicateEmail(Object[] args) {
      super(1, Msg.getMessage("cong.ty.ttb.duplicate.email", args));
    }
  }


  public static class DuplicateMaSoThue extends DangKyTaiKhoanException {

    public DuplicateMaSoThue(Object[] args) {
      super(2, Msg.getMessage("cong.ty.ttb.duplicate.ma.so.thue", args));
    }
  }

  public static class IsActived extends DangKyTaiKhoanException {

    public IsActived() {
      super(3, Msg.getMessage("dang.ky.tai.khoan.da.tao"));
    }
  }

  public static class MissingMaSoThue extends DangKyTaiKhoanException {

    public MissingMaSoThue() {
      super(4, Msg.getMessage("dang.ky.tai.khoan.missing.ma.so.thue"));
    }
  }

  public static class MissingEmail extends DangKyTaiKhoanException {

    public MissingEmail() {
      super(4, Msg.getMessage("dang.ky.tai.khoan.missing.email"));
    }
  }

  public static class MissingSoDienThoai extends DangKyTaiKhoanException {

    public MissingSoDienThoai() {
      super(5, Msg.getMessage("dang.ky.tai.khoan.missing.so.dien.thoai"));
    }
  }


  public static class MissingSoCMND extends DangKyTaiKhoanException {

    public MissingSoCMND() {
      super(6, Msg.getMessage("dang.ky.tai.khoan.missing.cmnd"));
    }
  }


  public static class MissingTenDoanhNghiep extends DangKyTaiKhoanException {

    public MissingTenDoanhNghiep() {
      super(7, Msg.getMessage("dang.ky.tai.khoan.missing.ten.doanh.nghiep"));
    }
  }


  public static class MissingDiaChi extends DangKyTaiKhoanException {

    public MissingDiaChi() {
      super(8, Msg.getMessage("dang.ky.tai.khoan.missing.dia.chi"));
    }
  }


  public static class MissingNhomSanPham extends DangKyTaiKhoanException {

    public MissingNhomSanPham() {
      super(8, Msg.getMessage("dang.ky.tai.khoan.missing.nhom.san.pham"));
    }
  }

  public static class MissingFileTemplateEmail extends DangKyTaiKhoanException {

    public MissingFileTemplateEmail() {
      super(8, Msg.getMessage("dang.ky.tai.khoan.missing.template.email"));
    }
  }

}
