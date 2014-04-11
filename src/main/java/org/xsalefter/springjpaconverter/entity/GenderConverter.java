package org.xsalefter.springjpaconverter.entity;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class GenderConverter implements AttributeConverter<Gender, String> {

	@Override
	public String convertToDatabaseColumn(Gender attribute) {
		switch (attribute) {
		case MALE: return "Male";
		case FEMALE: return "Female";
		default: throw new IllegalStateException("'gender' attribute should not in default state.");
		}
	}

	@Override
	public Gender convertToEntityAttribute(String dbData) {
		switch (dbData) {
		case "Male": return Gender.MALE;
		case "Female": return Gender.FEMALE;
		default: throw new IllegalStateException("'gender' attribute should not in default state.");
		}
	}

}
