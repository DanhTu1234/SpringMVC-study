package laptrinhjavaweb.config;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;

public class AwsS3Factory {
    public static S3Client create(String accessKey, String secretKey, String region) {
        AwsBasicCredentials awsCreds = AwsBasicCredentials.create(accessKey, secretKey);

        return S3Client.builder()
                .region(Region.of(region))
                .credentialsProvider(StaticCredentialsProvider.create(awsCreds))
                .build();
    }

    public static S3Presigner s3Presigner(String accessKey, String secretKey, String region) {
        AwsBasicCredentials creds = AwsBasicCredentials.create(accessKey, secretKey);
        return S3Presigner.builder()
                .region(Region.of(region))
                .credentialsProvider(StaticCredentialsProvider.create(creds))
                .build();
    }
}
