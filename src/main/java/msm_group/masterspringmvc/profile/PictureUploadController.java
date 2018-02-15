package msm_group.masterspringmvc.profile;

import msm_group.masterspringmvc.config.PicturesUploadProperties;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sun.plugin2.message.Message;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLConnection;
import java.util.Locale;

@Controller
@SessionAttributes("picturePath")
public class PictureUploadController {

    private final Resource picturesDir;
    private final Resource anonymousPicture;

    private final MessageSource messageSource;

    @Autowired
    public PictureUploadController(PicturesUploadProperties uploadProperties, MessageSource messageSource) {
        picturesDir = uploadProperties.getUploadPath();
        anonymousPicture = uploadProperties.getAnonymousPicture();
        this.messageSource = messageSource;
    }

    @ModelAttribute("picturePath")
    public Resource picturePath() {
        return anonymousPicture;
    }

    @RequestMapping(value = "/upload", method = RequestMethod.GET)
    public String uploadPage() {
        return "profile/uploadPage";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String onUpload(MultipartFile file, RedirectAttributes redirectAttributes,
                           Model model, Locale locale) throws IOException {
        //throw new IOException("TEST HAHA");
        if (file.isEmpty() || !isImage(file)) {
            redirectAttributes.addFlashAttribute("error",
                    messageSource.getMessage("upload.type.mismatch",null,locale));
            return "redirect:/upload";
        }

        Resource picturePath = copyFileToPictures(file);
        model.addAttribute("picturePath", picturePath);

        return "profile/uploadPage";
    }

    @RequestMapping(value = "/uploadedPicture")
    public void getUploadedPicture(HttpServletResponse response, @ModelAttribute("picturePath")Resource picturePath) throws IOException {
        response.setHeader("Content-Type", URLConnection.guessContentTypeFromName(anonymousPicture.getFilename()));
        IOUtils.copy(picturePath.getInputStream(), response.getOutputStream());
    }

    private Resource copyFileToPictures(MultipartFile file) throws IOException {
        String filename = file.getOriginalFilename();
        File tempFile = File.createTempFile("pic", getFileExtension(filename), picturesDir.getFile());
        //try...with代码块会自动关闭流，即使有异常发生
        try (InputStream in = file.getInputStream(); OutputStream out = new FileOutputStream(tempFile)) {
            IOUtils.copy(in, out);
            return new FileSystemResource(tempFile);
        }
    }

    @ExceptionHandler(IOException.class)
    public ModelAndView handleIOException(Locale locale) {
        ModelAndView modelAndView = new ModelAndView("profile/uploadPage");
        modelAndView.addObject("error", messageSource.getMessage("upload.io.exception",null,locale));
        return modelAndView;
    }

    //MultipartException需要在Servlet容器级别处理，见WebConfiguration。
    @RequestMapping(value = "/uploadError")
    public ModelAndView onUploadError(Locale locale) {
        ModelAndView modelAndView = new ModelAndView("profile/uploadPage");
        modelAndView.addObject("error", messageSource.getMessage("upload.filesize.exceed",null,locale));
        return modelAndView;
    }

    private boolean isImage(MultipartFile file) {
        //通过MIME(Multipurpose Internet Mail Extensions)类型判断是否为图片
        return file.getContentType().startsWith("image");
    }

    private static String getFileExtension(String name) {
        return name.substring(name.lastIndexOf("."));
    }

}
