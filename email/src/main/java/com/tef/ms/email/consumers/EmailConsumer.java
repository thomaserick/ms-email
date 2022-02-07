package com.tef.ms.email.consumers;

import com.tef.ms.email.dtos.EmailDTO;
import com.tef.ms.email.models.EmailModel;
import com.tef.ms.email.services.EmailService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class EmailConsumer {


    @Autowired
    EmailService emailService;


    @RabbitListener(queues = "${spring.rabbitmq.queue}")
    public void listen(@Payload EmailDTO emailDTO)
    {
        EmailModel emailModel = new EmailModel();
        BeanUtils.copyProperties(emailDTO,emailModel);
        emailService.sendEmail(emailModel);
    }


}
