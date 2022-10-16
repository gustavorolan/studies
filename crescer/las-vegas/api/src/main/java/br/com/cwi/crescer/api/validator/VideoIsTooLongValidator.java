package br.com.cwi.crescer.api.validator;

import com.coremedia.iso.IsoFile;
import com.googlecode.mp4parser.DataSource;
import com.googlecode.mp4parser.FileDataSourceImpl;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.FileChannel;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Component
public class VideoIsTooLongValidator {
    public void verify (InputStream file, int time) throws IOException {
            FileInputStream inputStream = (FileInputStream) file;
            FileChannel fileChannel = inputStream.getChannel();
            DataSource channel = new FileDataSourceImpl(fileChannel);
            IsoFile isoFile = new IsoFile(channel);

            Double durationSeconds = Double.valueOf(isoFile.getMovieBox().getMovieHeaderBox().getDuration()/
                    Double.valueOf(isoFile.getMovieBox().getMovieHeaderBox().getTimescale()));

            isoFile.close();

            if(!(0<durationSeconds && durationSeconds<= time)) {
                throw new ResponseStatusException(BAD_REQUEST, "Video is way too long | ");
            }

    }

}
