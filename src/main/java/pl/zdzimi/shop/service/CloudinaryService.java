package pl.zdzimi.shop.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.ssl.SslProperties.Bundles.Watch;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class CloudinaryService {

  private final Cloudinary cloudinary;

  public String uploadFile(MultipartFile multipartFile) {

    if (!Files.exists(Path.of("upload-dir"))) {
      try {
        Files.createDirectory(Path.of("upload-dir"));
      } catch (IOException ioException) {
        ioException.printStackTrace();
      }
    }

    Path destinationFile = Paths.get("upload-dir").resolve(
        Paths.get(multipartFile.getOriginalFilename()))
        .normalize().toAbsolutePath();

    System.out.println(destinationFile);

    try (InputStream inputStream = multipartFile.getInputStream()) {
      Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
    } catch (IOException ioException) {
      ioException.printStackTrace();
    }

    File file = new File(destinationFile.toString());
    Map uploadResult = null;
    try {
      uploadResult = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
    } catch (IOException ioException) {
      ioException.printStackTrace();
    }
    return (String) uploadResult.get("url");
  }

}
