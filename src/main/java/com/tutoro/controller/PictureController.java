package com.tutoro.controller;

import com.tutoro.entities.Tutor;
import com.tutoro.service.TutorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Locale;

@Controller
@RequestMapping("/picture")
public class PictureController {

    private final MessageSource messageSource;

    private static Logger LOGGER = LoggerFactory.getLogger(PictureController.class);

    @Value("${profile.pictures.path}")
    String profilePicturesPath;

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
        LOGGER.info(tutor.toString());

        if (file.isEmpty() || !isImage(file)) {
            redirectAttrs.addFlashAttribute("error", "It is not a picture file!");
            return "redirect:/tutor/profile/" + tutor.getUsername();
        }

        createPicDir(tutor.getUsername());

        String fullPicPath = profilePicturesPath + tutor.getUsername() + "\\" + file.getOriginalFilename();
        file.transferTo(new File(fullPicPath));

        tutor.setProfilePicPath(fullPicPath);
        LOGGER.info(tutor.toString());
        tutorService.saveTutor(tutor);

        return "redirect:/tutor/profile/" + tutor.getUsername();
    }

    private void createPicDir(String username) {
        File theDir = new File(profilePicturesPath + username);

        if (!theDir.exists()) {

            try {
                theDir.mkdir();

            } catch (SecurityException e) {
                LOGGER.error("Could not create dir: ", e);
            }

        }
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

    @RequestMapping(value = "/show", method = RequestMethod.GET)
    public BufferedImage showPicture(@RequestParam Long id, Model model) {
        Tutor tutor = tutorService.findOne(id);
        byte[] bytes = null;
        BufferedImage img = null;

        try {
            img = ImageIO.read(new ByteArrayInputStream(bytes));
        } catch (IOException e) {
            LOGGER.error("Could not load image");
        }
        return img;
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

    private boolean isImage(MultipartFile file) {
        return file.getContentType().startsWith("image");
    }

    private static String getFileExtension(String name) {
        return name.substring(name.lastIndexOf("."));
    }

}
