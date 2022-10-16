package br.com.cwi.crescer.api.model;

public enum VideoType {
    REELS(15),
    COURSE(900);

    private int timeMax;

    VideoType(int timeMax){

        this.timeMax=timeMax;
    }

    public int getTimeMax(){

        return timeMax;
    }
}
