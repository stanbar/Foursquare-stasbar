
package com.stasbar.foursquare_stasbar.POJOs;

import java.util.HashMap;
import java.util.Map;
public class Contact {

    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
