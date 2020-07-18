package com.md.demo.web;

import com.md.demo.dto.ImageDTO;
import com.md.demo.model.Image;
import com.md.demo.service.ImageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

@RestController
public class ImageController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ImageController.class);

	private ImageService imageService;

	public ImageController(ImageService imageService) {
		this.imageService = Objects.requireNonNull(imageService, "imageService is mandatory");
	}

	@PostMapping("/image/{itemId}/addImage")
	public ResponseEntity<Object> createImage(@RequestParam("imageData") MultipartFile imageData,
											  @RequestParam("itemId") Integer itemId) {
		LOGGER.info("About to add an Image in DB");
		Image image = imageService.createNewImage(imageData, itemId);
		LOGGER.info("Image was added successfully in DB!");
		ImageDTO itemDto = new ImageDTO(image);
		return ResponseEntity.status(HttpStatus.OK).body(itemDto);
	}
}
