package com.pos.system.utils;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class ImageController {

    @GetMapping("/images/{filename:.+}")
    @ResponseBody
    public byte[] getImage(@PathVariable String filename) throws IOException {
        return ImageUtil.getImageBytes(filename);
    }
}