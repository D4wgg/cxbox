package org.demo.service.cxbox.inner;

import org.cxbox.core.crudma.bc.impl.InnerBcDescription;
import org.cxbox.core.dto.rowmeta.FieldsMeta;
import org.cxbox.core.dto.rowmeta.RowDependentFieldsMeta;
import org.cxbox.core.service.rowmeta.FieldMetaBuilder;
import org.demo.dto.cxbox.inner.FileDTO;
import org.springframework.stereotype.Service;

@Service
public class FileReadMeta extends FieldMetaBuilder<FileDTO> {

	@Override
	public void buildRowDependentMeta(RowDependentFieldsMeta<FileDTO> fields, InnerBcDescription bcDescription, Long id,
			Long parentId) {

	}

	@Override
	public void buildIndependentMeta(FieldsMeta<FileDTO> fields, InnerBcDescription bcDescription, Long parentId) {

	}

}
