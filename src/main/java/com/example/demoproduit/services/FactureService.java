package com.example.demoproduit.services;

import com.example.demoproduit.entities.Client;
import com.example.demoproduit.entities.Commande;
import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public class FactureService {
    private final TemplateEngine templateEngine;

    public FactureService(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    public byte[] genererFacturePdf(Client client, Commande commande) throws IOException {
        Context context = new Context();
        context.setVariable("client", client);
        context.setVariable("commande", commande);

        String html = templateEngine.process("facture", context);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PdfRendererBuilder builder = new PdfRendererBuilder();
        builder.useFastMode();
        builder.withHtmlContent(html, null);
        builder.toStream(outputStream);
        builder.run();
        return outputStream.toByteArray();
    }



}
