package pl.zdzimi.shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.zdzimi.shop.model.data.Amount;
import pl.zdzimi.shop.repository.AmountsRepository;
import pl.zdzimi.shop.service.exception.CommodityNotFoundException;

@Service
@RequiredArgsConstructor
public class AmountsService {

  private final AmountsRepository amountsRepository;

  public void setAmount(Long id, int amount) {
    Amount amountEntity = amountsRepository.findById(id)
        .orElseThrow(() -> new CommodityNotFoundException("Commodity not found id: " + id));
    amountEntity.setAmount(amount);
    amountsRepository.save(amountEntity);
  }

}
