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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@AllArgsConstructor
@NoArgsConstructor
public class FileUtils {

    @Value("${file.path.hospital.information}")
    private String hospitalInformationJSONFilePath;

    public List<String> getApiData(List<Long> hospitalIds, String uri) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readValue(new File(hospitalInformationJSONFilePath), JsonNode.class);
        List<String> uriList = new ArrayList<>();
        for(Long id : hospitalIds){
            String data = jsonNode.get(id.toString()).get(uri).asText();
            uriList.add(data);
        }
        return uriList;
    }

}