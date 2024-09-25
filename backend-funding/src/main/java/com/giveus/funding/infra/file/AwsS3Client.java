package com.giveus.funding.infra.file;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.giveus.funding.global.common.enums.Folder;
import com.giveus.funding.global.error.exception.FileUploadException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class AwsS3Client implements FileClient {

    private final AmazonS3Client amazonS3Client;
    private final String HTTPS = "https://";
    private final String SLASH = "/";
    private final String S3 = ".s3.";
    private final String AMAZON_AWS = ".amazonaws.com";
    private final String DOT = ".";

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Value("${cloud.aws.region.static}")
    private String region;


    @Override
    public String uploadFile(MultipartFile file, String objectName, Folder folder) {
        try {
            String folderName = folder.getName();
            String extension = StringUtils.getFilenameExtension(file.getOriginalFilename());
            String fileName = objectName + DOT + extension;
            String fileUrl = getFileUrl(folderName, fileName);

            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentType(file.getContentType());
            metadata.setContentLength(file.getSize());

            String bucketName = bucket + SLASH + folderName;
            amazonS3Client.putObject(bucketName, fileName, file.getInputStream(), metadata);

            return fileUrl;
        } catch (IOException e) {
            throw new FileUploadException();
        }
    }


    @Override
    public String getFileUrl(String folderName, String fileName) {
        return HTTPS + bucket + S3 + region + AMAZON_AWS + SLASH + folderName + SLASH + fileName;
    }


    @Override
    public void deleteFile(String objectName, String folderName) {
        String bucketName = bucket + SLASH + folderName;
        amazonS3Client.deleteObject(bucketName, objectName);
    }
}
