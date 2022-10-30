package com.cozastore.service.cloudinary;

import java.io.IOException;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

public interface ICloudinaryService {
	public String upload(MultipartFile multipartFile) throws IOException ;
}
