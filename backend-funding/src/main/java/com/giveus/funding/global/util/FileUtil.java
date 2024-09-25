package com.giveus.funding.global.util;

import com.giveus.funding.global.common.enums.Folder;
import com.giveus.funding.infra.file.FileClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

/**
 * 파일 업로드를 담당하는 파일 서비스입니다.
 *
 * @author 이하늬
 */
@Component
@RequiredArgsConstructor
public class FileUtil {
    private final FileClient fileClient;

    public String upload(MultipartFile file, String objectName, Folder folderName) {
        return fileClient.uploadFile(file, objectName, folderName);
    }

    public void delete(String objectName, Folder folder) {
        String folderName = folder.getName();
        fileClient.deleteFile(objectName, folderName);
    }


    /**
     * 고유 오브젝트명을 생성하는 메서드입니다.
     *
     * @return 생성한 오브젝트명
     */
    public String generateObjectName() {
        String random = UUID.randomUUID().toString();
        random = random.replace("-", "");
        random = random.substring(0, 10);
        return random;
    }
}
