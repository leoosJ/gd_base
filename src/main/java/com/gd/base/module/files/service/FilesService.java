package com.gd.base.module.files.service;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.gd.base.base.crud.CrudService;
import com.gd.base.common.utils.IdGenUtil;
import com.gd.base.module.files.entity.Files;
import com.gd.base.module.files.mapper.FilesMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;

@Service
public class FilesService extends CrudService<FilesMapper, Files> {

    @Resource
    private FilesMapper filesMapper;

    /**
     * 文件上传路径
     */
    @Value("${files.upload.path}")
    private String fileUploadPath;

    /**
     * 文件下载路径
     */
    @Value("${files.download.url}")
    private String fileDownloadUrl;

    /**
     * 文件上传
     *
     * @author JLP
     * @param file
     * @return
     * @throws IOException
     * @date 2022-12-23
     */
    public Files upload(MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();
        String type = FileUtil.extName(originalFilename);
        long size = file.getSize();
        // 定义一个文件唯一的标识码
        String uuid = IdGenUtil.uuid();
        String fileUUID = uuid + StrUtil.DOT + type;
        File uploadFile = new File(fileUploadPath + fileUUID);
        // 判断配置的文件目录是否存在，若不存在则创建一个新的文件目录
        File parentFile = uploadFile.getParentFile();
        if (!parentFile.exists()) {
            parentFile.mkdirs();
        }
        String url;
        // 获取文件的md5，通过对比md5避免重复上传相同内容的文件
        String md5 = SecureUtil.md5(file.getInputStream());
        // 从数据库查询是否存在相同的记录
        Files dbFiles = this.filesMapper.getFileByMd5(md5);
        if (dbFiles != null) { // 文件已存在
            url = dbFiles.getUrl();
        } else {
            // 上传文件到磁盘
            file.transferTo(uploadFile);
            // 数据库若不存在重复文件，则不删除刚才上传的文件
            url = fileDownloadUrl + uuid;
        }

        // 同步文件库
        Files files = new Files();
        files.setId(uuid);
        files.setName(originalFilename);
        files.setType(type);
        files.setSize(size / 1024);
        files.setUrl(url);
        files.setMd5(md5);
        files.setNewRecord(true);
        this.save(files);

        return files;
    }

    /**
     * 文件下载
     *
     * @author JLP
     * @param fileId
     * @param response
     * @throws IOException
     * @date 2022-12-23
     */
    public void download(String fileId, HttpServletResponse response) throws IOException {
        // 根据文件的唯一标识码获取文件
        Files files = this.filesMapper.get(fileId);
        if (files != null) {
            String fileUUID = files.getId() + StrUtil.DOT + files.getType();
            File uploadFile = new File(fileUploadPath + fileUUID);
            // 设置输出流的格式
            ServletOutputStream os = response.getOutputStream();
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(files.getName(), "UTF-8"));
            response.setContentType("application/octet-stream");
            // 读取文件的字节流
            os.write(FileUtil.readBytes(uploadFile));
            os.flush();
            os.close();
        }
    }

}
