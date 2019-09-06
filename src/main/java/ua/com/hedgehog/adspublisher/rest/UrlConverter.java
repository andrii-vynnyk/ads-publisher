package ua.com.hedgehog.adspublisher.rest;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;
import java.net.URL;

@JsonComponent
public class UrlConverter {
    public static class Serialize extends JsonSerializer<URL> {

        @Override
        public void serialize(URL value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            if (value != null) {
                gen.writeString(value.toString());
            }
        }
    }

    public static class Deserialize extends JsonDeserializer<URL> {

        @Override
        public URL deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
            String urlAsString = p.getText();
            if (urlAsString != null) {
                return new URL(urlAsString);
            }
            return null;
        }
    }
}
