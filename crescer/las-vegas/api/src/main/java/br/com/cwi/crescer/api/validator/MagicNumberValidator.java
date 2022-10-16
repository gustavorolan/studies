package br.com.cwi.crescer.api.validator;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;

@Component
public class MagicNumberValidator {
    public static final int JPG = -2555936;
    public static final int PNG = -1991225785;

    public void validateAttachment(MultipartFile file) {
        try {
            DataInputStream dataInputStream = new DataInputStream (new BufferedInputStream(file.getInputStream()));
            int magicNumber = dataInputStream.readInt();
            if (!(magicNumber == JPG || magicNumber == PNG)) {
                throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Image type is not acceptable");
            }
        }catch (IOException e){
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Error");}

    }

}
