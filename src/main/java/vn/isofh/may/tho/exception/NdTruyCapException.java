package vn.isofh.may.tho.exception;

import vn.isofh.common.exception.BaseException;
import vn.isofh.common.msg.Msg;

public class NdTruyCapException extends BaseException {

  private final static int ERROR_CODE = 2200;

  private NdTruyCapException(int code, String message) {
    super(ERROR_CODE + code, message);
  }

  private NdTruyCapException(int code, String message, Object data) {
    super(ERROR_CODE + code, message, data);
  }

  public NdTruyCapException(String message) {
    super(ERROR_CODE, message);
  }

  public NdTruyCapException(String message, Object data) {
    super(ERROR_CODE, message, data);
  }

  public static class IpInvalid extends NdTruyCapException {

    public IpInvalid() {
      super(ERROR_CODE + 1, Msg.getMessage("nd.truy.cap.invalid.ip"));
    }
  }
}
