package com.neworin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

/**
 * Created by NewOrin Zhang on 2016/6/29.
 * Email: NewOrinZhang@Gmail.com
 */
@Controller
@RequestMapping("file")
public class FileUploadController {

    /**
     * 上传单个文件
     *
     * @param file
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("fileupload")
    public String singleFileUpload(MultipartFile file, HttpServletRequest request, Model model) {
        // 1. 获取解析上传的文件 -- file

        // 2. 保存文件到服务器指定目录 -- IO流写入
        String path = request.getSession().getServletContext().getRealPath("upload");// 获取要上传的目录
        System.out.println("文件存储路径--->" + path);
        String fileName = file.getOriginalFilename();//处理保存的文件名
        saveFile(file, path);
        model.addAttribute("filePath", path + "\\" + fileName);
        return "result";
    }

    @RequestMapping("multi_diff_fileupload")
    public String MultiDiffNameFileUpload(HttpServletRequest request, Model model) {
        Iterator<String> iterator = null;
        //创建多文件的解析器
        CommonsMultipartResolver resolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        //解析请求内容
        //先判断是否有文件上传
        if (resolver.isMultipart(request)) {
            //转换request
            MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
            //获取request中的所有文件名
            iterator = multipartHttpServletRequest.getFileNames();
            String path = request.getSession().getServletContext().getRealPath("upload"); // 获取要上传的目录
            int count = 0;
            //遍历
            while (iterator.hasNext()) {
                //通过文件名获取文件
                MultipartFile file = multipartHttpServletRequest.getFile(iterator.next());
                saveFile(file, path);
                count++;
            }
            System.out.println("iterator size = " + count);
        }
        if (model != null) {
            model.addAttribute("filePath", iterator.toString());
        }
        return "result";

    }

    @RequestMapping("multi_same_fileupload")
//	public String Fileupload2(@RequestParam("files")MultipartFile[] files, HttpServletRequest request) { //也可以
    public String MultiSameNameFileUpload(@RequestParam("files") CommonsMultipartFile[] files, HttpServletRequest request, Model model) {
        StringBuffer sb = new StringBuffer("文件名:");
        String path = request.getSession().getServletContext().getRealPath("upload");//获取要上传的目录
        for (CommonsMultipartFile file : files) {
            System.out.println("文件名:" + file.getOriginalFilename());
            saveFile(file, path);
            sb.append(file.getOriginalFilename() + "-");
        }
        if (model != null) {
            model.addAttribute("filePath", "所有文件:" + sb.toString());
        }
        return "result";
    }

    /**
     * 保存文件到指定目录下
     *
     * @param file
     * @param path 保存的路径
     */
    public void saveFile(MultipartFile file, String path) {
        String fileName = file.getOriginalFilename();
        File destFile = new File(path, fileName);
        if (!destFile.exists()) {
            //若不存在，则创建文件
            destFile.mkdirs();
        }
        try {
            file.transferTo(destFile);//另存为
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
