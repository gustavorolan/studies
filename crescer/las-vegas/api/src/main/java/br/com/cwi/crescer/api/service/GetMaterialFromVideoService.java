package br.com.cwi.crescer.api.service;

import br.com.cwi.crescer.api.model.Video;
import br.com.cwi.crescer.api.service.finder.FindVideoWithThrow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class GetMaterialFromVideoService {
    @Autowired
    private FindVideoWithThrow findVideoWithThrow;

    public ResponseEntity<Resource> download(Long id) {
        Video video = findVideoWithThrow.findByIdAndActiveWithException(id, true);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/pdf"))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment")
                .body(new ByteArrayResource(video.getSupportMaterial()));
    }
}
