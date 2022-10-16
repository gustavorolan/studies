package br.com.cwi.crescer.api.util;

import br.com.cwi.crescer.api.controller.response.AttachmentResponseData;
import br.com.cwi.crescer.api.model.Attachment;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;

public class AttachmentFactory {

    public static AttachmentResponseData getAttachmentResponseData () {
        return  getBuilderAttachmentResponseData().build();
    }

    public static AttachmentResponseData.AttachmentResponseDataBuilder
    getBuilderAttachmentResponseData  () {
        return AttachmentResponseData.builder()
                .id("1")
                .fileName("name")
                .downloadURL("name")
                .fileType("image/png")
                .fileSize(3L);
    }

    public static Attachment getAttachment() {
        return  getBuilderAttachment().build();
    }

    public static Attachment.AttachmentBuilder getBuilderAttachment () {
        return Attachment.builder()
                .id("1")
                .fileName("name")
                .fileType("image/png");
    }
    public static byte[] mockFile() throws IOException {
        return new
                MockMultipartFile("1", "1.png"
                , "image/png", "png".getBytes()).getBytes();

    }

    public static MockMultipartFile mockTextFile() throws IOException {
        return new
                MockMultipartFile("1", "1.txt"
                , "text/txt", "txt".getBytes());

    }
    public static MockMultipartFile mockPdfFile() throws IOException {
        return new
                MockMultipartFile("1", "1.pdf"
                , "text/pdf", "pdf".getBytes());

    }
}
