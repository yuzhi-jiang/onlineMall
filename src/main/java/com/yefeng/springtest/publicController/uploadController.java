package com.yefeng.springtest.publicController;

import com.yefeng.springtest.util.FileUtil;
import com.yefeng.springtest.util.UploadUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static com.yefeng.springtest.util.Constant.*;

@RestController
@RequestMapping("/post")
public class uploadController {

    /**
     * 接收附件
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "fileupload", method = RequestMethod.POST)
    public void upload(HttpServletRequest request) throws IOException {
        //文件上传的请求
        MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;
        //获取请求的参数
        Map<String, MultipartFile> fileMap = mRequest.getFileMap();
        //项目的绝对路径   request.getSession().getServletContext().getRealPath()这个是得到项目的绝对地址
//        String ctxPath = request.getSession().getServletContext().getRealPath("/")
//                + FileOperateUtil.FILEDIR;
//        File file = new File(ctxPath);
//        if (!file.exists()) {
//            file.mkdir();
//        }
        Map<String, Object> result = new HashMap<String, Object>();
        Iterator<Map.Entry<String, MultipartFile>> it = fileMap.entrySet().iterator();
        //用hasNext() 判断是否有值，用next()方法把元素取出。
        while (it.hasNext()) {
            Map.Entry<String, MultipartFile> entry = it.next();
            MultipartFile mFile = entry.getValue();
            if (mFile.getSize() != 0 && !"".equals(mFile.getName())) {
                System.out.println(mFile.getOriginalFilename());
//                write(mFile.getInputStream(), new FileOutputStream(ctxPath+mFile.getOriginalFilename()));
//                result.put(mFile.getName(), mFile.getOriginalFilename());


            }
        }


    }


    /**
     * 接收图片文件
     *
     * @param MultipartFile
     * @return Map
     * @throws IOException
     * @throws IllegalStateException
     */
    @RequestMapping(value = "imageupload")
    public Map<String, Object> imageUpload(MultipartFile file) throws IllegalStateException, IOException {
        HashMap<String, Object> map = new HashMap<>();
        //文件名称
        String realFileName = file.getOriginalFilename();
        //文件后缀
        String suffix = realFileName.substring(realFileName.lastIndexOf(".") + 1);
        /***************文件处理*********************/

        try {
            String filePath = UploadUtils.getUUIDName(realFileName);
            file.transferTo(FileUtil.getFile(filePath));
            map.put(ERROR_CODE, CODE_OK);
            map.put("name", realFileName);
            map.put("picPath", filePath);
            map.put(ERROR_MSSAGE, "上传图片成功");
        } catch (IOException e) {
            e.printStackTrace();
            map.put(ERROR_CODE, IMAGE_SAVE_FAIL);

            map.put(ERROR_MSSAGE, "上传图片失败");
        }


        return map;
    }
}
