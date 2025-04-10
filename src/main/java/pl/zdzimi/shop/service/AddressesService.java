package pl.zdzimi.shop.service;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.zdzimi.shop.model.AddressDTO;
import pl.zdzimi.shop.model.data.Address;
import pl.zdzimi.shop.model.data.User;
import pl.zdzimi.shop.repository.AddressesRepository;
import pl.zdzimi.shop.securiry.PrincipalUser;
import pl.zdzimi.shop.service.exception.AddressNotFoundException;

@Service
@RequiredArgsConstructor
public class AddressesService {

  private final AddressesRepository addressesRepository;

  public Collection<AddressDTO> findAddress(User user) {
    return addressesRepository.findByUser(user).stream()
        .map(Mapper::mapToAddressDTO)
        .collect(Collectors.toList());
  }

  public void save(AddressDTO addressDTO, User user) {
    Address address = Mapper.mapToAddress(addressDTO);
    address.setUser(user);
    addressesRepository.save(address);
  }

  public void delete(User user, Long id) {
    Address address = addressesRepository.findByIdAndUser(id, user)
        .orElseThrow(() -> new AddressNotFoundException(id));
    address.setActual(false);
    addressesRepository.save(address);
  }

  public Address findAddress(User user, Long addressId) {
    return addressesRepository.findByUserAndId(user, addressId)
        .orElseThrow(() -> new AddressNotFoundException(addressId));
  }

  private static class Mapper {

    static AddressDTO mapToAddressDTO(Address address) {
      AddressDTO addressDTO = new AddressDTO();
      addressDTO.setId(address.getId());
      addressDTO.setProvince(address.getProvince());
      addressDTO.setCity(address.getCity());
      addressDTO.setStreet(address.getStreet());
      addressDTO.setBuildingNumber(address.getBuildingNumber());
      addressDTO.setApartmentNumber(address.getApartmentNumber());
      addressDTO.setZipCode(address.getZipCode());
      return addressDTO;
    }

    static Address mapToAddress(AddressDTO addressDTO) {
      Address address = new Address();
      address.setId(addressDTO.getId());
      address.setProvince(addressDTO.getProvince());
      address.setCity(addressDTO.getCity());
      address.setStreet(addressDTO.getStreet());
      address.setBuildingNumber(addressDTO.getBuildingNumber());
      address.setApartmentNumber(addressDTO.getApartmentNumber());
      address.setZipCode(addressDTO.getZipCode());
      address.setActual(true);
      return address;
    }

  }

}
