package msm_group.masterspringmvc.profile;

import msm_group.masterspringmvc.config.PicturesUploadProperties;
import org.apache.tomcat.util.http.fileupload.FileUploadBase;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLConnection;

@Controller
public class PictureUploadController {

    private final Resource picturesDir;
    private final Resource anonymousPicture;

    @Autowired
    public PictureUploadController(PicturesUploadProperties uploadProperties) {
        picturesDir = uploadProperties.getUploadPath();
        anonymousPicture = uploadProperties.getAnonymousPicture();
    }

    @RequestMapping(value = "/upload", method = RequestMethod.GET)
    public String uploadPage() {
        return "profile/uploadPage";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String onUpload(MultipartFile file, RedirectAttributes redirectAttributes) {
        if(file.isEmpty() || !isImage(file)) {
            redirectAttributes.addFlashAttribute("error",
                    "Incorrect file. Please upload a picture.");
            return "redirect:/upload";
        }

        try {
            copyFileToPictures(file);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error",
                    "An exception occurs during upload. Please try another picture.");
            e.printStackTrace();
            return "redirect:/upload";
        }

        return "profile/uploadPage";
    }

    @RequestMapping(value = "/uploadedPicture")
    public void getUploadedPicture(HttpServletResponse response) throws IOException {
        response.setHeader("Content-Type", URLConnection.guessContentTypeFromName(anonymousPicture.getFilename()));
        IOUtils.copy(anonymousPicture.getInputStream(), response.getOutputStream());
    }

    @ControllerAdvice
    public class ExceptionProcess {
        private String errorMsg = "An exception occured during upload. Please try another picture. (Maybe due to file size over limit)";

        @ExceptionHandler(FileUploadBase.SizeLimitExceededException.class)
        public String handleException(Exception e, RedirectAttributes redirectAttributes) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", errorMsg);
            return "redirect:/upload";
        }

        @ExceptionHandler(MultipartException.class)
        public String handleMultipartException(Exception e, RedirectAttributes redirectAttributes){
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", errorMsg);
            return "redirect:/upload";
        }

    }

    private Resource copyFileToPictures(MultipartFile file) throws Exception {
        String filename = file.getOriginalFilename();
        File tempFile = File.createTempFile("pic", getFileExtension(filename), picturesDir.getFile());
        //try...with代码块会自动关闭流，即使有异常发生
        try (InputStream in = file.getInputStream(); OutputStream out = new FileOutputStream(tempFile)) {
            IOUtils.copy(in, out);
            return new FileSystemResource(tempFile);
        }
    }

    private boolean isImage(MultipartFile file) {
        return file.getContentType().startsWith("image");
        //通过MIME(Multipurpose Internet Mail Extensions)类型判断是否为图片
    }

    private static String getFileExtension(String name) {
        return name.substring(name.lastIndexOf("."));
    }

}
