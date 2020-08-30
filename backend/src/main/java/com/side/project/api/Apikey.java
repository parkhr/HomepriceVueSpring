package com.side.project.api;

import lombok.Getter;

@Getter
public enum Apikey{
    PUBLIC_DATA_API_KEY("mbBUq5L3V5X6Rhyd1NmswQKop1cFXTyVSQyZKWEWn9679c8rV9RglyV%2FcqjxkCEaH1OWlfosY1D74B%2Bvn3xDDg%3D%3D");

    private String key;

    Apikey(String key){
        this.key = key;
    }
}