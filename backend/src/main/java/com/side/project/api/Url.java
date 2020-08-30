package com.side.project.api;

import lombok.Getter;

@Getter
public enum Url {
    // 아파트 매매
    APARTMENT_TRADE("http://openapi.molit.go.kr/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcAptTradeDev?serviceKey=mbBUq5L3V5X6Rhyd1NmswQKop1cFXTyVSQyZKWEWn9679c8rV9RglyV%2FcqjxkCEaH1OWlfosY1D74B%2Bvn3xDDg%3D%3D");

    private String url;

    Url(String url){
        this.url = url;
    }
}
