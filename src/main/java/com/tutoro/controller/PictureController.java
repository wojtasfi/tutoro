package com.tutoro.controller;

import com.tutoro.entities.Tutor;
import com.tutoro.service.TutorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Locale;

@Controller
@RequestMapping("/picture")
public class PictureController {

    private final MessageSource messageSource;

    private static Logger LOGGER = LoggerFactory.getLogger(PictureController.class);

    @Autowired
    private TutorService tutorService;

    @Autowired
    public PictureController(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String onUpload(@RequestParam MultipartFile file, @RequestParam Long id,
                           RedirectAttributes redirectAttrs, HttpServletRequest request) throws IOException {
        Tutor tutor = tutorService.findOne(id);
        if (file.isEmpty() || !isImage(file)) {
            redirectAttrs.addFlashAttribute("error", "It is not a picture file!");
            return "redirect:/tutor/profile/" + tutor.getUsername();
        }

        File image = multipartToFile(file);
        byte[] byteImage = readContentIntoByteArray(image);

        tutor.setProfilePic(byteImage);

        tutorService.saveTutor(tutor);

        return "redirect:/tutor/profile/" + tutor.getUsername();
    }

    @RequestMapping(value = "/upload", method = RequestMethod.GET)
    public String uploadPage(@RequestParam Long id, Model model) {

        Tutor tutor = tutorService.findOne(id);
        LOGGER.info(tutor.toString());

        if (!model.containsAttribute("tutor")) {
            model.addAttribute("tutor", tutor);
        }
        return "uploadPicture";
    }


    @ExceptionHandler(IOException.class)
    public ModelAndView handleIOException(Locale locale) {
        ModelAndView modelAndView = new ModelAndView("profile/profilePage");
        modelAndView.addObject("error", messageSource.getMessage("upload.io.exception", null, locale));
        return modelAndView;
    }

    @RequestMapping("uploadError")
    public ModelAndView onUploadError(Locale locale) {
        ModelAndView modelAndView = new ModelAndView("profile/profilePage");
        modelAndView.addObject("error", messageSource.getMessage("upload.file.too.big", null, locale));
        return modelAndView;
    }


    @RequestMapping(value = "/profilepic", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<byte[]> downloadUserAvatarImage(@RequestParam("username") String username) throws IOException {
        Tutor tutor = tutorService.findByUsername(username);

        byte[] imageContent = tutor.getProfilePic();
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);

        if (imageContent == null) {
            File file = new ClassPathResource("static/images/ninja.jpg").getFile();
            byte[] deafultPicture = readContentIntoByteArray(file);
            return new ResponseEntity<>(deafultPicture, headers, HttpStatus.OK);

        }
        return new ResponseEntity<>(imageContent, headers, HttpStatus.OK);
    }

    public File multipartToFile(MultipartFile multipart) throws IllegalStateException, IOException {
        File convFile = new File(multipart.getOriginalFilename());
        convFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(multipart.getBytes());
        fos.close();
        return convFile;
    }

    private boolean isImage(MultipartFile file) {
        return file.getContentType().startsWith("image");
    }

    private static String getFileExtension(String name) {
        return name.substring(name.lastIndexOf("."));
    }

    private static byte[] readContentIntoByteArray(File file) {
        byte[] bFile = new byte[(int) file.length()];
        try (FileInputStream fileInputStream = new FileInputStream(file)) {

            fileInputStream.read(bFile);

        } catch (Exception e) {
            LOGGER.error("Could not read file", e);
        }
        return bFile;
    }

}
