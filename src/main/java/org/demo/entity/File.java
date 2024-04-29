package org.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.cxbox.model.core.entity.BaseEntity;
import org.demo.entity.enums.FileEditStep;
import org.demo.entity.enums.FileType;

@Entity
@Table(name = "FILE")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = {}, callSuper = true)
public class File extends BaseEntity {

		private String name;

		@Enumerated(EnumType.STRING)
		private FileType type;

		@ManyToOne
		@JoinColumn(name = "CLIENT_ID")
		private Client client;

		@Enumerated(EnumType.STRING)
		private FileEditStep editStep = FileEditStep.EDIT_FILE_NAME_STEP;

	public FileEditStep getEditStep() {
		return Optional.ofNullable(editStep)
				.orElse(FileEditStep.EDIT_FILE_NAME_STEP);
	}
}
