package com.qpay.config;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.BasicSessionCredentials;
import com.amazonaws.services.secretsmanager.AWSSecretsManagerClient;
import com.amazonaws.services.secretsmanager.AWSSecretsManagerClientBuilder;
import com.amazonaws.services.secretsmanager.model.GetSecretValueRequest;
import com.amazonaws.services.secretsmanager.model.GetSecretValueResult;
import com.amazonaws.services.securitytoken.AWSSecurityTokenService;
import com.amazonaws.services.securitytoken.AWSSecurityTokenServiceAsyncClientBuilder;
import com.amazonaws.services.securitytoken.model.AssumeRoleResult;
import com.amazonaws.services.securitytoken.model.Credentials;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qpay.dto.AwsMysqlSecrets;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

//@Configuration
@Slf4j
public class AwsRdsSecrets {


    ObjectMapper objectMapper = new ObjectMapper();
    @Value("${aws.secrets-manager.secret-id}")
    private String secret_id;
    @Value("${aws.secrets-manager.secret-key}")
    private String secret_key;
    @Value("${aws.secrets-manager.roleArn}")
    private String roleArn;
    @Value("${aws.secrets-manager.region}")
    private String region;
    @Value("${aws.secrets-manager.secret-name}")
    private String secret_name;
    String secret ="";

    /*
    datasource config
     */
   /**
    @Bean
     public DataSource dataSource() throws JsonProcessingException {

         AwsMysqlSecrets awsMysqlSecrets = getSecrets();
         log.debug(awsMysqlSecrets.toString());
         return DataSourceBuilder
                 .create().driverClassName("com.mysql.jdbc.Driver")
                 .url("jdbc:"+awsMysqlSecrets.getEngine()+"://"+awsMysqlSecrets.getHost()+":"+awsMysqlSecrets.getPort()+"/quantum-pay")
                 .username(awsMysqlSecrets.getUsername())
                 .password(awsMysqlSecrets.getPassword())
                 .build();
     }
**/

     /*fetch secret from secrets manager*/
    /**
    private  AwsMysqlSecrets getSecrets() throws JsonProcessingException {


        BasicAWSCredentials basicAWSCredentials = new BasicAWSCredentials(secret_id,secret_key);
        AWSSecurityTokenService awsSecurityTokenService = AWSSecurityTokenServiceAsyncClientBuilder.standard().withRegion(region).withCredentials(new AWSStaticCredentialsProvider(basicAWSCredentials)).build();

        AWSSecretsManagerClient secretsManagerClient1 = (AWSSecretsManagerClient) AWSSecretsManagerClientBuilder.standard().withRegion(region).build();

        com.amazonaws.services.securitytoken.model.AssumeRoleRequest roleRequest = new com.amazonaws.services.securitytoken.model.AssumeRoleRequest().withRoleArn(roleArn).withRoleSessionName("session").withDurationSeconds(3600);

        AssumeRoleResult assumeRoleResult = awsSecurityTokenService.assumeRole(roleRequest);
        Credentials credentials = assumeRoleResult.getCredentials();
        AWSCredentialsProvider credentialsProvider = new AWSStaticCredentialsProvider(new BasicSessionCredentials(credentials.getAccessKeyId(),credentials.getSecretAccessKey(),credentials.getSessionToken()));

        if(credentialsProvider != null){
            GetSecretValueRequest getSecretValueRequest = new com.amazonaws.services.secretsmanager.model.GetSecretValueRequest().withSecretId(secret_name);
            getSecretValueRequest.setRequestCredentialsProvider(credentialsProvider);
            GetSecretValueResult valueResult = secretsManagerClient1.getSecretValue(getSecretValueRequest);

            if(valueResult != null){
                 secret = valueResult.getSecretString();
            }


        }
        AwsMysqlSecrets awsMysqlSecrets = objectMapper.readValue(secret,AwsMysqlSecrets.class);
        return awsMysqlSecrets;
    }
**/
}
