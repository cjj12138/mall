package priv.jesse.mall.entity;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import java.io.Serializable;

public class NameValue implements Serializable {
    private String Value;
    private String name;

    public NameValue(String value, String name) {
        Value = value;
        this.name = name;
    }

    public String getValue() {
        return Value;
    }

    public void setValue(String value) {
        Value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof NameValue)) return false;

        NameValue nameValue = (NameValue) o;

        return new EqualsBuilder()
                .append(getValue(), nameValue.getValue())
                .append(getName(), nameValue.getName())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getValue())
                .append(getName())
                .toHashCode();
    }
}
