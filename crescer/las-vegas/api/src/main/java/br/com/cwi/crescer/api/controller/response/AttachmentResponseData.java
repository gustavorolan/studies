package br.com.cwi.crescer.api.controller.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class AttachmentResponseData {
    private String id;
    private String fileName;
    private String downloadURL;
    private String fileType;
    private long fileSize;
}
