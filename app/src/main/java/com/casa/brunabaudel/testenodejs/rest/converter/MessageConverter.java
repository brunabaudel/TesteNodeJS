package com.casa.brunabaudel.testenodejs.rest.converter;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestClientException;

import java.io.IOException;

public class MessageConverter extends MappingJackson2HttpMessageConverter {
    /**
     */
    public MessageConverter() {
        super();

        ObjectMapper om = new ObjectMapper();
        om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        this.setObjectMapper(om);
    }

    @Override
    protected Object readInternal(Class<?> clazz, HttpInputMessage inputMessage) throws IOException {
// rafael.volpato: app crash on motorolas (moto g and x)
        try {
            return super.readInternal(clazz, inputMessage);
        } catch (HttpMessageNotWritableException e) {
            throw new RestClientException("Error on Rest", e);
        } catch (IOException e) {
            throw new RestClientException("Error on Rest", e);
        }
    }

    @Override
    protected void writeInternal(Object object, HttpOutputMessage outputMessage) throws IOException {
// rafael.volpato: app crash on motorolas (moto g and x)
        try {
            super.writeInternal(object, outputMessage);
        } catch (HttpMessageNotWritableException e) {
            throw new IOException(e);
        }
    }
}