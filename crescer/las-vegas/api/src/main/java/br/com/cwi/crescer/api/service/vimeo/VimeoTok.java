package br.com.cwi.crescer.api.service.vimeo;

public enum VimeoTok {
    TOKEN("6f28d4e5e83b4930c6f651eec0277353");
    private String token;
    VimeoTok(String token){
        this.token=token;
    }
    public String getToken(){
        return token;
    }
}
