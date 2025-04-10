package pl.zdzimi.shop.service;

import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.zdzimi.shop.model.CommodityDTO;
import pl.zdzimi.shop.model.CreatingCommodity;
import pl.zdzimi.shop.model.PhotoDTO;
import pl.zdzimi.shop.model.SearchingParams;
import pl.zdzimi.shop.model.data.Amount;
import pl.zdzimi.shop.model.data.Commodity;
import pl.zdzimi.shop.model.data.Photo;
import pl.zdzimi.shop.repository.AmountsRepository;
import pl.zdzimi.shop.repository.CategoriesRepository;
import pl.zdzimi.shop.repository.CommoditiesRepository;
import pl.zdzimi.shop.repository.PhotosRepository;
import pl.zdzimi.shop.service.exception.CategoryNotFoundException;
import pl.zdzimi.shop.service.exception.CommodityNotFoundException;

@Service
@RequiredArgsConstructor
public class CommoditiesService {

  private final CommoditiesRepository commoditiesRepository;
  private final CloudinaryService cloudinaryService;
  private final CategoriesRepository categoriesRepository;
  private final AmountsRepository amountsRepository;
  private final PhotosRepository photosRepository;

  public Collection<CommodityDTO> findAll() {
    return commoditiesRepository.findAllCommodities().stream()
        .map(Mapper::mapToDTO)
        .collect(Collectors.toList());
  }

  public Collection<CommodityDTO> findAllAvailable() {
    return commoditiesRepository.findAllAvailable().stream()
        .map(Mapper::mapToDTO)
        .collect(Collectors.toList());
  }

  public Collection<CommodityDTO> findAllAvailableBySearchingParams(SearchingParams searchingParams) {
    if(searchingParams.getCategoryId() != 0) {
      return commoditiesRepository.findAllAvailableBySearchingParams(searchingParams.getName(), searchingParams.getCategoryId()).stream()
          .map(Mapper::mapToDTO)
          .collect(Collectors.toList());
    } else {
      return commoditiesRepository.findAllAvailableByNameLike(searchingParams.getName()).stream()
          .map(Mapper::mapToDTO)
          .collect(Collectors.toList());
    }
  }

  public CommodityDTO findById(Long id) {
    return Mapper.mapToDTO(
        commoditiesRepository.findById(id)
            .orElseThrow(() -> new CommodityNotFoundException("Commodity not found no: " + id))
    );
  }

  @Transactional
  public void saveCommodity(CreatingCommodity creatingCommodity) {
    Commodity commodity = new Commodity();
    commodity.setName(creatingCommodity.getName());
    commodity.setDescription(creatingCommodity.getDescription());
    commodity.setPrice(creatingCommodity.getPrice());
    commodity.setCategory(
        categoriesRepository.findById(creatingCommodity.getCategoryId())
        .orElseThrow(() -> new CategoryNotFoundException("Category not found: " + creatingCommodity.getCategoryId()))
    );
    Commodity save = commoditiesRepository.save(commodity);

    createPhotos(creatingCommodity, save);

    Amount amount = new Amount();
    amount.setAmount(creatingCommodity.getAmount());
    amount.setId(save.getId());
    amountsRepository.save(amount);
  }

  private void createPhotos(CreatingCommodity creatingCommodity, Commodity save) {
    if (!creatingCommodity.getPhotoFirst().getOriginalFilename().equals("")) {
      Photo photo = new Photo();
      photo.setName(creatingCommodity.getPhotoFirstName());
      photo.setDescription(creatingCommodity.getPhotoFirstDescription());
      photo.setUrl(cloudinaryService.uploadFile(creatingCommodity.getPhotoFirst()));
      photo.setHint(creatingCommodity.getPhotoFirstHint());
      photo.setHeight(creatingCommodity.getPhotoFirstHeight());
      photo.setWidth(creatingCommodity.getPhotoFirstWidth());
      photo.setCommodity(save);
      photosRepository.save(photo);
    }
    if (!creatingCommodity.getPhotoSecond().getOriginalFilename().equals("")) {
      Photo photo1 = new Photo();
      photo1.setName(creatingCommodity.getPhotoSecondName());
      photo1.setDescription(creatingCommodity.getPhotoSecondDescription());
      photo1.setUrl(cloudinaryService.uploadFile(creatingCommodity.getPhotoSecond()));
      photo1.setHint(creatingCommodity.getPhotoSecondHint());
      photo1.setHeight(creatingCommodity.getPhotoSecondHeight());
      photo1.setWidth(creatingCommodity.getPhotoSecondWidth());
      photo1.setCommodity(save);
      photosRepository.save(photo1);
    }
  }

  public Commodity find(Long id) {
    return commoditiesRepository.findById(id).orElseThrow(() -> new CommodityNotFoundException(id));
  }

  public CommodityDTO mapToCommodityDTO(Commodity commodity) {
    return Mapper.mapToDTO(commodity);
  }

  private static class Mapper {

    static CommodityDTO mapToDTO(Commodity commodity) {
      CommodityDTO commodityDTO = new CommodityDTO();
      commodityDTO.setId(commodity.getId());
      commodityDTO.setName(commodity.getName());
      commodityDTO.setDescription(commodity.getDescription());
      commodityDTO.setPrice(commodity.getPrice());
      commodityDTO.setCategoryName(commodity.getCategory().getName());
      commodityDTO.setAmount(commodity.getAmount().getAmount());
      commodityDTO.setPhotos(mapPhotos(commodity.getPhotos()));
      return commodityDTO;
    }

    private static Collection<PhotoDTO> mapPhotos(List<Photo> photos) {
      Collection<PhotoDTO> photoDTOS = new ArrayList<>();
      for (Photo photo : photos) {
        PhotoDTO photoDTO = new PhotoDTO();
        photoDTO.setId(photo.getId());
        photoDTO.setUrl(photo.getUrl());
        photoDTO.setName(photo.getName());
        photoDTO.setDescription(photo.getDescription());
        photoDTO.setHint(photo.getHint());
        photoDTO.setWidth(photo.getWidth());
        photoDTO.setHeight(photo.getHeight());
        photoDTOS.add(photoDTO);
      }
      return photoDTOS;
    }

  }

}
