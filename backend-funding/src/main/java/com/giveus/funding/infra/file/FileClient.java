package com.giveus.funding.infra.file;

import com.giveus.funding.global.common.enums.Folder;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author 이하늬
 * @since 1.0
 */
public interface FileClient {
    
    /**
     * 파일을 업로드하는 메서드입니다.
     *
     * @param file       업로드 할 파일
     * @param folderName 업로드 할 버킷 폴더명
     * @return 업로드 한 S3 url
     */
    String uploadFile(MultipartFile file, String objectName, Folder folderName);

    /**
     * 파일 URL을 생성하는 메서드입니다.
     *
     * @param folderName 폴더명
     * @param fileName   파일명
     * @return url
     */
    String getFileUrl(String folderName, String fileName);

    /**
     * 파일을 삭제하는 메서드입니다.
     *
     * @param objectName 삭제 할 오브젝트명
     * @param folderName 삭제 할 버킷 폴더명
     */
    void deleteFile(String objectName, String folderName);
}
