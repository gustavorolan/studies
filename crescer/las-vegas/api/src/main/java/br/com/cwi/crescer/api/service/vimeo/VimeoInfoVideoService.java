package br.com.cwi.crescer.api.service.vimeo;

import com.clickntap.vimeo.Vimeo;
import com.clickntap.vimeo.VimeoResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class VimeoInfoVideoService {
    public VimeoResponse info (String videoEndPoint)  {
        
        Vimeo vimeo = new Vimeo(VimeoTok.TOKEN.getToken());
        try{
            return vimeo.getVideoInfo(videoEndPoint);
        }catch (Exception e){
            throw  new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Vimeo Problem");
        }

    }
}
