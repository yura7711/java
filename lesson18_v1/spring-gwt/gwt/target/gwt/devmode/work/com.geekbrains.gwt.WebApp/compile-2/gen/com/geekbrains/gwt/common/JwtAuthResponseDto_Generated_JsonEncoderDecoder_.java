package com.geekbrains.gwt.common;

public class JwtAuthResponseDto_Generated_JsonEncoderDecoder_ extends org.fusesource.restygwt.client.AbstractJsonEncoderDecoder<com.geekbrains.gwt.common.JwtAuthResponseDto> {
  
  public static final JwtAuthResponseDto_Generated_JsonEncoderDecoder_ INSTANCE = new JwtAuthResponseDto_Generated_JsonEncoderDecoder_();
  
  public com.google.gwt.json.client.JSONValue encode(com.geekbrains.gwt.common.JwtAuthResponseDto value) {
    if( value==null ) {
      return getNullType();
    }
    com.google.gwt.json.client.JSONObject rc = new com.google.gwt.json.client.JSONObject();
    com.geekbrains.gwt.common.JwtAuthResponseDto parseValue = (com.geekbrains.gwt.common.JwtAuthResponseDto)value;
    isNotNullValuePut(org.fusesource.restygwt.client.AbstractJsonEncoderDecoder.STRING.encode(parseValue.getToken()), rc, "token");
    return rc;
  }
  
  public com.geekbrains.gwt.common.JwtAuthResponseDto decode(com.google.gwt.json.client.JSONValue value) {
    if( value == null || value.isNull()!=null ) {
      return null;
    }
    com.google.gwt.json.client.JSONObject object = toObject(value);
    com.geekbrains.gwt.common.JwtAuthResponseDto rc = new com.geekbrains.gwt.common.JwtAuthResponseDto();
    rc.setToken(getValueToSet(org.fusesource.restygwt.client.AbstractJsonEncoderDecoder.STRING.decode(object.get("token")), null));
    return rc;
  }
  
}
