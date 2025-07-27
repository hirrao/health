package com.hirrao.health.common.utils;

import com.hirrao.health.common.exception.ServerException;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;


/**
 * 封装部分图片操作, 获取图片应由Nginx等代理
 */
@Component
@Slf4j
public class ImageUtil {
    private final Path baseDirPath;

    @Autowired
    public ImageUtil(@Value("${file.images}") String baseDir) {
        this.baseDirPath = Paths.get(baseDir);
    }

    /**
     * 启动时创建图片目录
     */
    @PostConstruct
    public void init() {
        try {
            if (Files.notExists(baseDirPath)) {
                Files.createDirectories(baseDirPath);
                log.info("创建图片路径: {}", baseDirPath);
            }
        }
        catch (IOException e) {
            log.error("创建图片路径失败", e);
            throw new ServerException("创建图片路径失败");
        }
    }

    private String generateFileName() {
        var MAX_RETRY = 100;
        for (int i = 0; i < MAX_RETRY; i++) {
            var uuid = UUID.randomUUID()
                           .toString()
                           .substring(0, 12);
            var fileName = uuid + ".jpg";
            if (!exists(fileName)) {
                return fileName;
            }
        }
        throw new ServerException("生成图片路径失败");
    }

    public boolean exists(String fileName) {
        if (!StringUtils.hasText(fileName)) {
            return false;
        }
        var filePath = baseDirPath.resolve(fileName);
        return Files.exists(filePath) && Files.isRegularFile(filePath);
    }

    public String createImage(MultipartFile imageFile) {
        var newFilename = generateFileName();
        var path = baseDirPath.resolve(newFilename);
        try {
            var inputStream = imageFile.getInputStream();
            Files.copy(inputStream, path);
            return newFilename;
        }
        catch (IOException e) {
            log.error("创建图片失败", e);
            throw new ServerException("创建图片失败");
        }
    }
}
