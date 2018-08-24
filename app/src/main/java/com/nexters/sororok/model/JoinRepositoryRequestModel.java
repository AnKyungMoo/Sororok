package com.nexters.sororok.model;

public class JoinRepositoryRequestModel {
    private String code;
    private int memberId;
    private int repositoryId;

    public JoinRepositoryRequestModel(String code, int memberId, int repositoryId) {
        this.code = code;
        this.memberId = memberId;
        this.repositoryId = repositoryId;
    }
}
