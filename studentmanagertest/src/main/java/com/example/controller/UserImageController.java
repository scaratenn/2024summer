package com.example.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.pojo.UserImage;
import com.example.usermapper.UserImageMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.Base64;
import java.util.Objects;
@Slf4j
@RestController
@RequestMapping("/user/image")
public class UserImageController {
    @Resource
    UserImageMapper userImageMapper;
    @PostMapping("/uploadImage")
    public boolean uploadImage(@RequestParam Integer id,
                               @RequestParam("file") MultipartFile file){

        UserImage user = userImageMapper.selectOne(Wrappers.<UserImage>lambdaQuery().eq(UserImage::getId, id));
        if (Objects.isNull(user)){
            return false;
        }
        String base64Image = "";
        try {
            base64Image = Base64.getEncoder().encodeToString(file.getBytes());
            user.setImage(base64Image);
            userImageMapper.updateById(user);
        }catch (Exception e){
            log.error("md5 transfer error",e);
            return false;
        }
        return true;
    }
}
