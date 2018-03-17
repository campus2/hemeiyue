package com.hemeiyue.util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class DateHHmmJSONSerializer extends JsonSerializer<Date> {

	@Override
	public void serialize(Date date, JsonGenerator jsonGen, SerializerProvider arg2)
			throws IOException, JsonProcessingException {
		SimpleDateFormat fmt = new SimpleDateFormat("HH:mm");
		jsonGen.writeString(fmt.format(date));
	}

}
