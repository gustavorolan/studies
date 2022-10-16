package br.com.cwi.crescer.api.service.vimeo;
import com.clickntap.vimeo.Vimeo;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class VimeoEditVideoService {
    public void edit (String videoEndPoint, String name,String desc) {
        Vimeo vimeo = new Vimeo(VimeoTok.TOKEN.getToken());
        String license = "";
        String privacyView = "disable";
        String privacyEmbed = "whitelist";
        String domain = "localhost:3000";
        boolean reviewLink = false;
        try{
            vimeo.updateVideoMetadata(videoEndPoint, name, desc, license, privacyView, privacyEmbed, reviewLink);
            vimeo.addVideoPrivacyDomain(videoEndPoint,domain);
        }catch (Exception e){
            throw  new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Vimeo Problem");
        }
    }
}
