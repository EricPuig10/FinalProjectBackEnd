package com.app.finalproject.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class CloudinaryService implements ICloudinaryService {

    Cloudinary cloudinary;
    private Map<String,String> valuesMap = new HashMap<>();

    public CloudinaryService() {
        valuesMap.put("cloud_name" ,"ericpuig10");
        valuesMap.put("api_key", "459283242843895");
        valuesMap.put("api_secret", "funKnUWXUm2TuJZe_N63gBnwsIc");

        cloudinary = new Cloudinary(valuesMap);
    }

    @Override
    public Map upload (MultipartFile multipartFile) throws IOException {
        File file = convert(multipartFile);
        Map result = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
        file.delete();
        return result;
    }
    @Override
    public Map delete (String id) throws IOException {
        Map result = cloudinary.uploader().destroy(id, ObjectUtils.emptyMap());
        return result;
    }
    @Override
    public File convert(MultipartFile multipartFile) throws IOException {
        File file = new File(multipartFile.getOriginalFilename());
        FileOutputStream fo = new FileOutputStream(file);
        fo.write(multipartFile.getBytes());
        fo.close();
        return file;
    }


}
