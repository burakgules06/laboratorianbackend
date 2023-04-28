package com.laborantproject.laborantproject.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttachmentDataResponse {
    private String fileName;
    private String downloadUrl;
    private String fileType;
    private Long fileSize;
}
