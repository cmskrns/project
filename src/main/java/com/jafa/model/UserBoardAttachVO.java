package com.jafa.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserBoardAttachVO {
	
	private String uuid;
	private String uploadPath;
	private String fileName;
	private boolean fileType;
	private Long bno;
	
	public String getImageName() {
		StringBuilder builder = new StringBuilder();
		builder.append(uploadPath.replace("\\", "/"))
		.append("/").append(uuid).append("_").append(fileName);
		return builder.toString();
	}
}
