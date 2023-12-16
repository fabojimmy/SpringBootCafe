package com.inn.cafe.cafe.utils;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.mail.SimpleMailMessage;
// import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailUtils {

    // private JavaMailSender emailSender;

    // public void sendSmpleMessage(String to,String subjct,String text,List<String>list)
    // {

    //     SimpleMailMessage message = new SimpleMailMessage();
    //     message.setFrom("fabojimmy04@gmail.com");
    //     message.setTo(to);
    //     message.setSubject(subjct);
    //     message.setText(text);

    //         if(list!=null&&list.size()>0)
    //         {

    //             message.setCc(getCcArray(list));
    //             emailSender.send(message);
    //         }

    // }
    // private String[] getCcArray(List<String> ccList)
    // {
    //     String[] cc = new String[ccList.size()];

    //     for (int i = 0; i < ccList.size(); i++)
    //     {
    //         cc[i] = ccList.get(i);
    //     }

    //     return cc;
    // }

    // public void forgotMail(String to,String subject,String password)
    // {
    //     MineMessage message=emailSender.createMineMessage(to,subject,password);
    //     MineMessageHelper helper=new MineMessageHelper(message,true);
    //     helper.setFrom("fabojimmy04@gmail.com");
    //     helper.setTo(to);
    //     helper.setSubject(subject);

    //     String htmlMsg="<p><b>Your Login details for Cafe Management System <b><br><b>Emaol:<b>"+to+"</b></b>Password:<br>"+password+"</b><a href=\"http://localhost:4200/\">Click here to login</a></p>";
    //     message.setContent(htmlMsg,"text/html");
    //     emailSender.send(message);
    // }


}
