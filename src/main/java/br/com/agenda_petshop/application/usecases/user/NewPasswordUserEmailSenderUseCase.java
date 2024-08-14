package br.com.agenda_petshop.application.usecases.user;

import br.com.agenda_petshop.application.exceptions.EmailException;
import br.com.agenda_petshop.model.user.User;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.HashMap;
import java.util.Map;

@Service
public class NewPasswordUserEmailSenderUseCase {
    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;

    public NewPasswordUserEmailSenderUseCase(JavaMailSender mailSender, TemplateEngine templateEngine) {
        this.mailSender = mailSender;
        this.templateEngine = templateEngine;
    }

    /*public void execute(User user, String subject) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            Map<String,Object> variables = new HashMap<>();
            variables.put("name", user.getName());
            variables.put("email", user.getEmail());
            variables.put("password", user.getPassword());

            Context context = new Context();
            context.setVariables(variables);

            String htmlContent = templateEngine.process("new-password-email-template", context);
            helper.setTo(user.getEmail());
            helper.setSubject(subject);
            helper.setText(htmlContent, true);
            //TODO: desativado por hora
            //mailSender.send(message);
        } catch (MessagingException e) {
            throw new EmailException("Erro ao tentar enviar email.", e);
        }
    }*/

    public String execute(User user, String subject) {

        Model model = new ExtendedModelMap();

        Map<String,Object> variables = new HashMap<>();
        variables.put("name", user.getName());
        variables.put("email", user.getEmail());
        variables.put("password", user.getPassword());

        model.addAllAttributes(variables);

        return "new-password-email-template";
    }
}
