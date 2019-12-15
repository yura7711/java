package com.geekbrains.gwt.common;

public class TaskAddDto_Generated_JsonEncoderDecoder_ extends org.fusesource.restygwt.client.AbstractJsonEncoderDecoder<com.geekbrains.gwt.common.TaskAddDto> {
  
  public static final TaskAddDto_Generated_JsonEncoderDecoder_ INSTANCE = new TaskAddDto_Generated_JsonEncoderDecoder_();
  
  public com.google.gwt.json.client.JSONValue encode(com.geekbrains.gwt.common.TaskAddDto value) {
    if( value==null ) {
      return getNullType();
    }
    com.google.gwt.json.client.JSONObject rc = new com.google.gwt.json.client.JSONObject();
    com.geekbrains.gwt.common.TaskAddDto parseValue = (com.geekbrains.gwt.common.TaskAddDto)value;
    isNotNullValuePut(org.fusesource.restygwt.client.AbstractJsonEncoderDecoder.LONG.encode(parseValue.getId()), rc, "id");
    isNotNullValuePut(org.fusesource.restygwt.client.AbstractJsonEncoderDecoder.STRING.encode(parseValue.getName()), rc, "name");
    isNotNullValuePut(org.fusesource.restygwt.client.AbstractJsonEncoderDecoder.LONG.encode(parseValue.getAuthor_id()), rc, "author_id");
    isNotNullValuePut(org.fusesource.restygwt.client.AbstractJsonEncoderDecoder.LONG.encode(parseValue.getExecutor_id()), rc, "executor_id");
    isNotNullValuePut(org.fusesource.restygwt.client.AbstractJsonEncoderDecoder.STRING.encode(parseValue.getDescription()), rc, "description");
    return rc;
  }
  
  public com.geekbrains.gwt.common.TaskAddDto decode(com.google.gwt.json.client.JSONValue value) {
    if( value == null || value.isNull()!=null ) {
      return null;
    }
    com.google.gwt.json.client.JSONObject object = toObject(value);
    com.geekbrains.gwt.common.TaskAddDto rc = new com.geekbrains.gwt.common.TaskAddDto();
    rc.setId(getValueToSet(org.fusesource.restygwt.client.AbstractJsonEncoderDecoder.LONG.decode(object.get("id")), null));
    rc.setName(getValueToSet(org.fusesource.restygwt.client.AbstractJsonEncoderDecoder.STRING.decode(object.get("name")), null));
    rc.setAuthor_id(getValueToSet(org.fusesource.restygwt.client.AbstractJsonEncoderDecoder.LONG.decode(object.get("author_id")), null));
    rc.setExecutor_id(getValueToSet(org.fusesource.restygwt.client.AbstractJsonEncoderDecoder.LONG.decode(object.get("executor_id")), null));
    rc.setDescription(getValueToSet(org.fusesource.restygwt.client.AbstractJsonEncoderDecoder.STRING.decode(object.get("description")), null));
    return rc;
  }
  
}
