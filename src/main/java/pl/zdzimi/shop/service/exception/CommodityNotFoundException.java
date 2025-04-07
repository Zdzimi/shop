package pl.zdzimi.shop.service.exception;

public class CommodityNotFoundException extends RuntimeException {

  public CommodityNotFoundException(String message) {
    super(message);
  }

  public CommodityNotFoundException(Long id) {
    super("Nie znaleziono towaru nr " + id);
  }

}
