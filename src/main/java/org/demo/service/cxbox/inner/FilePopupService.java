package org.demo.service.cxbox.inner;

import java.util.Arrays;
import lombok.NonNull;
import org.cxbox.core.crudma.bc.BusinessComponent;
import org.cxbox.core.crudma.impl.VersionAwareResponseService;
import org.cxbox.core.dto.DrillDownType;
import org.cxbox.core.dto.rowmeta.ActionResultDTO;
import org.cxbox.core.dto.rowmeta.CreateResult;
import org.cxbox.core.dto.rowmeta.PostAction;
import org.cxbox.core.dto.rowmeta.PreAction;
import org.cxbox.core.service.action.ActionAvailableChecker;
import org.cxbox.core.service.action.ActionScope;
import org.cxbox.core.service.action.Actions;
import org.cxbox.core.service.action.CxboxActionIconSpecifier;
import org.demo.conf.cxbox.extension.action.ActionsExt;
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
public class FilePopupService extends VersionAwareResponseService<FileDTO, File> {

	@Autowired
	private FileRepository fileRepository;

	@Autowired
	private ClientRepository clientRepository;

	public FilePopupService() {
		super(FileDTO.class, File.class, null, FilePopupMeta.class);
	}

	@Override
	protected CreateResult<FileDTO> doCreateEntity(File entity, BusinessComponent bc) {
		fileRepository.save(entity);
//		return new CreateResult<>(entityToDto(bc, entity));
		return new CreateResult<>(entityToDto(bc, entity)).setAction(
				PostAction.drillDown(
						DrillDownType.INNER,
						entity.getEditStep().getEditView() + CxboxRestController.fileEditPopup + "/" + entity.getId()
				)
		);
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
		setIfChanged(data, FileDTO_.editStep, entity::setEditStep);
		return new ActionResultDTO<>(entityToDto(bc, entity));
	}

	@Override
	public ActionResultDTO<FileDTO> onCancel(BusinessComponent bc) {
		return new ActionResultDTO<FileDTO>().setAction(
				PostAction.drillDown(
						DrillDownType.INNER,
						"/screen/filepopup"
				)
		);
	}

	private static PreAction confirmWithComment(@NonNull String actionText) {
		return ActionsExt.confirmWithCustomWidget(actionText, "fileEditPopupForm", "Apply", "Cancel");
	}

	@Override
	public Actions<FileDTO> getActions() {
		return Actions.<FileDTO>builder()
				.save().add()
				.newAction()
				.action("edit", "Edit")
				.withoutAutoSaveBefore()
				.invoker((bc, data) -> {
					var file = fileRepository.getById(bc.getIdAsLong());
					return new ActionResultDTO<FileDTO>()
							.setAction(PostAction.drillDown(
									DrillDownType.INNER,
									file.getEditStep().getEditView()
									+ CxboxRestController.fileEditPopup + "/"
									+ bc.getId()
							));
				})
				.add()
				.newAction()
				.scope(ActionScope.RECORD)
				.action("next", "Save and Continue")
				.invoker((bc, dto) -> {
					var file = fileRepository.getById(bc.getIdAsLong());
					var nextStep = FileEditStep.getNextEditStep(file).get();
					file.setEditStep(nextStep);
					fileRepository.save(file);
					return new ActionResultDTO<FileDTO>().setAction(
							PostAction.drillDown(
									DrillDownType.INNER,
									nextStep.getEditView() + CxboxRestController.fileEditPopup + "/" + bc.getId()
							)
					);
				})
				.available(ActionAvailableChecker.and(ActionAvailableChecker.NOT_NULL_ID, bc -> {
					var file = fileRepository.getById(bc.getIdAsLong());
					return FileEditStep.getNextEditStep(file).isPresent();
				}))
				.add()
				.newAction()
				.scope(ActionScope.RECORD)
				.action("finish", "Save and Close")
				.invoker((bc, dto) -> {
					var file = fileRepository.getById(bc.getIdAsLong());
					var firstStep = Arrays.stream(FileEditStep.values()).findFirst().get();
					file.setEditStep(firstStep);
					fileRepository.save(file);
					return new ActionResultDTO<FileDTO>().setAction(
							PostAction.drillDown(
									DrillDownType.INNER,
									"/screen/filepopup"
							)
					);
				})
				.available(ActionAvailableChecker.and(ActionAvailableChecker.NOT_NULL_ID, bc -> {
					var file = fileRepository.getById(bc.getIdAsLong());
					return !FileEditStep.getNextEditStep(file).isPresent();
				}))
				.add()
				.cancelCreate().text("Cancel").available(bc -> true).add()
//				.create().text("Add").withPreAction(confirmWithComment("Enter data")).add()
				.create().text("Add").add()
				.delete().text("Delete").add()
				.build();
	}
}
