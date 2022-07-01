package com.dynamodb.repository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.util.StringUtils;
import com.dynamodb.entity.UserHistory;
import com.dynamodb.util.DateUtil;

@Repository
public class UserRepository {

    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    public List<UserHistory> getUserHistory(String startDate, String endDate, String personId) {

        LocalDate date = LocalDate.now();
        if(StringUtils.isNullOrEmpty(startDate) || StringUtils.isNullOrEmpty(endDate)){
            LocalDate firstDateOfYear = DateUtil.getFirstDateOfCurrentYear(date);
            startDate = firstDateOfYear.format(DateTimeFormatter.ofPattern("YYYY-MM-dd"));

        }else{
            startDate = date.minusMonths(18).format(DateTimeFormatter.ofPattern("YYYY-MM-dd"));
        }
        endDate = date.format(DateTimeFormatter.ofPattern("YYYY-MM-dd"));
        Map<String, AttributeValue> attrMap = new HashMap<String, AttributeValue>();
        attrMap.put(":val1", new AttributeValue().withS(personId));
        attrMap.put(":val2", new AttributeValue().withS(startDate));
        attrMap.put(":val3", new AttributeValue().withS(endDate));

        DynamoDBQueryExpression<UserHistory> queryExpression = new DynamoDBQueryExpression<UserHistory>()
                .withIndexName("eventTimeStamp-index")
                .withKeyConditionExpression("personId = :val1 and eventTimeStamp between :val2 and :val3")
                .withExpressionAttributeValues(attrMap);

        List<UserHistory> userHistory = dynamoDBMapper.query(UserHistory.class, queryExpression);

        return userHistory;
    }

    public UserHistory saveUserHistory(UserHistory userHistory) {
        dynamoDBMapper.save(userHistory);
        return userHistory;
    }
}
