package org.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.cxbox.model.core.entity.BaseEntity;

@Entity
@Table(name = "MEETING_DOCUMENTS")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = {}, callSuper = true)
public class MeetingDocuments extends BaseEntity {

	private String notes;

	@Column
	private String file;

	@Column
	private String fileId;

	@ManyToOne
	@JoinColumn(name = "MEETING_ID")
	private Meeting meeting;
}