package br.com.cwi.crescer.api.validator;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class ParametersRegexValidator {
    public void verifyIfUserIfEmailIsValid(String email) {
        Pattern patternEmail = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$"
                ,Pattern.CASE_INSENSITIVE);
        Matcher matcher = patternEmail.matcher(email);
        if (!matcher.find()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email não é valido");
        }
    }

    public void verifyIfUserNameIsValid(String name) {
        Pattern patternName = Pattern.compile("[A-Za-zÀ-ÖØ-öø-ÿ]", Pattern.CASE_INSENSITIVE);
        Matcher matcher = patternName.matcher(name);
        if (!matcher.find()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Name is not valid");
        }
    }

    public void verifyIfUserPasswordIsValid(String password) {
        Pattern patternPassword = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = patternPassword.matcher(password);
        if (!matcher.find()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Password is not valid");
        }
    }

    public void verifyIfImageIdIsValid(String image) {
        Pattern patternImageId = Pattern.compile("^[\\w*(|\\-)]*$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = patternImageId.matcher(image);
        if (!matcher.find()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Image is not valid");
        }
    }

    public void validateAttachmentFileName(String fileName) {
        Pattern patternAttachmentContentType = Pattern.compile("([^\\s]+(\\.(?i)(png|jpg|jpeg|PNG|JPG|JPEG))$)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = patternAttachmentContentType.matcher(fileName);
        if (!matcher.find()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Image content type is not valid");
        }
    }

    public void validatePdfFileName(String fileName) {
        Pattern patternAttachmentContentType = Pattern.compile("([^\\s]+(\\.(?i)(pdf))$)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = patternAttachmentContentType.matcher(fileName);
        if (!matcher.find()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "pdf is not valid");
        }
    }

    public void validateAttachmentContentType(String contentType) {
        Pattern patternAttachmentContentType = Pattern.compile("([^\\s]+(\\/(?i)(png|jpg|jpeg|PNG|JPG|JPEG))$)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = patternAttachmentContentType.matcher(contentType);
        if (!matcher.find()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Image content type is not valid");
        }
    }

    public void validatePdfContentType(String contentType) {
        final String type = "application/pdf";
        if (!type.equals(contentType)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "pdf is not valid");
        }
    }
}