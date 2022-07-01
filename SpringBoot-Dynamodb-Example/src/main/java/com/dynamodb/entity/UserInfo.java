package com.dynamodb.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamoDBDocument
@Builder(toBuilder = true)
public class UserInfo {
    private String name;
    private String emailAddress;
    private String phoneNumber;
}
