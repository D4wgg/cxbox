package org.demo.service.cxbox.inner;

import org.cxbox.core.crudma.bc.impl.InnerBcDescription;
import org.cxbox.core.dto.rowmeta.FieldsMeta;
import org.cxbox.core.dto.rowmeta.RowDependentFieldsMeta;
import org.cxbox.core.service.rowmeta.FieldMetaBuilder;
import org.demo.dto.cxbox.inner.FileDTO;
import org.demo.dto.cxbox.inner.FileDTO_;
import org.demo.entity.enums.FileEditStep;
import org.demo.entity.enums.FileType;
import org.springframework.stereotype.Service;

@Service
public class FilePopupMeta extends FieldMetaBuilder<FileDTO> {

	@Override
	public void buildRowDependentMeta(RowDependentFieldsMeta<FileDTO> fields, InnerBcDescription bcDescription,
			Long id, Long parentId) {
		fields.setEnabled(
				FileDTO_.clientName,
				FileDTO_.clientId,
				FileDTO_.fileName,
				FileDTO_.editStep,
				FileDTO_.type
		);

		fields.setRequired(
				FileDTO_.clientName,
				FileDTO_.clientId,
				FileDTO_.fileName,
				FileDTO_.type
		);

		fields.setEnumValues(FileDTO_.type, FileType.values());
		fields.setEnumValues(FileDTO_.editStep, FileEditStep.values());
	}

	@Override
	public void buildIndependentMeta(FieldsMeta<FileDTO> fields, InnerBcDescription bcDescription, Long parentId) {
		fields.enableFilter(FileDTO_.fileName);
		fields.enableFilter(FileDTO_.type);
	}

}
