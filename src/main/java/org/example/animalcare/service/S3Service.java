package org.example.animalcare.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.ResponseBytes;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.GetUrlRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.io.InputStream;

@Service
@RequiredArgsConstructor
public class S3Service {

     private final S3Client s3Client;

     @Value("${aws.s3.bucketName}")
     private String bucketName;

     public String uploadFile(String key, InputStream fileStream, String contentType) throws IOException {
          s3Client.putObject(PutObjectRequest.builder()
                          .bucket(bucketName)
                          .key(key)
                          .contentType(contentType)
                          .build(),
                  software.amazon.awssdk.core.sync.RequestBody.fromInputStream(fileStream, fileStream.available()));

          return key;
     }

     public String generatePresignedUrl(String key) {
          return s3Client.utilities().getUrl(GetUrlRequest.builder()
                  .bucket(bucketName)
                  .key(key)
                  .build()).toString();
     }

     public byte[] downloadFileBytes(String key) {
          ResponseBytes<GetObjectResponse> objectBytes = s3Client.getObjectAsBytes(
                  GetObjectRequest.builder()
                          .bucket(bucketName)
                          .key(key)
                          .build()
          );
          return objectBytes.asByteArray();
     }


}
