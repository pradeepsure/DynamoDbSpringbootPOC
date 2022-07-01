package com.dynamodb.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamoDBDocument
@Builder(toBuilder = true)
public class EventType {
    @DynamoDBAttribute
    private UserInfo userInfo;
    private LoginInfo loginInfo;
    private Investment investment;
    private Banking banking;

}
