package app.hdj.datepick.global.config.file;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    String add(MultipartFile multipartFile, String path);

    void remove(String key);

}
