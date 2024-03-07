package com.atakan.rabbitmq.consumer;

import com.atakan.dto.request.UserProfileSaveRequestDto;
import com.atakan.rabbitmq.model.CreateUser;
import com.atakan.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateUserConsumer {
    private final UserProfileService userProfileService;
    @RabbitListener(queues = "queue-auth-create-user")
    public void createUserFromQueue(CreateUser createUser){
        System.out.println("Kuyruk dinlendi gelen kayıt."+ createUser.toString());
        userProfileService.save(UserProfileSaveRequestDto.builder()
                        .userName(createUser.getUserName())
                        .authId(createUser.getAuthId())
                .build());
    }
}
