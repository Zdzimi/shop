package pl.zdzimi.shop.service;

import com.cloudinary.Cloudinary;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class CloudinaryConfiguration {

  private final CloudinaryProperties cloudinaryProperties;

  @Bean
  public Cloudinary cloudinary() {
    return new Cloudinary(cloudinaryProperties.getUrl());
  }

}
