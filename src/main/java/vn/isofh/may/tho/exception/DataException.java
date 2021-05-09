package vn.isofh.may.tho.exception;

import vn.isofh.common.msg.Msg;

public class DataException extends vn.isofh.common.exception.DataException {

  private DataException(int code, String message) {
    super(code, message);
  }

  public static class NotFoundEntityByMa extends DataException {

    public NotFoundEntityByMa(String ma, String entity) {
      super(1, Msg.getMessage("AbstractBaseService.notFoundEntityByMa", new Object[]{ma, entity}));
    }
  }

  public static class NotFoundEntityByTen extends DataException {

    public NotFoundEntityByTen(String ten, String entity) {
      super(1, Msg.getMessage("AbstractBaseService.notFoundEntityByTen", new Object[]{ten, entity}));
    }
  }
}
