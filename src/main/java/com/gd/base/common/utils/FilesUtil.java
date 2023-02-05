package com.gd.base.common.utils;

import cn.hutool.extra.spring.SpringUtil;
import com.gd.base.module.files.entity.Files;
import com.gd.base.module.files.service.FilesService;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 文件工具类
 *
 */
public class FilesUtil {

    private static FilesService filesService = SpringUtil.getBean(FilesService.class);

    /**
     * 获取文件
     *
     * @return
     */
    public static Files get(String fileId) {
        return filesService.get(fileId);
    }

    /**
     * 上传文件
     *
     * @return
     */
    public static Files upload(MultipartFile file) throws IOException {
        return filesService.upload(file);
    }

    /**
     * 下载文件
     *
     * @return
     */
    public static void download(String fileId, HttpServletResponse response) throws IOException {
        filesService.download(fileId, response);
    }

    /**
     * 删除文件
     *
     * @return
     */
    public static void download(String fileId) {
        filesService.deleteByLogic(fileId);
    }

}
