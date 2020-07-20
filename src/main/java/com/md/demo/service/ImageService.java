package com.md.demo.service;

import com.md.demo.model.Image;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
	Image createNewImage(MultipartFile imageData, Integer itemId);

	Image getImageById(Integer id);

	boolean isImageDeleted(Integer imageId);
}
