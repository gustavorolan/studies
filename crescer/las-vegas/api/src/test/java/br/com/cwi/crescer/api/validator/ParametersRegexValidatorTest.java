package br.com.cwi.crescer.api.validator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ParametersRegexValidatorTest {

    @InjectMocks
    private ParametersRegexValidator parametersRegexValidator;

    @Test
    @DisplayName("Verifies if email is invalid")
    void verifyIfUserIfEmailIsInValid() {
        String invalid = "email";
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            parametersRegexValidator.verifyIfUserIfEmailIsValid(invalid);
        });
        Assertions.assertEquals("400 BAD_REQUEST \"Email não é valido\"", exception.getMessage());
    }

    @Test
    @DisplayName("Verifies if email is valid")
    void verifyIfUserIfEmailIsValid() {
        String invalid = "email@gmail.com";

        parametersRegexValidator.verifyIfUserIfEmailIsValid(invalid);
    }

    @Test
    @DisplayName("Verifies if userName is invalid")
    void verifyIfUserNameIsInvalid() {
        String invalid = "12345";
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            parametersRegexValidator.verifyIfUserNameIsValid(invalid);
        });
        Assertions.assertEquals("400 BAD_REQUEST \"Name is not valid\"", exception.getMessage());
    }

    @Test
    @DisplayName("Verifies if userName is valid")
    void verifyIfUserNameIsValid() {
        String invalid = "George Orwell";

        parametersRegexValidator.verifyIfUserNameIsValid(invalid);
    }

    @Test
    @DisplayName("Verifies if password is invalid")
    void verifyIfUserPasswordIsInvalid() {
        String invalid = "12345";
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            parametersRegexValidator.verifyIfUserPasswordIsValid(invalid);
        });
        Assertions.assertEquals("400 BAD_REQUEST \"Password is not valid\"", exception.getMessage());
    }

    @Test
    @DisplayName("Verifies if password is valid")
    void verifyIfUserPasswordIsValid() {
        String invalid = "0123456789$abcdefgAB";

        parametersRegexValidator.verifyIfUserPasswordIsValid(invalid);
     }

    @Test
    @DisplayName("Verifies if image name is invalid")
    void verifyIfImageIdIsInvalid() {
        String invalid = "//";
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            parametersRegexValidator.verifyIfImageIdIsValid(invalid);
        });
        Assertions.assertEquals("400 BAD_REQUEST \"Image is not valid\"", exception.getMessage());

    }

    @Test
    @DisplayName("Verifies if image name is valid")
    void verifyIfImageIdIsValid() {
        String invalid = "A1B2C3";

        parametersRegexValidator.verifyIfImageIdIsValid(invalid);
    }

    @Test
    @DisplayName("Verifies if attachment path name is invalid")
    void invalidateAttachmentFileName() {
        String invalid = "image.mp4";
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            parametersRegexValidator.validateAttachmentFileName(invalid);
        });
        Assertions.assertEquals("400 BAD_REQUEST \"Image content type is not valid\"", exception.getMessage());

    }

    @Test
    @DisplayName("Verifies if attachment path name is valid")
    void validateAttachmentFileName() {
        String invalid = "image.jpg";
        parametersRegexValidator.validateAttachmentFileName(invalid);
    }

    @Test
    @DisplayName("Verifies if pdf file name is invalid")
    void invalidatePdfFileName() {
        String invalid = "image.mp4";
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            parametersRegexValidator.validatePdfFileName(invalid);
        });
        Assertions.assertEquals("400 BAD_REQUEST \"pdf is not valid\"", exception.getMessage());

    }

    @Test
    @DisplayName("Verifies if pdf file name is valid")
    void validatePdfFileName() {
        String invalid = "image.pdf";

        parametersRegexValidator.validatePdfFileName(invalid);
    }

    @Test
    @DisplayName("Verifies if attachment content type is invalid")
    void invalidateAttachmentContentType() {
        String invalid = "image";
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            parametersRegexValidator.validateAttachmentContentType(invalid);
        });
        Assertions.assertEquals("400 BAD_REQUEST \"Image content type is not valid\"", exception.getMessage());
    }

    @Test
    @DisplayName("Verifies if attachment content type is valid")
    void validateAttachmentContentType() {
        String invalid = "image/jpg";

        parametersRegexValidator.validateAttachmentContentType(invalid);
    }


    @Test
    @DisplayName("Verifies if pdf content type is invalid")
    void invalidatePdfContentType() {
        String invalid = "pdf";
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            parametersRegexValidator.validatePdfContentType(invalid);
        });
        Assertions.assertEquals("400 BAD_REQUEST \"pdf is not valid\"", exception.getMessage());
    }

    @Test
    @DisplayName("Verifies if pdf content type is valid")
    void validatePdfContentType() {
        String invalid = "application/pdf";

        parametersRegexValidator.validatePdfContentType(invalid);
    }
}