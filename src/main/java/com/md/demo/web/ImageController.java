package com.md.demo.web;

import com.md.demo.service.ImageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Api(description = "Perform CRUD operations on Image Entity")
@AllArgsConstructor
@RestController
@RequestMapping("/image")
public class ImageController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ImageController.class);

	private final ImageService imageService;

	@ApiOperation(value = "Get a specific Image by id")
	@GetMapping("/{imageId}")
	public ResponseEntity<Object> getImage(@RequestParam("imageId") Integer imageId) {
		LOGGER.info("About to get an Image by id: {}", imageId);
		imageService.getImageById(imageId);
		LOGGER.info("Image was found successfully!");

		return ResponseEntity.status(HttpStatus.OK).body("itemDto");
	}

	@ApiOperation(value = "Delete a specific Image by id")
	@DeleteMapping("/{imageId}")
	public ResponseEntity<Object> deleteImage(@RequestParam("imageId") Integer imageId) {
		LOGGER.info("About to delete an Image by id: {}", imageId);
		boolean imageDeleted = imageService.isImageDeleted(imageId);
		LOGGER.info("Image with {} id was deleted successfully: ", imageId, imageDeleted);

		return ResponseEntity.status(HttpStatus.OK).body("Image was deleted successfully");
	}

	@ApiOperation(value = "Create a new Image by id")
	@PostMapping("/addImage/{itemId}")
	public ResponseEntity<Object> createImage(@RequestParam("imageData") MultipartFile imageData,
											  @RequestParam("itemId") Integer itemId) {
		LOGGER.info("About to add an Image in DB");
		imageService.createNewImage(imageData, itemId);
		LOGGER.info("Image was added successfully in DB!: {}");

		return ResponseEntity.status(HttpStatus.OK).body("Image was uploaded successfully");
	}
}
