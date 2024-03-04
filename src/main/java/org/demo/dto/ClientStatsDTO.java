package org.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.cxbox.api.data.dto.DataResponseDTO;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class ClientStatsDTO extends DataResponseDTO {

	private String title;

	private Long value;

	private String color;

	private String description;

	private String icon;

	public ClientStatsDTO(ClientStatsDTO entity) {
		this.id = entity.id;
		this.vstamp = entity.vstamp;
		this.title = entity.title;
		this.value = entity.value;
		this.color = entity.color;
		this.description = entity.description;
		this.icon = entity.icon;
	}

}
