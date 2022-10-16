package br.com.cwi.crescer.api.service.vimeo;
import com.clickntap.vimeo.Vimeo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

@Service
public class VimeoUploadVideoService {
    @Autowired
    private  VimeoEditVideoService vimeoEditVideoService;

    public String upload (MultipartFile video, String name, String desc) {

        Vimeo vimeo = new Vimeo(VimeoTok.TOKEN.getToken());
        try {
            boolean upgradeTo1080 = true;

            String videoEndPoint = vimeo.addVideo(video.getBytes(), upgradeTo1080);

            vimeoEditVideoService.edit(videoEndPoint,name,desc);

            return videoEndPoint;
        }catch (Exception e){
            throw  new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Vimeo Problem");
        }
    }
}
