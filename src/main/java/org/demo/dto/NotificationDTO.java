package org.demo.dto;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import org.demo.entity.Notification;

@Setter
@Getter
public class NotificationDTO {

	private Long id;

	private Boolean isRead;

	private String text;

	private LocalDateTime createTime;

	public NotificationDTO(Notification notification) {
		this.id = notification.getId();
		this.isRead = notification.getIsRead();
		this.text = notification.getText();
		this.createTime = notification.getCreatedDate();
	}

}
