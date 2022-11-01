package com.cozastore.service.cloudinary;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;

@Service
public class CloudinaryService implements ICloudinaryService {
	private static final String CLOUDINARY_CLOUD_NAME = "dmfgtsxtv";
	private static final String CLOUDINARY_API_KEY = "937937847294251";
	private static final String CLOUDINARY_API_SECRET = "VOPgFGzjMkkcTm4ht7pLpJLj_WI";
	
	

	@Override
	public String upload(MultipartFile multipartFile) throws IOException {
		
		
		Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap("cloud_name", CLOUDINARY_CLOUD_NAME, "api_key",
				CLOUDINARY_API_KEY, "api_secret", CLOUDINARY_API_SECRET, "secure", true));
		
		Map cloudinaryApi = cloudinary.uploader().upload(multipartFile, ObjectUtils.asMap("resource_type", "auto"));
        String url = (String) cloudinaryApi.get("url");
        return url;
	}
	

	
}
