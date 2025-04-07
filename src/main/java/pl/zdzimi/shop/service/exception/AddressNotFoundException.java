package pl.zdzimi.shop.service.exception;

public class AddressNotFoundException extends RuntimeException {

  public AddressNotFoundException(Long addressId) {
    super("Nie znaleziono adresu nr " + addressId);
  }

}
