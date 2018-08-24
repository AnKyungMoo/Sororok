package com.nexters.sororok.model;

public class DestroyRequestModel {
    private int memberId;
    private int repositoryId;

    public DestroyRequestModel(int memberId, int repositoryId) {
        this.memberId = memberId;
        this.repositoryId = repositoryId;
    }
}
