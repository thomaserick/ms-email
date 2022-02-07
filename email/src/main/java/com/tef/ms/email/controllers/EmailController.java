package com.tef.ms.email.controllers;

import com.tef.ms.email.dtos.EmailDTO;
import com.tef.ms.email.models.EmailModel;
import com.tef.ms.email.services.EmailService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/emails")
public class EmailController {


    @Autowired
    private EmailService emailService;


    @PostMapping("/sending")
    public ResponseEntity<EmailModel> sendingEmail(@RequestBody @Valid EmailDTO emailDTO)
    {
        EmailModel emailModel = new EmailModel();
        BeanUtils.copyProperties(emailDTO,emailModel);
        emailService.sendEmail(emailModel);
        return new ResponseEntity<>(emailModel, HttpStatus.CREATED);

    }
}
