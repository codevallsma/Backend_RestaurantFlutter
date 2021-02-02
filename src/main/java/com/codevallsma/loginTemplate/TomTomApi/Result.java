
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
    "type",
    "id",
    "score",
    "dist",
    "info",
    "poi",
    "address",
    "position",
    "viewport",
    "entryPoints",
    "dataSources"
})
public class Result {

    @JsonProperty("type")
    private String type;
    @JsonProperty("id")
    private String id;
    @JsonProperty("score")
    private Double score;
    @JsonProperty("dist")
    private Double dist;
    @JsonProperty("info")
    private String info;
    @JsonProperty("poi")
    private Poi poi;
    @JsonProperty("address")
    private Address address;
    @JsonProperty("position")
    private Position position;
    @JsonProperty("viewport")
    private Viewport viewport;
    @JsonProperty("entryPoints")
    private List<EntryPoint> entryPoints = null;
    @JsonProperty("dataSources")
    private DataSources dataSources;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("score")
    public Double getScore() {
        return score;
    }

    @JsonProperty("score")
    public void setScore(Double score) {
        this.score = score;
    }

    @JsonProperty("dist")
    public Double getDist() {
        return dist;
    }

    @JsonProperty("dist")
    public void setDist(Double dist) {
        this.dist = dist;
    }

    @JsonProperty("info")
    public String getInfo() {
        return info;
    }

    @JsonProperty("info")
    public void setInfo(String info) {
        this.info = info;
    }

    @JsonProperty("poi")
    public Poi getPoi() {
        return poi;
    }

    @JsonProperty("poi")
    public void setPoi(Poi poi) {
        this.poi = poi;
    }

    @JsonProperty("address")
    public Address getAddress() {
        return address;
    }

    @JsonProperty("address")
    public void setAddress(Address address) {
        this.address = address;
    }

    @JsonProperty("position")
    public Position getPosition() {
        return position;
    }

    @JsonProperty("position")
    public void setPosition(Position position) {
        this.position = position;
    }

    @JsonProperty("viewport")
    public Viewport getViewport() {
        return viewport;
    }

    @JsonProperty("viewport")
    public void setViewport(Viewport viewport) {
        this.viewport = viewport;
    }

    @JsonProperty("entryPoints")
    public List<EntryPoint> getEntryPoints() {
        return entryPoints;
    }

    @JsonProperty("entryPoints")
    public void setEntryPoints(List<EntryPoint> entryPoints) {
        this.entryPoints = entryPoints;
    }

    @JsonProperty("dataSources")
    public DataSources getDataSources() {
        return dataSources;
    }

    @JsonProperty("dataSources")
    public void setDataSources(DataSources dataSources) {
        this.dataSources = dataSources;
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
