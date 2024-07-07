package com.example.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.pojo.ManagerImage;
import com.example.pojo.UserImage;
import com.example.usermapper.ManagerImagerMapper;
import com.example.usermapper.ManagerMapper;
import com.example.usermapper.UserImageMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.Base64;
import java.util.Objects;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/manager/image")
public class ManagerImagerController {
    @Resource
    ManagerImagerMapper managerImagerMapper;
    @PostMapping("/uploadImage")
    public boolean uploadImage(@RequestParam Integer id,
                               @RequestParam("file") MultipartFile file){

       ManagerImage user = managerImagerMapper.selectOne(Wrappers.<ManagerImage>lambdaQuery().eq(ManagerImage::getId, id));
        if (Objects.isNull(user)){
            return false;
        }
        String base64Image = "";
        try {
            base64Image = Base64.getEncoder().encodeToString(file.getBytes());
            user.setImage(base64Image);
            managerImagerMapper.updateById(user);
            //log.info("{}",file.getBytes());

        }catch (Exception e){
            log.error("md5 transfer error",e);
            return false;
        }
        log.info("上传成功");
        return true;
    }
}
