
package com.codevallsma.loginTemplate.TomTomApi;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "queryType",
    "queryTime",
    "numResults",
    "offset",
    "totalResults",
    "fuzzyLevel",
    "geoBias"
})
public class Summary {

    @JsonProperty("queryType")
    private String queryType;
    @JsonProperty("queryTime")
    private Integer queryTime;
    @JsonProperty("numResults")
    private Integer numResults;
    @JsonProperty("offset")
    private Integer offset;
    @JsonProperty("totalResults")
    private Integer totalResults;
    @JsonProperty("fuzzyLevel")
    private Integer fuzzyLevel;
    @JsonProperty("geoBias")
    private GeoBias geoBias;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("queryType")
    public String getQueryType() {
        return queryType;
    }

    @JsonProperty("queryType")
    public void setQueryType(String queryType) {
        this.queryType = queryType;
    }

    @JsonProperty("queryTime")
    public Integer getQueryTime() {
        return queryTime;
    }

    @JsonProperty("queryTime")
    public void setQueryTime(Integer queryTime) {
        this.queryTime = queryTime;
    }

    @JsonProperty("numResults")
    public Integer getNumResults() {
        return numResults;
    }

    @JsonProperty("numResults")
    public void setNumResults(Integer numResults) {
        this.numResults = numResults;
    }

    @JsonProperty("offset")
    public Integer getOffset() {
        return offset;
    }

    @JsonProperty("offset")
    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    @JsonProperty("totalResults")
    public Integer getTotalResults() {
        return totalResults;
    }

    @JsonProperty("totalResults")
    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    @JsonProperty("fuzzyLevel")
    public Integer getFuzzyLevel() {
        return fuzzyLevel;
    }

    @JsonProperty("fuzzyLevel")
    public void setFuzzyLevel(Integer fuzzyLevel) {
        this.fuzzyLevel = fuzzyLevel;
    }

    @JsonProperty("geoBias")
    public GeoBias getGeoBias() {
        return geoBias;
    }

    @JsonProperty("geoBias")
    public void setGeoBias(GeoBias geoBias) {
        this.geoBias = geoBias;
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
