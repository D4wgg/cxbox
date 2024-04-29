package org.demo.dto.cxbox.inner;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import java.util.Optional;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.cxbox.api.data.dto.DataResponseDTO;
import org.cxbox.core.util.filter.SearchParameter;
import org.cxbox.core.util.filter.provider.impl.EnumValueProvider;
import org.demo.entity.File;
import org.demo.entity.enums.FileEditStep;
import org.demo.entity.enums.FileType;

@Getter
@Setter
@NoArgsConstructor
public class FileDTO extends DataResponseDTO {

	@SearchParameter(name = "client.fullName")
	private String clientName;

	@SearchParameter(name = "type", provider = EnumValueProvider.class)
	private FileType type;

	@SearchParameter(name = "fileName")
	private String fileName;

	private Long clientId;

	@Enumerated(EnumType.STRING)
	private FileEditStep editStep;

	public FileDTO(File file) {
		this.id = file.getId().toString();
		this.fileName = file.getName();
		this.type = file.getType();
		this.clientName = file.getClient() == null ? null : file.getClient().getFullName();
		this.editStep = file.getEditStep();
	}

	public FileEditStep getEditStep() {
		return Optional.ofNullable(editStep)
				.orElse(FileEditStep.EDIT_FILE_NAME_STEP);
	}
}
