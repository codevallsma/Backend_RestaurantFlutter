
package com.codevallsma.loginTemplate.TomTomApi;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "poiDetails"
})
public class DataSources {

    @JsonProperty("poiDetails")
    private List<PoiDetail> poiDetails = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("poiDetails")
    public List<PoiDetail> getPoiDetails() {
        return poiDetails;
    }

    @JsonProperty("poiDetails")
    public void setPoiDetails(List<PoiDetail> poiDetails) {
        this.poiDetails = poiDetails;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
