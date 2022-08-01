package com.ryan.fw.utils;

import com.aspose.words.Document;
import com.aspose.words.NodeCollection;
import com.aspose.words.NodeType;
import com.aspose.words.Shape;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author Ryan
 * @date 2022年04月24日
 * @note
 */
@Slf4j
public class WordUtils {

    /**
     * 解析word中图片
     *
     * @param bytes
     * @return
     */
    public static List<MultipartFile> getPictures(byte[] bytes) {
        List<MultipartFile> list = new ArrayList<>();
        InputStream in = new ByteArrayInputStream(bytes);
        try {
            Document doc = new Document(in);
            NodeCollection nodes = doc.getChildNodes(NodeType.SHAPE, true);
            for (Object obj : nodes) {
                Shape shape = (Shape) obj;
                if (shape.hasImage()) {
                    String name = UUID.randomUUID().toString() + ".png";
                    byte[] imageBytes = shape.getImageData().getImageBytes();
                    MultipartFile file = new MockMultipartFile(name, imageBytes);
                    list.add(file);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("【Word解析】解析文件中图片失败");
        }
        return list;
    }

}
