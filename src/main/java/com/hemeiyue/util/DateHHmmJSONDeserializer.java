package com.hemeiyue.util;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class DateHHmmJSONDeserializer extends JsonDeserializer<Date> {

	@Override
	public Date deserialize(JsonParser jsonParser, DeserializationContext arg1) throws IOException, JsonProcessingException {
		SimpleDateFormat fmt = new SimpleDateFormat("HH:mm");
		
		try {
			return fmt.parse(jsonParser.getText());
		} catch (ParseException e) {
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		}
	}

}
