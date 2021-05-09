package vn.isofh.may.tho.exception;

import vn.isofh.common.exception.BaseException;
import vn.isofh.common.msg.Msg;

public class DmTenVatTuYTeException extends BaseException {

  private final static int ERROR_CODE = 3000;

  private DmTenVatTuYTeException(int code, String message) {
    super(ERROR_CODE + code, message);
  }

  private DmTenVatTuYTeException(int code, String message, Object data) {
    super(ERROR_CODE + code, message, data);
  }

  public DmTenVatTuYTeException(String message) {
    super(ERROR_CODE, message);
  }

  public DmTenVatTuYTeException(String message, Object data) {
    super(ERROR_CODE, message, data);
  }

  public static class MissingName extends DmTenVatTuYTeException {

    public MissingName() {
      super(1, Msg.getMessage("ten.vtyt.missing.ten"));
    }
  }

  public static class DuplicateName extends DmTenVatTuYTeException {

    public DuplicateName() {
      super(2, Msg.getMessage("ten.vtyt.duplicate.ten"));
    }
  }

  public static class MissingCode extends DmTenVatTuYTeException {

    public MissingCode() {
      super(3, Msg.getMessage("ten.vtyt.missing.ma"));
    }
  }

  public static class DuplicateCode extends DmTenVatTuYTeException {

    public DuplicateCode() {
      super(4, Msg.getMessage("ten.vtyt.duplicate.ma"));
    }
  }

  public static class InvalidCode extends DmTenVatTuYTeException {

    public InvalidCode() {
      super(5, Msg.getMessage("ten.vtyt.invalid.ma"));
    }
  }

  public static class MissingDmLoaiThietBiId extends DmTenVatTuYTeException {

    public MissingDmLoaiThietBiId() {
      super(6, Msg.getMessage("ten.vtyt.missing.dmLoaiThietBiId"));
    }
  }

  public static class ExistRecord extends DmTenVatTuYTeException {

    public ExistRecord() {
      super(7, Msg.getMessage("ton.tai.ban.ghi"));
    }
  }
}
