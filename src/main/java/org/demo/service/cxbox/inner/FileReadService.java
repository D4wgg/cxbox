package org.demo.service.cxbox.inner;

import org.cxbox.core.crudma.bc.BusinessComponent;
import org.cxbox.core.crudma.impl.VersionAwareResponseService;
import org.cxbox.core.dto.DrillDownType;
import org.cxbox.core.dto.rowmeta.ActionResultDTO;
import org.cxbox.core.dto.rowmeta.CreateResult;
import org.cxbox.core.dto.rowmeta.PostAction;
import org.cxbox.core.service.action.ActionScope;
import org.cxbox.core.service.action.Actions;
import org.demo.controller.CxboxRestController;
import org.demo.dto.cxbox.inner.FileDTO;
import org.demo.entity.File;
import org.demo.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileReadService extends VersionAwareResponseService<FileDTO, File> {

	@Autowired
	private FileRepository fileRepository;

	public FileReadService() {
		super(FileDTO.class, File.class, null, FileReadMeta.class);
	}

	@Override
	protected CreateResult<FileDTO> doCreateEntity(File entity, BusinessComponent bc) {
		fileRepository.save(entity);
		return new CreateResult<>(entityToDto(bc, entity))
				.setAction(PostAction.drillDown(
						DrillDownType.INNER,
						"/screen/file/view/fileedit/" + CxboxRestController.fileEdit + "/" + entity.getId()
				));
	}

	@Override
	protected ActionResultDTO<FileDTO> doUpdateEntity(File entity, FileDTO data, BusinessComponent bc) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Actions<FileDTO> getActions() {
		return Actions.<FileDTO>builder()
				.create().text("Add").add()
				.delete().text("Delete").add()
				.newAction()
				.action("edit", "Edit")
				.withoutAutoSaveBefore()
				.scope(ActionScope.RECORD)
				.invoker((bc, data) -> new ActionResultDTO<FileDTO>()
						.setAction(PostAction.drillDown(
								DrillDownType.INNER,
								"/screen/file/view/fileedit/" + CxboxRestController.fileEdit + "/" + bc.getId()
						)))
				.add()
				.build();
	}



}
