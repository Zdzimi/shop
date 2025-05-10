package pl.zdzimi.shop.service.exception;

public class CannotDeleteCategory extends RuntimeException {

  public CannotDeleteCategory(String message) {
    super(message);
  }

}
