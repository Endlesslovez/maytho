package vn.isofh.may.tho.exception;

import vn.isofh.common.exception.BaseException;

public class ThietBiInVitroException extends BaseException {

  private final static int ERROR_CODE = 1000;

  private ThietBiInVitroException(int code, Throwable cause) {
    super(ERROR_CODE + code, cause);
  }

  private ThietBiInVitroException(int code, String message) {
    super(ERROR_CODE + code, message);
  }

  private ThietBiInVitroException(int code, String message, Object data) {
    super(ERROR_CODE + code, message, data);
  }

  private ThietBiInVitroException(int code, String message, Throwable cause) {
    super(ERROR_CODE + code, message, cause);
  }

  public ThietBiInVitroException(Throwable cause) {
    super(ERROR_CODE, cause);
  }

  public ThietBiInVitroException(String message) {
    super(ERROR_CODE, message);
  }

  public ThietBiInVitroException(String message, Object data) {
    super(ERROR_CODE, message, data);
  }

  public ThietBiInVitroException(String message, Throwable cause) {
    super(ERROR_CODE, message, cause);
  }
}
