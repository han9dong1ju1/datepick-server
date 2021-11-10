package app.hdj.datepick.global.config.s3;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class AmazonS3Service {

    private final AmazonS3Client amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    public boolean exists(String key) {
        return amazonS3Client.doesObjectExist(bucket, key);
    }

    private Optional<File> convert(MultipartFile file) throws IOException {
        File convertFile = new File(Objects.requireNonNull(file.getOriginalFilename()));
        if (convertFile.createNewFile()) { // 바로 위에서 지정한 경로에 File이 생성됨 (경로가 잘못되었다면 생성 불가능)
            try (FileOutputStream fos = new FileOutputStream(convertFile)) { // FileOutputStream 데이터를 파일에 바이트 스트림으로 저장하기 위함
                fos.write(file.getBytes());
            }
            return Optional.of(convertFile);
        }

        return Optional.empty();
    }

    private String upload(File uploadFile, String key) {
        amazonS3Client.putObject(new PutObjectRequest(bucket, key, uploadFile).withCannedAcl(CannedAccessControlList.PublicRead));
        String uploadFilePath = amazonS3Client.getUrl(bucket, key).getFile();
        removeLocalFile(uploadFile);
        return uploadFilePath;
    }

    private void removeLocalFile(File targetFile) {
        if (targetFile.delete()) {
            log.debug("Local File deleted");
            return;
        }
        log.info("[Error] Local File delete failed");
    }

    // S3로 파일 업로드
    public String add(MultipartFile multipartFile, String dirName) throws IOException {
        File uploadFile = convert(multipartFile)
                .orElseThrow(() -> new IllegalArgumentException("[Error] AWS S3 MultipartFile - File conversion failed"));
        String key = dirName + "/" + UUID.randomUUID() + "." + StringUtils.getFilenameExtension(uploadFile.getName());

        return upload(uploadFile, key);
    }

    // S3의 파일 수정
    public String modify(MultipartFile multipartFile, String key) throws IOException {
        if (key.charAt(0) == '/') {
            key = key.substring(1);
        }

        if (amazonS3Client.doesObjectExist(bucket, key)) {
            remove(key);
        }

        File uploadFile = convert(multipartFile)
                .orElseThrow(() -> new IllegalArgumentException("[Error] AWS S3 MultipartFile - File conversion failed"));
        return upload(uploadFile, key);
    }

    // S3의 파일 삭제
    public void remove(String key) {
        if (key.charAt(0) == '/') {
            key = key.substring(1);
        }

        amazonS3Client.deleteObject(new DeleteObjectRequest(bucket, key));
    }

}
