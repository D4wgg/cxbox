package org.demo.entity.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import java.util.Arrays;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import org.demo.entity.File;

@Getter
@AllArgsConstructor
public enum FileEditStep {
	EDIT_FILE_NAME_STEP("Fill file name", "/screen/filepopup/view/fileeditname/"),
	EDIT_FILE_TYPE_STEP("Fill file type", "/screen/filepopup/view/fileedittype/"),
	EDIT_FILE_CLIENT_STEP("Add client the file uploaded by", "/screen/filepopup/view/fileeditclient/");

	@JsonValue
	private final String value;
	private final String editView;

	@NonNull
	public static Optional<FileEditStep> getNextEditStep(File file) {
		return Arrays.stream(FileEditStep.values())
				.filter(v -> v.ordinal() > file.getEditStep().ordinal())
				.findFirst();
	}

	@NonNull
	public static Optional<FileEditStep> getPreviousEditStep(File file) {
		return Arrays.stream(FileEditStep.values())
				.filter(v -> v.ordinal() < file.getEditStep().ordinal())
				.min((v1, v2) -> Integer.compare(v2.ordinal(), v1.ordinal()));
	}


}
