package com.dynamodb.entity;

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
public class UpdatedField {
    private String fieldName;
    private String newValue;
}
