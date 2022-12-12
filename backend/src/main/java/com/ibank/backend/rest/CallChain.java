package com.ibank.backend.rest;

import java.util.Map;

public class CallChain{
    
    private String api;
    private RestClient  client;

    private Map<String, ?> _pathParam;
    private Map<String, ?> _urlParam;
    private  Object _data;
    private Map<String, ?> _headers;
  
    CallChain(String api, RestClient client) {
      this.api  = api;
      this.client =client;
    }

   public CallChain pathParam(Map<String, ?> pathParam) {
      this._pathParam = pathParam;
        return this;
    }
    
    public  CallChain urlParam(Map<String, ?> urlParam) {
        this._urlParam = urlParam;
        return this;
      }
      public  CallChain data(Object data){
        this._data = data;
        return this;
      }

      public CallChain  headers(Map<String, ?> headers) {
        this._headers = headers;
        return this;
      }
    
    public Object CallChain(Object data) {
        this.data(data);
        return this.client.call(this.api, this._pathParam, this._urlParam, this._data, this._headers);
    }

}
