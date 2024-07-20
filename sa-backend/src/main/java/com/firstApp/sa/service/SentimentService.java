package com.firstApp.sa.service;

import com.firstApp.sa.entities.Client;
import com.firstApp.sa.entities.Sentiment;
import com.firstApp.sa.enums.TypeSentiment;
import com.firstApp.sa.repository.SentimentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SentimentService {

    private ClientService clientService;
    private SentimentRepository sentimentRepository;

    public SentimentService(ClientService clientService, SentimentRepository sentimentRepository) {
        this.clientService = clientService;
        this.sentimentRepository = sentimentRepository;
    }

    public void creer(Sentiment sentiment)
    {
        Client client = this.clientService.lireOuCreer(sentiment.getClient());
        sentiment.setClient(client);

        // Analyse
        if (sentiment.getTexte().contains("pas"))
        {
            sentiment.setType(TypeSentiment.NEGATIF);
        }
        else
        {
            sentiment.setType(TypeSentiment.POSITIF);
        }

        this.sentimentRepository.save(sentiment);
    }

    public List<Sentiment> rechercher(TypeSentiment type) {

        if (type == null)
        {
            return this.sentimentRepository.findAll();
        }
        else
        {
            return this.sentimentRepository.findByType(type);
        }
    }

    public void supprimer(int id) {
        this.sentimentRepository.deleteById(id);
    }
}
