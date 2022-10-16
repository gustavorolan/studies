package br.com.cwi.crescer.api.service.cloud;



public enum CloudApi {
    BASE_URL("https://vision.googleapis.com/v1/images:annotate?key="),
    TOKEN("AIzaSyDsn17RtWwWQE7EnI5mrLRJuU0dQGwtAuQ");

    private String token;
    private String baseUrl;
    CloudApi(String desc){
        this.baseUrl=desc;
        this.token=desc;
    }
    public String getToken(){
        return token;
    }
    public String getBaseUrl(){
        return baseUrl;
    }
}
