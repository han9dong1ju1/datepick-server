package app.hdj.datepick.global.config.s3;

import com.amazonaws.services.s3.AmazonS3Client;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Slf4j
@SpringBootTest
class AmazonS3ServiceTest {

    @Autowired
    private AmazonS3Service amazonS3Service;

    @Autowired
    private AmazonS3Client amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Test
    void AWS_S3_파일_업로드_삭제() throws IOException {
        File file = new File("src/test/resources/test.png");
        MultipartFile multipartFile = new MockMultipartFile("testPic", "test.png", "image/png", new FileInputStream(file));
        String uploadFileKey = amazonS3Service.add(multipartFile, "testFolder");
        Assertions.assertThat(uploadFileKey).isNotBlank();
        log.debug(uploadFileKey);

        // 삭제
        amazonS3Service.remove(uploadFileKey);
        Assertions.assertThat(amazonS3Client.doesObjectExist(bucket, uploadFileKey)).isFalse();
    }

    @Test
    void AWS_S3_파일_수정() throws IOException {
        MultipartFile multipartFile1 = new MockMultipartFile("testPic1", "test.png", "image/png", "test".getBytes());
        String uploadFileKey1 = amazonS3Service.add(multipartFile1, "testFolder");
        log.debug(uploadFileKey1);

        File file = new File("src/test/resources/test.png");
        MultipartFile multipartFile2 = new MockMultipartFile("testPic2", "test.png", "image/png", new FileInputStream(file));
        String uploadFileKey2 = amazonS3Service.modify(multipartFile2, uploadFileKey1);
        Assertions.assertThat(uploadFileKey2).isNotBlank();
        Assertions.assertThat(uploadFileKey1).isEqualTo(uploadFileKey2);
        log.debug(uploadFileKey2);

        // 나머지 삭제
        amazonS3Service.remove(uploadFileKey1);
        amazonS3Service.remove(uploadFileKey2);
    }

}