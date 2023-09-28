package com.test.palindromesearch.model;

import com.test.palindromesearch.config.CustomListSize;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Size;

import java.util.List;

public class MatrizModel {

    @CustomListSize(min = 2, max = 10, message = "Columns need to have between 2 and 10 elements")
    private List<ColumnModel> columns;

    //    @AssertTrue(message = "The Columns needs to be square matrix.")
    //    public boolean isColumnCustomValid() {
    //        int columnSize = columns.size();
    //        int lineSize = 0;
    //
    //        for (int i = 0; i < columns.get(i).getLines().size(); i++ ) {
    //            lineSize = i;
    //        }
    //
    //        if (columnSize != lineSize) {
    //            return false;
    //        }
    //        return true;
    //
    //    }


    public List<ColumnModel> getColumns() {
        return columns;
    }

    public void setColumns(List<ColumnModel> columns) {
        this.columns = columns;
    }

    public MatrizModel(List<ColumnModel> columns) {
        this.columns = columns;
    }
    public MatrizModel(MatrizModel matrizModel) {
        this.columns = matrizModel.getColumns();
    }

    public MatrizModel() {

    }
}
