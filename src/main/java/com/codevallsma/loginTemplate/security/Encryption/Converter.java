package com.codevallsma.loginTemplate.security.Encryption;

import com.fasterxml.jackson.annotation.JacksonInject;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

@Component
public class Converter extends AbstractHttpMessageConverter<Object> {

    public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

    @JacksonInject
    private ObjectMapper objectMapper =  new ObjectMapper();

    public Converter(){
        super(MediaType.APPLICATION_JSON_UTF8,
                new MediaType("application", "*+json", DEFAULT_CHARSET));
    }

    @Override
    protected boolean supports(Class<?> clazz) {
        return true;
    }

    @Override
    protected Object readInternal(Class<? extends Object> clazz,
                                  HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        return objectMapper.readValue(decrypt(inputMessage.getBody()), clazz);
    }

    @Override
    protected void writeInternal(Object o, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        outputMessage.getBody().write(encrypt(objectMapper.writeValueAsBytes(o)));
         /*try {
            outputMessage.getBody().write(AESencrp.encrypt(o, "Vallsma"));
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }

    private InputStream decrypt(InputStream inputStream){
        // do your decryption here
        return inputStream;
    }

    private byte[] encrypt(byte[] bytesToEncrypt){
        // do your encryption here
        return bytesToEncrypt;
    }
}