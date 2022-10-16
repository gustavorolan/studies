package br.com.cwi.crescer.api.controller;

import br.com.cwi.crescer.api.controller.response.AttachmentResponseData;
import br.com.cwi.crescer.api.service.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class AttachmentController {

    @Autowired
    private AttachmentService attachmentService;

    @PostMapping("/upload")
    public AttachmentResponseData uploadFile(@RequestParam("file")MultipartFile file) throws IOException {
        return attachmentService.uploadFile(file);
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String id)  {
        return attachmentService.download(id);
    }
}