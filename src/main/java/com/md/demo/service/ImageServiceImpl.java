package com.md.demo.service;

import com.md.demo.exception.NoImageToUploadException;
import com.md.demo.model.Image;
import com.md.demo.model.Item;
import com.md.demo.repository.ImageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Objects;

@Service
public class ImageServiceImpl implements ImageService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ImageServiceImpl.class);

	private ImageRepository imageRepository;

	private ItemService itemService;

	public ImageServiceImpl(ImageRepository imageRepository, ItemService itemService) {
		this.imageRepository = Objects.requireNonNull(imageRepository, "imageRepository is mandatory");
		this.itemService = Objects.requireNonNull(itemService, "itemService is mandatory");
	}

	@Override
	public Image createNewImage(MultipartFile imageData, Integer id) {

		LOGGER.info("About reading bytes from image");

		if (!imageData.isEmpty()) {
			try {
				Item itemById = itemService.getItemById(id);

				Image builderImage = Image.builder()
						.datePosted(LocalDateTime.now())
						.name(imageData.getOriginalFilename())
						.imageData(imageData.getBytes())
						.item(itemById)
						.build();

				return imageRepository.save(builderImage);
			} catch (IOException e) {
				LOGGER.error("An error occurred during parsing the image {}", e);
			}
			return null;
		} else {
			throw new NoImageToUploadException("Select an image to upload");
		}
	}
}
