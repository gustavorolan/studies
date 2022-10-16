package br.com.cwi.crescer.api.service.cloud;
import br.com.cwi.crescer.api.model.SafeSearchAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Base64;
import java.util.Scanner;

import static br.com.cwi.crescer.api.service.cloud.CloudApi.BASE_URL;
import static br.com.cwi.crescer.api.service.cloud.CloudApi.TOKEN;

@Service
public class ImageValidate {
    @Autowired
    private SafeSearchConverterService safeSearchConverterService;
    @Autowired
    private ContentService contentService;

    public void imageValidator (byte[] image) {
       try{

        String encoded = Base64.getEncoder().encodeToString(image);

        URL serverUrl = new URL(BASE_URL.getBaseUrl() + TOKEN.getToken());
        URLConnection urlConnection = serverUrl.openConnection();
        HttpURLConnection httpConnection = (HttpURLConnection)urlConnection;

        httpConnection.setRequestMethod("POST");
        httpConnection.setRequestProperty("Content-Type", "application/json");

        httpConnection.setDoOutput(true);

        BufferedWriter httpRequestBodyWriter = new BufferedWriter(new
                OutputStreamWriter(httpConnection.getOutputStream()));

        httpRequestBodyWriter.write("{\n" +
                "  \"requests\": [\n" +
                "    {\n" +
                "      \"image\": {\n" +
                "        \"content\": \"" +encoded +"\"}," +
                "      \"features\": [\n" +
                "        {\n" +
                "          \"type\": \"SAFE_SEARCH_DETECTION\"\n" +
                "        }\n" +
                "      ]\n" +
                "    }\n" +
                "  ]\n" +
                "}");
        httpRequestBodyWriter.close();



        Scanner httpResponseScanner = new Scanner (httpConnection.getInputStream());

        String resp = "";
        while (httpResponseScanner.hasNext()) {
            String line = httpResponseScanner.nextLine();
            resp += line;//  alternatively, print the line of response
        }

        httpResponseScanner.close();

           SafeSearchAnnotation safeSearchAnnotation = safeSearchConverterService.converter(resp);

           contentService.verifyContent(safeSearchAnnotation);

        }
       catch (IOException e){
           throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Cloud vision problem");
       }
    }
}

