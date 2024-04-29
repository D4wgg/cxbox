package org.demo.service.cxbox.inner;

import java.util.Arrays;
import org.cxbox.core.crudma.bc.BusinessComponent;
import org.cxbox.core.crudma.impl.VersionAwareResponseService;
import org.cxbox.core.dto.DrillDownType;
import org.cxbox.core.dto.rowmeta.ActionResultDTO;
import org.cxbox.core.dto.rowmeta.CreateResult;
import org.cxbox.core.dto.rowmeta.PostAction;
import org.cxbox.core.service.action.ActionScope;
import org.cxbox.core.service.action.Actions;
import org.cxbox.core.service.action.CxboxActionIconSpecifier;
import org.demo.controller.CxboxRestController;
import org.demo.dto.cxbox.inner.FileDTO;
import org.demo.dto.cxbox.inner.FileDTO_;
import org.demo.entity.File;
import org.demo.entity.enums.FileEditStep;
import org.demo.repository.ClientRepository;
import org.demo.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileWriteService extends VersionAwareResponseService<FileDTO, File> {

	@Autowired
	private FileRepository fileRepository;

	@Autowired
	private ClientRepository clientRepository;

	public FileWriteService() {
		super(FileDTO.class, File.class, null, FileWriteMeta.class);
	}

	@Override
	protected CreateResult<FileDTO> doCreateEntity(File entity, BusinessComponent bc) {
		fileRepository.save(entity);
		return new CreateResult<>(entityToDto(bc, entity)).setAction(PostAction.drillDown(
				DrillDownType.INNER,
				"/screen/file/view/filelist/"
						+ CxboxRestController.fileEdit
						+ "/" + entity.getId()
		));
	}

	@Override
	protected ActionResultDTO<FileDTO> doUpdateEntity(File entity, FileDTO data, BusinessComponent bc) {
		if (data.isFieldChanged(FileDTO_.clientId)) {
			entity.setClient(
					clientRepository.findById(data.getClientId())
							.orElse(null)
			);
		}
		setIfChanged(data, FileDTO_.fileName, entity::setName);
		setIfChanged(data, FileDTO_.type, entity::setType);
		return new ActionResultDTO<>(entityToDto(bc, entity));
	}

	@Override
	public ActionResultDTO<FileDTO> onCancel(BusinessComponent bc) {
		return new ActionResultDTO<FileDTO>().setAction(
				PostAction.drillDown(
						DrillDownType.INNER,
						"/screen/file/"
				));
	}

	@Override
	public Actions<FileDTO> getActions() {
		return Actions.<FileDTO>builder()
				.newAction()
				.scope(ActionScope.RECORD)
				.withAutoSaveBefore()
				.action("saveAndContinue", "Save")
				.invoker((bc, dto) -> new ActionResultDTO<FileDTO>().setAction(
						PostAction.drillDown(
								DrillDownType.INNER,
								"/screen/file/view/filelist"
						)))
				.add()
				.cancelCreate().text("Cancel").withIcon(CxboxActionIconSpecifier.CLOSE, false).add()
				.build();
	}

}
