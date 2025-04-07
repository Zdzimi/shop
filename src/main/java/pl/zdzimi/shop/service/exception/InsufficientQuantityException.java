package pl.zdzimi.shop.service.exception;

public class InsufficientQuantityException extends RuntimeException {

  public InsufficientQuantityException(String message) {
    super(message);
  }

}
