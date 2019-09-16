package fr.sabb.application.utils;

import java.util.Objects;
import java.util.stream.Stream;

import com.vaadin.ui.AbstractField;
import com.vaadin.ui.AbstractMultiSelect;

public class FormUtils {
	
	public static void resetFields(AbstractField... fields)  {
		Stream.of(fields).filter(Objects::nonNull).forEach(FormUtils::clearField);
	}
	
	public static void resetFields(AbstractMultiSelect... fields) {
		Stream.of(fields).filter(Objects::nonNull).forEach(AbstractMultiSelect::clear);
	}
	
	private static void clearField(AbstractField field) {
		System.out.println(field);
		field.clear();
	}

}
