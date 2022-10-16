package br.com.cwi.crescer.api.service;

import br.com.cwi.crescer.api.service.vimeo.VimeoInfoVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UploadVideoInfoService {

    @Autowired
    private VimeoInfoVideoService vimeoInfoVideoService;

    public String getPlayerEmbedUrl(String endPoint) {
        return (String) vimeoInfoVideoService.info(endPoint).getJson().get("player_embed_url");
    }
}
