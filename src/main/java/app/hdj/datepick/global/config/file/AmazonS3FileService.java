package app.hdj.datepick.global.config.file;

import app.hdj.datepick.global.error.enums.ErrorCode;
import app.hdj.datepick.global.error.exception.CustomException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class AmazonS3FileService implements FileService {

    private final AmazonS3 amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Value("${cloud.aws.cloudfront.host}")
    private String cdnHost;

    public String add(MultipartFile multipartFile, String path) {
        String key = path + "/" + UUID.randomUUID() + "." + StringUtils.getFilenameExtension(multipartFile.getOriginalFilename());

        try {
            amazonS3Client.putObject(new PutObjectRequest(bucket, key, multipartFile.getInputStream(), null));
        } catch (IOException e) {
            throw new CustomException(ErrorCode.FILE_UPLOAD_FAILED);
        }

        return "https://" + cdnHost + amazonS3Client.getUrl(bucket, key).getFile();
    }

    public void remove(String key) {
        if (key.startsWith("https://")) {
            key = key.substring(("https://" + cdnHost).length() + 1);
        }

        amazonS3Client.deleteObject(new DeleteObjectRequest(bucket, key));
    }

}
