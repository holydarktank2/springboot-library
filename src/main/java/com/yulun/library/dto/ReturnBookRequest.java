package com.yulun.library.dto;

import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public class ReturnBookRequest {
    @NotEmpty
    List<Integer> recordIdList;

    public List<Integer> getRecordIdList() {
        return recordIdList;
    }

    public void setRecordIdList(List<Integer> recordIdList) {
        this.recordIdList = recordIdList;
    }
}
