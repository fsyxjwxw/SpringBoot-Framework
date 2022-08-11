package com.ryan.fw.controller;

import com.ryan.fw.common.GoFastDfsUploadFileResult;
import com.ryan.fw.utils.GoFastDfsUtil;
import com.ryan.fw.utils.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * @author Administrator
 */
@RestController
@Api(tags = "文件服务器测试API")
public class GoFastDfsController {

    @Autowired
    private GoFastDfsUtil goFastDfsUtil;

    @PostMapping("/uploadFile")
    @ApiOperation(value = "上传文件到文件服务器")
    public ResponseResult<GoFastDfsUploadFileResult> uploadFile(MultipartFile multipartFile) {
        return goFastDfsUtil.uploadFileResult(multipartFile);
    }

    @ApiOperation(value = "根据文件的md5值删除文件")
    @GetMapping("/deleteFileByMd5")
    public Map deleteFileByMd5(@RequestParam String md5) {
        return goFastDfsUtil.deleteFileByMd5(md5);
    }

    @ApiOperation(value = "根据上传路径删除文件")
    @GetMapping("/deleteFileByPath")
    public Map deleteFileByPath(@RequestParam String path) {
        return goFastDfsUtil.deleteFileByPath(path);
    }

    @ApiOperation(value = "根据文件的md5值获取文件信息")
    @GetMapping("/getFileInfoByMd5")
    public Map getFileInfoByMd5(@RequestParam String md5) {
        return goFastDfsUtil.getFileInfoByMd5(md5);
    }

    @ApiOperation(value = "根据上传路径获取文件信息")
    @GetMapping("/getFileInfoByPath")
    public Map getFileInfoByPath(@RequestParam String path) {
        return goFastDfsUtil.getFileInfoByPath(path);
    }

    @ApiOperation(value = "统计文件服务器文件数量")
    @GetMapping("/stat")
    public Map stat() {
        return goFastDfsUtil.getFileStat();
    }

    @ApiOperation(value = "获取文件服务器的文件byte数组")
    @GetMapping("/getFileBytes")
    public ResponseResult getFileBytes(@RequestParam String filePath) {
        return goFastDfsUtil.getFileBytes(filePath);
    }

    @ApiOperation(value = "根据文件服务器的地址下载文件到本地")
    @GetMapping("/downloadFileToLocal")
    public ResponseResult downloadFileToLocal(@RequestParam String filePath,@RequestParam String localDir) {
        return goFastDfsUtil.downloadFileToLocal(filePath,localDir);
    }

}
