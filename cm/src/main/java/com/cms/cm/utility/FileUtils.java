package com.cms.cm.utility;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
@NoArgsConstructor
public class FileUtils {

    @Value("${file.path.hospital.information}")
    private String hospitalInformationJSONFilePath;

    public List<String> getApiUriList(List<Long> hospitalIds, String uri) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readValue(new File(hospitalInformationJSONFilePath), JsonNode.class);
        List<String> uriList = new ArrayList<>();
        for(Long id : hospitalIds){
            uriList.add(jsonNode.get(id.toString()).get(uri).asText());
        }
        return uriList;
    }

}