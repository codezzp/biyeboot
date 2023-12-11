package com.example.biyeboot.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.biyeboot.config.RemoteData;
import com.example.biyeboot.entity.Files;
import com.example.biyeboot.mapper.FilesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

@RestController
@RequestMapping("/file")
public class FilesController {

    @Autowired
    FilesMapper filesMapper;
    private String fileUploadPath= RemoteData.VARIBLE_FILE_NAME +"\\files\\";
    //文件的上传
    @PostMapping("/upload")
    public String upload(@RequestParam MultipartFile file){
        String originalFilename = file.getOriginalFilename();
        String type= FileUtil.extName(originalFilename);
        long size = file.getSize();
        //先存到磁盘
        File ParentFile = new File(fileUploadPath);
        //判断配置的文件目录是否存在
        if (!ParentFile.exists()){
            ParentFile.mkdirs();
        }
        //定义文件唯一的标识码
        String uuid = IdUtil.fastSimpleUUID();
        File uploadFilePath = new File(fileUploadPath+uuid+originalFilename);
        try {
            file.transferTo(uploadFilePath);
        } catch (IOException e) {
                e.printStackTrace();
        }
        //存储数据库
        Files savefile = new Files();
        savefile.setName(uuid+originalFilename);
        savefile.setType(type);
        savefile.setSize(size);
        filesMapper.insert(savefile);
        return RemoteData.HOST_VARIBLE+"/file/"+uuid+originalFilename;
    }
    @GetMapping("/{filename}")
    public void download(@PathVariable String filename, HttpServletResponse httpServletResponse) throws IOException {
        File uploadfile = new File(fileUploadPath+filename);
        ServletOutputStream outputStream=httpServletResponse.getOutputStream();
        httpServletResponse.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(filename, "UTF-8"));
        httpServletResponse.setContentType("application/octet-stream");
        outputStream.write(FileUtil.readBytes(uploadfile));
        outputStream.flush();
        outputStream.close();
    }
}
