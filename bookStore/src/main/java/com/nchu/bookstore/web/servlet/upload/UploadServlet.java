package com.nchu.bookstore.web.servlet.upload;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@WebServlet("/upload")
public class UploadServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //解析和检查请求 判断请求方式是不是POST 请求编码是否是multipart/form-data
        boolean isMultipart = ServletFileUpload.isMultipartContent(req);
        String reqParam="";
        //System.out.println(req);
        if (!isMultipart) {
            return;
        }
        //1 创建一个FileItemFactory
        //FileItemFactory是创建FileItem的
        //FileItem对象 是表单控件的封装
        try {
            DiskFileItemFactory factory = new DiskFileItemFactory();
            //2 准备文件上传处理器
            ServletFileUpload upload = new ServletFileUpload(factory);

            //3 开始解析请求
            List<FileItem> list = upload.parseRequest(req);

            for (FileItem item : list) {
                String fieldName = item.getFieldName();
                if (item.isFormField()) {
                    if ("id".equalsIgnoreCase(fieldName) && fieldName!=null){
                        reqParam+=fieldName+"="+item.getString("UTF-8")+"&";
                    }else{
                        reqParam+=fieldName+"="+item.getString("UTF-8")+"&";
                    }
                    //System.out.println(fieldName + "----" + item.getString("UTF-8"));
                } else {
                    //为了防止文件名重复将文件覆盖 所以使用UUID生成随机文件名
                    String fileName = UUID.randomUUID().toString().substring(0,6);
                    //将文件保存在应用中
                    //获取绝对路径
                    fileName = fileName+ "."
                            + FilenameUtils.getExtension(item.getName());
                    reqParam+=fieldName+"="+fileName;
                    //System.out.println(reqParam);
                    String path=getServletContext().getRealPath("/images");
                    //将文件保存在哪个文件
                    item.write(new File(path, fileName));
                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //System.out.println("/manage/books?cmd=save&"+reqParam);
        req.getRequestDispatcher("/manage/books?cmd=save&"+reqParam).forward(req,resp);
    }
}
