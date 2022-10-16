package br.com.cwi.crescer.api.service.vimeo;
import com.clickntap.vimeo.Vimeo;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class VimeoRemoveVideoService {
    public void remove (String videoEndPoint){
        
        Vimeo vimeo = new Vimeo(VimeoTok.TOKEN.getToken());
        try{
            vimeo.removeVideo(videoEndPoint);
        }catch (Exception e){
            throw  new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Vimeo Problem");
        }
    }
}
