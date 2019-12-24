package com.geekbrains.gwt.common;

public class StatusDto_Generated_JsonEncoderDecoder_ extends org.fusesource.restygwt.client.AbstractJsonEncoderDecoder<com.geekbrains.gwt.common.StatusDto> {
  
  public static final StatusDto_Generated_JsonEncoderDecoder_ INSTANCE = new StatusDto_Generated_JsonEncoderDecoder_();
  
  public com.google.gwt.json.client.JSONValue encode(com.geekbrains.gwt.common.StatusDto value) {
    if( value==null ) {
      return getNullType();
    }
    com.google.gwt.json.client.JSONObject rc = new com.google.gwt.json.client.JSONObject();
    com.geekbrains.gwt.common.StatusDto parseValue = (com.geekbrains.gwt.common.StatusDto)value;
    isNotNullValuePut(org.fusesource.restygwt.client.AbstractJsonEncoderDecoder.INT.encode(parseValue.getStatusId()), rc, "statusId");
    isNotNullValuePut(org.fusesource.restygwt.client.AbstractJsonEncoderDecoder.STRING.encode(parseValue.getRusTitle()), rc, "rusTitle");
    return rc;
  }
  
  public com.geekbrains.gwt.common.StatusDto decode(com.google.gwt.json.client.JSONValue value) {
    if( value == null || value.isNull()!=null ) {
      return null;
    }
    com.google.gwt.json.client.JSONObject object = toObject(value);
    com.geekbrains.gwt.common.StatusDto rc = new com.geekbrains.gwt.common.StatusDto();
    rc.setStatusId(getValueToSet(org.fusesource.restygwt.client.AbstractJsonEncoderDecoder.INT.decode(object.get("statusId")), null));
    rc.setRusTitle(getValueToSet(org.fusesource.restygwt.client.AbstractJsonEncoderDecoder.STRING.decode(object.get("rusTitle")), null));
    return rc;
  }
  
}
