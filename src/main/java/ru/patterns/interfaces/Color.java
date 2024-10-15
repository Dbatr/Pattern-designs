package ru.patterns.interfaces;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import ru.patterns.models.BlueColor;
import ru.patterns.models.RedColor;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = RedColor.class, name = "red"),
        @JsonSubTypes.Type(value = BlueColor.class, name = "blue")
})
public interface Color {
    String applyColor();
}
