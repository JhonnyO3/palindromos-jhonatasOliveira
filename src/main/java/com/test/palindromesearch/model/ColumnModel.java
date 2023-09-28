package com.test.palindromesearch.model;

import com.test.palindromesearch.config.CustomListSize;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;
@Data
public class ColumnModel {

    @CustomListSize(min = 2, max = 10, message = "Lines need to have between 2 and 10 elements")
    @NotNull
    private List<String> lines;

    public List<String> getLines() {
        return lines;
    }

    public void setLines(List<String> lines) {
        this.lines = lines;
    }

    public ColumnModel(List<String> lines) {
        this.lines = lines;
    }

    public ColumnModel() {

    }
}
