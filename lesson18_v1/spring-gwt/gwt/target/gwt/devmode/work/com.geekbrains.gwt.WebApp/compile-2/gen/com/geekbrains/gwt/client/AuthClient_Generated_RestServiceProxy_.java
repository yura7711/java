package com.geekbrains.gwt.client;

public class AuthClient_Generated_RestServiceProxy_ implements com.geekbrains.gwt.client.AuthClient, org.fusesource.restygwt.client.RestServiceProxy {
  private org.fusesource.restygwt.client.Resource resource = null;
  
  public void setResource(org.fusesource.restygwt.client.Resource resource) {
    this.resource = resource;
  }
  public org.fusesource.restygwt.client.Resource getResource() {
    if (this.resource == null) {
      String serviceRoot = org.fusesource.restygwt.client.Defaults.getServiceRoot();
      this.resource = new org.fusesource.restygwt.client.Resource(serviceRoot).resolve("/api/v1/");
    }
    return this.resource;
  }
  private org.fusesource.restygwt.client.Dispatcher dispatcher = null;
  
  public void setDispatcher(org.fusesource.restygwt.client.Dispatcher dispatcher) {
    this.dispatcher = dispatcher;
  }
  
  public org.fusesource.restygwt.client.Dispatcher getDispatcher() {
    return this.dispatcher;
  }
  public void authenticate(com.geekbrains.gwt.common.JwtAuthRequestDto authRequest, org.fusesource.restygwt.client.MethodCallback<com.geekbrains.gwt.common.JwtAuthResponseDto> result) {
    final com.geekbrains.gwt.common.JwtAuthRequestDto final_authRequest = authRequest;
    final org.fusesource.restygwt.client.Method __method =
    getResource()
    .resolve("/authenticate")
    .post();
    __method.setDispatcher(this.dispatcher);
    __method.header(org.fusesource.restygwt.client.Resource.HEADER_ACCEPT, org.fusesource.restygwt.client.Resource.CONTENT_TYPE_JSON);
    __method.json(com.geekbrains.gwt.common.JwtAuthRequestDto_Generated_JsonEncoderDecoder_.INSTANCE.encode(authRequest));
    try {
      __method.send(new org.fusesource.restygwt.client.AbstractRequestCallback<com.geekbrains.gwt.common.JwtAuthResponseDto>(__method, result) {
        protected com.geekbrains.gwt.common.JwtAuthResponseDto parseResult() throws Exception {
          try {
            return com.geekbrains.gwt.common.JwtAuthResponseDto_Generated_JsonEncoderDecoder_.INSTANCE.decode(com.google.gwt.json.client.JSONParser.parse(__method.getResponse().getText()));
          } catch (Throwable __e) {
            throw new org.fusesource.restygwt.client.ResponseFormatException("Response was NOT a valid JSON document", __e);
          }
        }
      });
    } catch (com.google.gwt.http.client.RequestException __e) {
      result.onFailure(__method,__e);
    }
  }
}
