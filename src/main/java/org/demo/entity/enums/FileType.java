package org.demo.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum FileType {
	PASSPORT("Passport"),
	DRIVER_LICENSE("Driver license");

	private final String value;
}
